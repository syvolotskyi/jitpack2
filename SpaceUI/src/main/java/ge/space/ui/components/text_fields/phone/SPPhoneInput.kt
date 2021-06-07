package ge.space.ui.components.text_fields.phone

import android.content.Context
import android.content.res.TypedArray
import android.text.Editable
import android.text.InputType
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.widget.TextView
import androidx.annotation.AttrRes
import ge.space.spaceui.R
import ge.space.ui.components.text_fields.base.SPBaseTextField


class SPPhoneInput @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBaseTextField(context, attrs, defStyleAttr), TextWatcher {

    val SPACE = " "
    private val onEditorActionListener: TextView.OnEditorActionListener =
        TextView.OnEditorActionListener { v, actionId, event ->
            when (actionId) {
                else -> true
            }
        }
    private var mask: String? = null
    private var charRepresentation = 0.toChar()
    private var keepHint = false
    private lateinit var rawToMask: IntArray
    private var rawText: RawText = RawText()
    private var editingBefore = false
    private var editingOnChanged = false
    private var editingAfter = false
    private lateinit var maskToRaw: IntArray
    private var selection = 0
    private var initialized = false
    private var ignore = false
    protected var maxRawLength = 0
    private var lastValidMaskPosition = 0
    private var selectionChanged = false
    private var focusChangeListener: OnFocusChangeListener? = null
    private var allowedChars: String = "1234567890"
    private var shouldKeepText = false

   init {
        val attributes: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.SPTextInputPassword)
        mask = attributes.getString(R.styleable.SPTextInputPassword_sp_mask)
        val enableImeAction: Boolean =
            attributes.getBoolean(R.styleable.SPTextInputPassword_sp_enable_ime_action, false)
        charRepresentation = '#'

          cleanUp()

        // Ignoring enter key presses if needed
        if (!enableImeAction) {
            _binding.etInputField.setOnEditorActionListener(onEditorActionListener)
        } else {
            _binding.etInputField.setOnEditorActionListener(null)
        }
        attributes.recycle()
       _binding.etInputField.addTextChangedListener(this)
       _binding.etInputField.inputType = InputType.TYPE_CLASS_PHONE
    }


    /** @param listener - its onFocusChange() method will be called before performing MaskedEditText operations,
     * related to this event.
     */
    override fun setOnFocusChangeListener(listener: OnFocusChangeListener?) {
        focusChangeListener = listener
    }

    private fun cleanUp() {
        initialized = false
        if (mask == null || mask!!.isEmpty()) {
            return
        }
        generatePositionArrays()
        if (!shouldKeepText || rawText == null) {
            rawText = RawText()
            selection = rawToMask[0]
        }
        editingBefore = true
        editingOnChanged = true
        editingAfter = true
        text = makeMaskedTextWithHint().toString()
        editingBefore = false
        editingOnChanged = false
        editingAfter = false
        maxRawLength = maskToRaw[previousValidPosition(mask!!.length - 1)] + 1
        lastValidMaskPosition = findLastValidMaskPosition()
        initialized = true
        super.setOnFocusChangeListener { v, hasFocus ->
            if (focusChangeListener != null) {
                focusChangeListener!!.onFocusChange(v, hasFocus)
            }
            if (hasFocus()) {
                selectionChanged = false
                _binding.etInputField.setSelection(lastValidPosition())
            }
        }
    }

    private fun findLastValidMaskPosition(): Int {
        for (i in maskToRaw.indices.reversed()) {
            if (maskToRaw[i] != -1) return i
        }
        throw RuntimeException("Mask must contain at least one representation char")
    }

    private fun hasHint(): Boolean {
        return  _binding.etInputField.getHint() != null
    }

    fun setShouldKeepText(shouldKeepText: Boolean) {
        this.shouldKeepText = shouldKeepText
    }

    fun isKeepingText(): Boolean {
        return shouldKeepText
    }

    fun setMask(mask: String?) {
        this.mask = mask
        cleanUp()
    }

    fun getMask(): String? {
        return mask
    }

    fun setImeActionEnabled(isEnabled: Boolean) {
        if (isEnabled)  _binding.etInputField.setOnEditorActionListener(onEditorActionListener) else  _binding.etInputField.setOnEditorActionListener(
            null
        )
    }

    private fun getRawText(): String {
        return rawText.text
    }

    fun setCharRepresentation(charRepresentation: Char) {
        this.charRepresentation = charRepresentation
        cleanUp()
    }

    fun getCharRepresentation(): Char {
        return charRepresentation
    }

    /**
     * Generates positions for values characters. For instance:
     * Input data: mask = "+7(###)###-##-##
     * After method execution:
     * rawToMask = [3, 4, 5, 6, 8, 9, 11, 12, 14, 15]
     * maskToRaw = [-1, -1, -1, 0, 1, 2, -1, 3, 4, 5, -1, 6, 7, -1, 8, 9]
     * charsInMask = "+7()- " (and space, yes)
     */
    private fun generatePositionArrays() {
        val aux = IntArray(mask!!.length)
        maskToRaw = IntArray(mask!!.length)
        var charsInMaskAux = ""
        var charIndex = 0
        for (i in mask!!.indices) {
            val currentChar = mask!![i]
            if (currentChar == charRepresentation) {
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
        val charsInMask = charsInMaskAux.toCharArray()
        rawToMask = IntArray(charIndex)
        System.arraycopy(aux, 0, rawToMask, 0, charIndex)
    }

   override fun beforeTextChanged(
        s: CharSequence?, start: Int, count: Int,
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
            val range: Range = calculateRange(rangeStart, start + count)
            if (range.start != -1) {
                rawText.subtractFromString(range)
            }
            if (count > 0) {
                selection = previousValidPosition(start)
            }
        }
    }

    private fun erasingStart(start: Int): Int {
        var start = start
        while (start > 0 && maskToRaw[start] == -1) {
            start--
        }
        return start
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
                count = rawText.addToString(clear(addedString)?: "", startingPosition, maxRawLength)
                if (initialized) {
                    val currentPosition: Int
                    currentPosition =
                        if (startingPosition + count < rawToMask.size) rawToMask[startingPosition + count] else lastValidMaskPosition + 1
                    selection = nextValidPosition(currentPosition)
                }
            }
        }
    }

    override fun afterTextChanged(s: Editable?) {
        if (!editingAfter && editingBefore && editingOnChanged) {
            editingAfter = true
            if (hasHint() && (keepHint || rawText.length() === 0)) {
                text = makeMaskedTextWithHint().toString()
            } else {
                text = makeMaskedText()
            }
            selectionChanged = false
            _binding.etInputField.setSelection(selection)
            editingBefore = false
            editingOnChanged = false
            editingAfter = false
            ignore = false
        }
    }

    fun isKeepHint(): Boolean {
        return keepHint
    }

    fun setKeepHint(keepHint: Boolean) {
        this.keepHint = keepHint
        text = getRawText()
    }


    private fun nextValidPosition(currentPosition: Int): Int {
        var currentPosition = currentPosition
        while (currentPosition < lastValidMaskPosition && maskToRaw[currentPosition] == -1) {
            currentPosition++
        }
        return if (currentPosition > lastValidMaskPosition) lastValidMaskPosition + 1 else currentPosition
    }

    private fun previousValidPosition(currentPosition: Int): Int {
        var currentPosition = currentPosition
        while (currentPosition >= 0 && maskToRaw[currentPosition] == -1) {
            currentPosition--
            if (currentPosition < 0) {
                return nextValidPosition(0)
            }
        }
        return currentPosition
    }

    private fun lastValidPosition(): Int {
        return if (rawText!!.length() === maxRawLength) {
            rawToMask[rawText!!.length() - 1] + 1
        } else nextValidPosition(rawToMask[rawText.length()])
    }


    private fun makeMaskedText(): String {
        val maskedTextLength: Int
        maskedTextLength = if (rawText.length() < rawToMask.size) {
            rawToMask[rawText.length()]
        } else {
            mask!!.length
        }
        val maskedText =
            CharArray(maskedTextLength) //mask.replace(charRepresentation, ' ').toCharArray();
        for (i in maskedText.indices) {
            val rawIndex = maskToRaw[i]
            if (rawIndex == -1) {
                maskedText[i] = mask!![i]
            } else {
                maskedText[i] = rawText!!.charAt(rawIndex)
            }
        }
        return String(maskedText)
    }

    private fun makeMaskedTextWithHint(): CharSequence {
        val ssb = SpannableStringBuilder()
        var mtrv: Int
        val maskFirstChunkEnd = rawToMask[0]
        for (i in mask!!.indices) {
            mtrv = maskToRaw[i]
            if (mtrv != -1) {
                if (mtrv < rawText?.length()!!) {
                    ssb.append(rawText!!.charAt(mtrv))
                } else {
                    ssb.append(_binding.etInputField.getHint().get(maskToRaw[i]))
                }
            } else {
                ssb.append(mask!![i])
            }
            if (keepHint && rawText!!.length() < rawToMask.size && i >= rawToMask[rawText!!.length()]
                || !keepHint && i >= maskFirstChunkEnd
            ) {
                ssb.setSpan(ForegroundColorSpan(_binding.etInputField.getCurrentHintTextColor()), i, i + 1, 0)
            }
        }
        return ssb
    }

    private fun calculateRange(start: Int, end: Int): Range {
        val range = Range()
        var i = start
        while (i <= end && i < mask!!.length) {
            if (maskToRaw[i] != -1) {
                if (range.start === -1) {
                    range.start = maskToRaw[i]
                }
                range.end = maskToRaw[i]
            }
            i++
        }
        if (end == mask?.length) {
            range.end = rawText.length()
        }
        if (range.start === range.end && start < end) {
            val newStart = previousValidPosition(range.start - 1)
            if (newStart < range.start) {
                range.start = newStart
            }
        }
        return range
    }

    private fun clear(string: String): String? {
        var string = string
        val builder = StringBuilder(string.length)
        for (c in string.toCharArray()) {
            if (allowedChars.contains(c.toString())) {
                builder.append(c)
            }
        }
        string = builder.toString()
        return string
    }
}