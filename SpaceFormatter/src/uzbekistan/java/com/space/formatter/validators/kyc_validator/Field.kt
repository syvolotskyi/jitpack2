package com.space.formatter.validators.kyc_validator

import androidx.annotation.StringRes
import com.space.formatter.R

enum class Field {
    WORKPLACE,
    ACTUAL_REGION,
    ACTUAL_LOCALITY,
    ACTUAL_STREET,
    MISSING_REGION,
    MISSING_CITY,
    MISSING_STREET,
    HAS_TMP_ADDRESS,
    TMP_REGION,
    TMP_LOCALITY,
    TMP_STREET,
    HAS_HOME_PHONE,
    EMAIL;

    @StringRes
    fun getErrorMessage(): Int {
        return when (this) {
            WORKPLACE -> R.string.formatter_validator_workplace_error
            ACTUAL_REGION -> R.string.formatter_validator_actual_region_error
            ACTUAL_LOCALITY -> R.string.formatter_validator_actual_locality_error
            ACTUAL_STREET -> R.string.formatter_validator_actual_street_error
            MISSING_REGION -> R.string.formatter_validator_missing_region_empty
            MISSING_CITY -> R.string.formatter_validator_missing_city_empty
            MISSING_STREET -> R.string.formatter_validator_missing_street_empty
            HAS_TMP_ADDRESS -> R.string.formatter_validator_temp_address_error
            TMP_REGION -> R.string.formatter_validator_temp_region_error
            TMP_LOCALITY -> R.string.formatter_validator_temp_locality_error
            TMP_STREET -> R.string.formatter_validator_actual_street_error
            HAS_HOME_PHONE -> R.string.formatter_validator_home_phone_error
            EMAIL -> R.string.formatter_validator_email_error
        }
    }
}