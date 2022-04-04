package ge.space.ui.util.extension

import android.content.Context
import android.widget.TextView
import java.util.*
import java.util.concurrent.TimeUnit

fun Array<String>.getMonthName(month: Int = 0, isShotName: Boolean = false): String {
    if (month <= 0) return ""

    var monthValue: String = this[month - 1]
    if (isShotName)
        monthValue = if (monthValue.length > 3) monthValue.substring(0,3) else monthValue

    return monthValue
}

fun getLongAsDate(year: Int, month: Int, date: Int):Long {
    val timeZone = TimeZone.getTimeZone("UTC")
    val calendar = Calendar.getInstance(timeZone)
    calendar.set(Calendar.DAY_OF_MONTH, date)
    calendar.set(Calendar.MONTH, month - 1)
    calendar.set(Calendar.YEAR, year)

    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)

    return calendar.timeInMillis
}


fun Int.monthStringFormat():String {
    return if (this < 10) "0${this}" else this.toString()
}
fun Int.dayStringFormat():String {
    return if (this < 10) "0${this}" else this.toString()
}

fun Int.hourOrMinutesStringFormat():String {
    return if (this < 10) "0${this}" else this.toString()
}

fun Long.lessYearMonthDay(time: Long): Boolean{

    return (this.getYear() > time.getYear())
            || (this.getYear() == time.getYear() && this.getMonth() > time.getMonth())
            || (this.getYear() == time.getYear() && this.getMonth() == time.getMonth() && this.getDayOfMonth() > time.getDayOfMonth())
}

fun getDaysInMonth(month: Int, year: Int): Int {
    return if (month == 2)
        28 + (if (year % 4 == 0) 1 else 0) - (if (year % 100 == 0) if (year % 400 == 0) 0 else 1 else 0)
    else
        31 - (month - 1) % 7 % 2
}

fun Long.getYear(timeZone: TimeZone = TimeZone.getTimeZone("UTC")): Int {
    val cal = Calendar.getInstance(timeZone)
    cal.timeInMillis = this
    return cal.get(Calendar.YEAR)
}

fun Long.getMonth(timeZone: TimeZone = TimeZone.getTimeZone("UTC")): Int {
    val cal = Calendar.getInstance(timeZone)
    cal.timeInMillis = this
    return cal.get(Calendar.MONTH)
}

fun Long.getLastDayOfMonth(timeZone: TimeZone = TimeZone.getTimeZone("UTC")): Int {
    val cal = Calendar.getInstance(timeZone)
    cal.timeInMillis = this
    return cal.getActualMaximum(Calendar.DAY_OF_MONTH)
}


fun Long.getDayOfMonth(timeZone: TimeZone = TimeZone.getTimeZone("UTC")): Int {
    val cal = Calendar.getInstance(timeZone)
    cal.timeInMillis = this
    return cal.get(Calendar.DAY_OF_MONTH)
}

fun Long.getHour(timeZone: TimeZone = TimeZone.getTimeZone("UTC")): Int {
    val cal = Calendar.getInstance(timeZone)
    cal.timeInMillis = this
    return cal.get(Calendar.HOUR_OF_DAY)
}

fun Long.getMinutes(timeZone: TimeZone = TimeZone.getTimeZone("UTC")): Int {
    val cal = Calendar.getInstance(timeZone)
    cal.timeInMillis = this
    return cal.get(Calendar.MINUTE)
}

fun Int.yearLastTwoNumber():String {
    return if (this.toString().length == 4) this.toString().substring(2, 4) else this.toString()
}

fun getTimeFormatFromTo(months: Array<String>, fromTimeStamp: Long? = null, toTimeStamp: Long?= null): String{
    if (fromTimeStamp == null || toTimeStamp == null || fromTimeStamp == 0L || toTimeStamp == 0L)
        return ""

    return "${fromTimeStamp.getDayOfMonth().dayStringFormat()} / ${months.getMonthName(month = fromTimeStamp.getMonth() + 1, isShotName = true)} / ${fromTimeStamp.getYear()} - ${toTimeStamp.getDayOfMonth().dayStringFormat()} / ${months.getMonthName(month = toTimeStamp.getMonth() + 1, isShotName = true)} / ${toTimeStamp.getYear()}"
}

fun dateFormat(timeStamp: Long, months: Array<String>, addColon: Boolean = false, timeZone: TimeZone = TimeZone.getTimeZone("UTC")): String {
    val day: String =
        if (timeStamp.getDayOfMonth(timeZone) < 10) "0${timeStamp.getDayOfMonth(timeZone)}" else timeStamp.getDayOfMonth(timeZone).toString()
    return "$day ${months[timeStamp.getMonth(timeZone)]}${if (addColon) "," else ""} ${timeStamp.getYear(timeZone)}"
}

fun Context.dateFormat(timeStamp: Long, monthsArrayRes: Int, addColon: Boolean = false): String {
    val months = this.resources.getStringArray(monthsArrayRes)
    return dateFormat(timeStamp, months, addColon)
}


fun Long.shortIntervalTo(months: Array<String>, endDate: Long): String {
    val startDay = this.getDayOfMonth().dayStringFormat()
    val endDay = endDate.getDayOfMonth().dayStringFormat()

    var startMonth = months.getMonthName(month = this.getMonth() + 1)
    var endMonth = months.getMonthName(month = endDate.getMonth() + 1)
    val year = endDate.getYear()

    return if (startMonth != endMonth) {
        startMonth = startMonth.substring(0, if (startMonth.length >= 3) 3 else startMonth.length)
        endMonth = endMonth.substring(0, if (endMonth.length >= 3) 3 else endMonth.length)
        "$startDay $startMonth - $endDay $endMonth $year"
    } else {
        "$startDay - $endDay $endMonth $year"
    }
}

fun Long.getTimeLabel():String{
    val minutesPart: Long = TimeUnit.MILLISECONDS.toMinutes(this)
    val secondsPart: Long = TimeUnit.MILLISECONDS.toSeconds(this) - minutesPart * 60
    return "$minutesPart:$secondsPart"
}

fun Long.monthAgo(monthsCount: Int = 1, timeZone: TimeZone = TimeZone.getTimeZone("UTC")): Long {
    val c = Calendar.getInstance(timeZone)
    c.timeInMillis = this
    c.add(Calendar.MONTH, -1)
    return c.timeInMillis
}

fun Long.dateToString() : String{
    return "${this.getDayOfMonth().dayStringFormat()}/${this.getMonth().plus(1).monthStringFormat()}/${this.getYear()}"
}