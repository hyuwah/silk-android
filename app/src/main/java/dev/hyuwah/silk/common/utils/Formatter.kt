package dev.hyuwah.silk.common.utils

import java.text.NumberFormat
import java.util.Locale

fun Long.toRupiahString(): String {
    return NumberFormat.getCurrencyInstance(Locale("in", "ID")).apply {
        maximumFractionDigits = 0
    }.format(this)
}