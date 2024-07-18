package com.jeluchu.scarlex.core.exception

open class ScarlexException(override val message: String?, val code: Int? = null) : Exception()