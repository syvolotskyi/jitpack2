package ge.space.design.main


@Suppress("NOTHING_TO_INLINE")
inline fun showcaseError(message: Any): Nothing = throw RuntimeException(message.toString())