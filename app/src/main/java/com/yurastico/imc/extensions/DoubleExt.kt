package com.yurastico.imc.extensions

fun Double.format(digits: Int = 2) = java.lang.String.format("%.${digits}f", this)

