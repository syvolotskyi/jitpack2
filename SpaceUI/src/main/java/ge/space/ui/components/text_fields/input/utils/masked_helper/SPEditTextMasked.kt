package ge.space.ui.components.text_fields.input.utils.masked_helper

import android.content.Context
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.view.KeyEvent
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.widget.AppCompatEditText
import ge.space.spaceui.R

open class SPEditTextMasked : AppCompatEditText, TextWatcher {

    private var keepHint = true
    private var rawText: RawText = RawText()
    private var editingBefore = false
    private var editingOnChanged = false
    private var editingAfter = false
    private var select = 0
    private var initialized = false
    private var ignore = false
    private var maxRawLength = 0
    private var lastValidMaskPosition = 0
    private var selectionChanged = false
    private var focusChangeListener: OnFocusChangeListener? = null
    private var isKeepingText = false
    private lateinit var rawToMask: IntArray
    private lateinit var maskToRaw: IntArray

    var onActionListener =
        OnEditorActionListener { _: TextView?, _: Int, _: KeyEvent? ->
            return@OnEditorActionListener true
        }
        set(value) {
            field = value
            setOnEditorActionListener(onActionListener)
        }

    var mask: String = ""
        set(value) {
            field = value
            cleanUp()
        }

    constructor(context: Context) :
            super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) :
            super(context, attrs, defStyle) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    fun setImeActionEnabled(isEnabled: Boolean) {
        if (isEnabled) {
            setOnEditorActionListener(onActionListener)
        } else {
            setOnEditorActionListener(null)
        }
    }

    /** @param listener - its onFocusChange() method will be called before performing MaskedEditText operations,
     * related to this event.
     */
    override fun setOnFocusChangeListener(listener: OnFocusChangeListener) {
        focusChangeListener = listener
    }

    override fun beforeTextChanged(
        s: CharSequence, start: Int, count: Int,
        after: Int
    ) {
        if (!editingBefore) {
            editingBefore = true
            if (start > lastValidMaskPosition) {
                ignore = true
            }
            var rangeStart = start
            if (after == 0) {
                rangeStart = erasingStart(start)
            }
            val range = calculateRange(rangeStart, start + count)
            if (range.start != -1) {
                rawText.text = rawText.text.removeRange(range.start, range.end)
            }
            if (count > 0) {
                select = previousValidPosition(start)
            }
        }
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        var count = count
        if (!editingOnChanged && editingBefore) {
            editingOnChanged = true
            if (ignore) {
                return
            }
            if (count > 0) {
                val startingPosition = maskToRaw[nextValidPosition(start)]
                val addedString = s.subSequence(start, start + count).toString()
                count = rawText.addToString(clear(addedString), startingPosition, maxRawLength)
                if (initialized) {
                    val currentPosition =
                        if (startingPosition + count < rawToMask.size) rawToMask[startingPosition + count] else lastValidMaskPosition + 1
                    select = nextValidPosition(currentPosition)
                }
            }
        }
    }

    fun getRawText(): String {
        return text.toString()
            .removeSpaces()
            .removeAllSymbols(MASK_SYMBOL)
            .trim()
    }

    override fun afterTextChanged(s: Editable) {
        if (!editingAfter && editingBefore && editingOnChanged) {
            editingAfter = true
            s.clear()
            s.append(makeMaskedTextWithHint())
            selectionChanged = false
            setSelection(select)
            editingBefore = false
            editingOnChanged = false
            editingAfter = false
            ignore = false
        }
    }

    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        // On Android 4+ this method is being called more than 1 time if there is a hint in the EditText, what moves the cursor to left
        // Using the boolean var selectionChanged to limit to one execution
        var selStart = selStart
        var selEnd = selEnd
        if (initialized) {
            if (!selectionChanged) {
                selStart = fixSelection(selStart)
                selEnd = fixSelection(selEnd)

                // exactly in this order. If getText.length() == 0 then selStart will be -1
                if (selStart > text!!.length) selStart = text!!.length
                if (selStart < 0) selStart = 0

                // exactly in this order. If getText.length() == 0 then selEnd will be -1
                if (selEnd > text!!.length) selEnd = text!!.length
                if (selEnd < 0) selEnd = 0
                setSelection(selStart, selEnd)
                selectionChanged = true
            } else {
                //check to see if the current selection is outside the already entered text
                if (selStart > rawText.length() - 1) {
                    val start = fixSelection(selStart)
                    val end = fixSelection(selEnd)
                    if (start >= 0 && end < text!!.length) {
                        setSelection(start, end)
                    }
                }
            }
        }
        super.onSelectionChanged(selStart, selEnd)
    }

    private fun cleanUp() {
        initialized = false
        if (mask.isEmpty()) {
            return
        }
        generatePositionArrays()
        if (!isKeepingText) {
            select = rawToMask[0]
        }
        editingBefore = true
        editingOnChanged = true
        editingAfter = true
        this.setText(makeMaskedTextWithHint())
        editingBefore = false
        editingOnChanged = false
        editingAfter = false
        maxRawLength = maskToRaw[previousValidPosition(mask.length - 1)] + 1
        lastValidMaskPosition = findLastValidMaskPosition()
        initialized = true
        super.setOnFocusChangeListener { v, hasFocus ->
            if (focusChangeListener != null) {
                focusChangeListener!!.onFocusChange(v, hasFocus)
            }
            if (hasFocus()) {
                selectionChanged = false
                this@SPEditTextMasked.setSelection(lastValidPosition())
            }
        }
    }

    private fun findLastValidMaskPosition(): Int {
        for (i in maskToRaw.indices.reversed()) {
            if (maskToRaw[i] != -1) return i
        }
        throw RuntimeException("Mask must contain at least one representation char")
    }

    private fun hasHint(): Boolean = hint != null

    /**
     * Generates positions for values characters. For instance:
     * Input data: mask = "+7(###)###-##-##
     * After method execution:
     * rawToMask = [3, 4, 5, 6, 8, 9, 11, 12, 14, 15]
     * maskToRaw = [-1, -1, -1, 0, 1, 2, -1, 3, 4, 5, -1, 6, 7, -1, 8, 9]
     * charsInMask = "+7()- " (and space, yes)
     */
    private fun generatePositionArrays() {
        val aux = IntArray(mask.length)
        maskToRaw = IntArray(mask.length)
        var charsInMaskAux = ""
        var charIndex = 0
        for (i in mask.indices) {
            val currentChar = mask[i]
            if (currentChar.toString() == CHAR_REPRESENTATION) {
                aux[charIndex] = i
                maskToRaw[i] = charIndex++
            } else {
                val charAsString = currentChar.toString()
                if (!charsInMaskAux.contains(charAsString)) {
                    charsInMaskAux += charAsString
                }
                maskToRaw[i] = -1
            }
        }
        if (charsInMaskAux.indexOf(' ') < 0) {
            charsInMaskAux += SPACE
        }
        charsInMaskAux.toCharArray()
        rawToMask = IntArray(charIndex)
        System.arraycopy(aux, 0, rawToMask, 0, charIndex)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val ta =
            context.obtainStyledAttributes(attrs, R.styleable.SPEditTextMasked, 0, 0)

        ta.run {

            mask = getString(R.styleable.SPEditTextMasked_mask) ?: ""

            recycle()
        }

        cleanUp()
        addTextChangedListener(this)
    }

    private fun erasingStart(position: Int): Int {
        var start = position
        while (start > 0 && maskToRaw[start] == -1) {
            start--
        }
        return start
    }

    private fun fixSelection(selection: Int): Int {
        return if (selection > lastValidPosition()) {
            lastValidPosition()
        } else {
            nextValidPosition(selection)
        }
    }

    private fun nextValidPosition(position: Int): Int {
        var currentPosition = position
        while (currentPosition < lastValidMaskPosition && maskToRaw[currentPosition] == -1) {
            currentPosition++
        }
        return if (currentPosition > lastValidMaskPosition) lastValidMaskPosition + 1 else currentPosition
    }

    private fun previousValidPosition(position: Int): Int {
        var currentPosition = position
        while (currentPosition >= 0 && maskToRaw[currentPosition] == -1) {
            currentPosition--
            if (currentPosition < 0) {
                return nextValidPosition(0)
            }
        }
        return currentPosition
    }

    private fun lastValidPosition(): Int {
        return if (rawText.length() == maxRawLength) {
            rawToMask[rawText.length() - 1] + 1
        } else nextValidPosition(rawToMask[rawText.length()])
    }

    private fun makeMaskedTextWithHint(): CharSequence {
        val ssb = SpannableStringBuilder()
        var mtrv: Int
        val maskFirstChunkEnd = rawToMask[0]
        for (i in mask.indices) {
            mtrv = maskToRaw[i]
            if (mtrv != -1) {
                if (mtrv < rawText.length()) {
                    ssb.append(rawText.charAt(mtrv))
                } else {
                    ssb.append(hint[maskToRaw[i]])
                }
            } else {
                ssb.append(mask[i])
            }
            if (keepHint && rawText.length() < rawToMask.size && i >= rawToMask[rawText.length()]
                || !keepHint && i >= maskFirstChunkEnd
            ) {
                ssb.setSpan(ForegroundColorSpan(currentHintTextColor), i, i + 1, 0)
            }
        }
        return ssb
    }

    private fun String.removeSpaces() = replace("\\s".toRegex(), "")

    private fun String.removeAllSymbols(symbol: String) = replace(symbol, "")


    private fun calculateRange(start: Int, end: Int): Range {
        val range = Range()
        var i = start
        while (i <= end && i < mask.length) {
            if (maskToRaw[i] != -1) {
                if (range.start == -1) {
                    range.start = maskToRaw[i]
                }
                range.end = maskToRaw[i]
            }
            i++
        }
        if (end == mask.length) {
            range.end = rawText.length()
        }
        if (range.start == range.end && start < end) {
            val newStart = previousValidPosition(range.start - 1)
            if (newStart < range.start) {
                range.start = newStart
            }
        }
        return range
    }

    private fun clear(string: String): String {
        var string = string
        val builder = StringBuilder(string.length)
        for (c in string.toCharArray()) {
            if (ALLOWED_CHARS.contains(c.toString())) {
                builder.append(c)
            }
        }
        string = builder.toString()

        return string
    }

    companion object {
        const val SPACE = "/"
        const val ALLOWED_CHARS = "1234567890 X"
        const val CHAR_REPRESENTATION = "#"
        const val MASK_SYMBOL = "X"
    }
}