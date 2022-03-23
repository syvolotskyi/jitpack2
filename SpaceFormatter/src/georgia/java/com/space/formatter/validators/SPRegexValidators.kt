package com.space.formatter.validators

/**
 *  GEO
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

    val MOBILE_PHONE_LONG = Regex("^\\+?9955[0-9]{8}")

    val LAND_LINE_SHORT = Regex("")

    val LAND_LINE_LONG = Regex("")

    val WHOLE = 5

    val FRACTION = 2

    val PROMO_CODE = Regex("^[0-9A-Z]{8}")
}