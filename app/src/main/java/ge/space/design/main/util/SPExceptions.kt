package ge.space.design.main.util

@Suppress("NOTHING_TO_INLINE")
inline fun showcaseError(message: Any): Nothing = throw RuntimeException(message.toString())