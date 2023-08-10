package com.yurastico.imc.extensions

import android.widget.EditText

fun EditText.valueDouble() = text.toString().toDouble()

fun EditText.value() = text.toString()