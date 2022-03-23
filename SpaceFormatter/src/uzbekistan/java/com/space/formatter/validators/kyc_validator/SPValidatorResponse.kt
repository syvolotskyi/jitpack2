package com.space.formatter.validators.kyc_validator

sealed class SPValidatorResponse

object SPValidData : SPValidatorResponse()

data class SPInvalidFormat(val field: Field) : SPValidatorResponse()

data class SPEmptyField(val field: Field) : SPValidatorResponse()

data class SPSmileFormat(val field: Field) : SPValidatorResponse()