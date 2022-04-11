package ge.space.ui.util.extension

import java.util.concurrent.TimeUnit

fun Long.getTimeLabel():String{
    val minutesPart: Long = TimeUnit.MILLISECONDS.toMinutes(this)
    val secondsPart: Long = TimeUnit.MILLISECONDS.toSeconds(this) - minutesPart * 60
    return "$minutesPart:$secondsPart"
}