package ge.space.ui.components.bottomsheet.navigation


/**
 * Interface definition for a result callback.
 */
fun interface SPBottomSheetResultListener {
    fun onResult(data: Any?)
}

/**
 * Handler for manual delete subscription and avoid leak
 */
fun interface ResultListenerHandler {
    fun dispose()
}

/**
 * Internal class which help us to store result listeners (by key) and send result to these listeners
 */
internal class SPResultWire {
    private val listeners = mutableMapOf<String, SPBottomSheetResultListener>()

    /**
     * Set a result listener and its key
     *
     * @param key [String] a string key helps to recognize listeners in map
     * @param listener [SPBottomSheetResultListener] a result callback.
     */
    fun setResultListener(
        key: String,
        listener: SPBottomSheetResultListener
    ): ResultListenerHandler {
        listeners[key] = listener
        return ResultListenerHandler {
            listeners.remove(key)
        }
    }

    /**
     * Set a result listener and its key
     *
     * @param key [String] the key of the listener which must be called
     * @param data [Any] is a result, can be nullable. Default is null.
    */
    fun sendResult(key: String, data: Any? = null) {
        listeners.remove(key)?.onResult(data)
    }
}