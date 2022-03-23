package com.space.formatter.validators.kyc_validator

import com.space.formatter.extensions.isAddress
import com.space.formatter.extensions.isEmail

class SPValidator {
    private var isEmptyField = false
    private var isBadFormat = false
    private var isSmileFormat = false

    fun validate(fields: Map<Field, String?>, checkTmpAddress: Boolean?): SPValidatorResponse {
        var result: SPValidatorResponse = SPValidData
        for ((field, value) in fields) {
            when {
                field == Field.WORKPLACE -> validateWorkPlace(value)
                field == Field.ACTUAL_STREET -> validateStreet(value)
                field == Field.EMAIL -> validateEmail(value)
                field == Field.MISSING_STREET -> validateStreet(value)
                field == Field.MISSING_REGION || field == Field.MISSING_CITY -> validateNonEmpty(value)
                checkTmpAddress == true && field == Field.TMP_STREET -> validateStreet(value)
                checkTmpAddress == true -> validateNonEmpty(value)
                checkTmpAddress == null && field == Field.HAS_TMP_ADDRESS -> isEmptyField = true
                isNotTempAddress(field) && (checkTmpAddress == false || checkTmpAddress == null) -> validateNonEmpty(
                    value
                )
            }

            //Email isn't mandatory
            if (isEmptyField && field != Field.EMAIL) {
                result = SPEmptyField(field)
                break
            }
            if (isBadFormat) {
                result = SPInvalidFormat(field)
                break
            }
            if (isSmileFormat) {
                result = SPSmileFormat(field)
                break
            }
        }

        reset()
        return result
    }

    private fun reset() {
        isEmptyField = false
        isBadFormat = false
    }

    private fun isNotTempAddress(field: Field): Boolean {
        return field != Field.TMP_REGION && field != Field.TMP_LOCALITY && field != Field.TMP_STREET
    }

    private fun validateNonEmpty(value: String?) {
        isEmptyField = value.isNullOrEmpty()
    }

    private fun validateStreet(street: String?) {
        validateNonEmpty(street)
        if (!isEmptyField) {
            isSmileFormat = street!!.contains(REGEX_EMOJIES)
            isBadFormat = !street.isAddress()
        }
    }

    private fun validateWorkPlace(workPlace: String?) {
        validateNonEmpty(workPlace)
        if (!isEmptyField) {
            isSmileFormat = workPlace!!.contains(REGEX_EMOJIES)
        }
    }

    private fun validateEmail(email: String?) {
        if (!email.isNullOrEmpty()) {
            isBadFormat = !email.isEmail()
        }
    }

    companion object {
        private val REGEX_EMOJIES = Regex("(?:[\uD83C\uDF00-\uD83D\uDDFF]|[\uD83E\uDD00-\uD83E\uDDFF]|" +
                "[\uD83D\uDE00-\uD83D\uDE4F]|[\uD83D\uDE80-\uD83D\uDEFF]|" +
                "[\u2600-\u26FF]\uFE0F?|[\u2700-\u27BF]\uFE0F?|\u24C2\uFE0F?|" +
                "[\uD83C\uDDE6-\uD83C\uDDFF]{1,2}|" +
                "[\uD83C\uDD70\uD83C\uDD71\uD83C\uDD7E\uD83C\uDD7F\uD83C\uDD8E\uD83C\uDD91-\uD83C\uDD9A]\uFE0F?|" +
                "[\u0023\u002A\u0030-\u0039]\uFE0F?\u20E3|[\u2194-\u2199\u21A9-\u21AA]\uFE0F?|[\u2B05-\u2B07\u2B1B\u2B1C\u2B50\u2B55]\uFE0F?|" +
                "[\u2934\u2935]\uFE0F?|[\u3030\u303D]\uFE0F?|[\u3297\u3299]\uFE0F?|" +
                "[\uD83C\uDE01\uD83C\uDE02\uD83C\uDE1A\uD83C\uDE2F\uD83C\uDE32-\uD83C\uDE3A\uD83C\uDE50\uD83C\uDE51]\uFE0F?|" +
                "[\u203C\u2049]\uFE0F?|[\u25AA\u25AB\u25B6\u25C0\u25FB-\u25FE]\uFE0F?|" +
                "[\u00A9\u00AE]\uFE0F?|[\u2122\u2139]\uFE0F?|\uD83C\uDC04\uFE0F?|\uD83C\uDCCF\uFE0F?|" +
                "[\u231A\u231B\u2328\u23CF\u23E9-\u23F3\u23F8-\u23FA]\uFE0F?)+")
    }
}
