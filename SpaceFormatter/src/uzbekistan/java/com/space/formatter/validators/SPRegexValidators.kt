package com.space.formatter.validators

/**
 *  UZ
 */
object SPRegexValidators : SPBaseRegexValidators() {

    private var homePhoneRegex: String?= null
    private var mobilePhoneRegex: String?= null

    fun setHomePhoneRegex(homeRegex: String?){
        homePhoneRegex = homeRegex
    }

    fun setPhonePhoneRegex(phoneRegex: String){
        mobilePhoneRegex = phoneRegex
    }

    val MOBILE_PHONE_SHORT
        get() = Regex(mobilePhoneRegex!!)

    val HOME_PHONE_SHORT
        get() = Regex(homePhoneRegex ?: "")

    val MOBILE_PHONE_LONG = Regex("^\\+?998[0-9]{9}")

    val LAND_LINE_SHORT = Regex("^(6\\d|70|71)\\d{7}$")

    val LAND_LINE_LONG = Regex("((\\+|0{2})?998)?(6\\d|70|71)\\d{7}")

    val WHOLE = 8

    val FRACTION = 2

    val PROMO_CODE = Regex("^[0-9A-Z]{8}")
}