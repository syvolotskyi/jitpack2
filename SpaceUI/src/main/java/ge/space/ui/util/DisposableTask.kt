package ge.space.ui.util

import java.util.concurrent.atomic.AtomicBoolean

/**
 * [DisposableTask] manages to execute task only one time
 */
class DisposableTask {

    private val disposable = AtomicBoolean()

    operator fun invoke(task: () -> Unit) {
        when {
            disposable.get() -> Unit
            disposable.compareAndSet(false, true) -> task()
        }
    }

}