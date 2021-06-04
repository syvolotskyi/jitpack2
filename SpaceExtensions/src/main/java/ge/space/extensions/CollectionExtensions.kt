package ge.space.extensions

fun <T> List<T>.replace(newValue: T, block: (T) -> Boolean): List<T> {
    return map {
        if (block(it)) newValue else it
    }
}

fun <E> Iterable<E>.updated(index: Int, elem: E) =
    mapIndexed { i, existing -> if (i == index) elem else existing }