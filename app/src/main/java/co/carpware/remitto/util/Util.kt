package co.carpware.remitto.util

import android.content.Context
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.RelativeSizeSpan
import android.text.style.SuperscriptSpan
import android.view.View
import android.view.inputmethod.InputMethodManager


/**
 * Created by jide on 08/07/17.
 */

fun titleCase(text: String): String {
    return "${text.first().toUpperCase()}${text.substring(1, text.length).toLowerCase()}"
}

fun toSuperScript(amount: String): SpannableStringBuilder {
    val dotPosition = amount.indexOf(".")
    var temp = amount
    if (dotPosition == amount.indexOfLast { it != null }) {
        temp = temp.replace("$", "")
        return SpannableStringBuilder("$$temp")
    }

    temp = amount.replace(".", "", true)
    val newAmount = SpannableStringBuilder(temp)
    newAmount.setSpan(SuperscriptSpan(), dotPosition, temp.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    newAmount.setSpan(RelativeSizeSpan(0.60f), dotPosition, temp.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return newAmount
}

fun hideKeyboard(view: View, context: Context) {
    val inputMethodManager = context.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (view != null) inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}


