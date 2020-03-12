package com.soyak.extensions.utils.extensions

import android.os.Build
import android.text.Html
import android.text.Spanned
import android.widget.TextView
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

fun String.toSpanned(): Spanned {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        return Html.fromHtml(this)
    }
}

fun Double.formatMoney(): String {
    val symbols = DecimalFormatSymbols.getInstance(Locale("tr", "TR"))
    symbols.groupingSeparator = '.'
    symbols.decimalSeparator = ','
    val df = DecimalFormat("#,##", symbols)
    df.groupingSize = 3
    df.isGroupingUsed = true
    df.maximumFractionDigits = 2
    df.minimumFractionDigits = 2
    if (this == 0.0) {
        return "0,00"
    } else {
        return df.format(this)
    }
}

fun String.regexFindIndex(foundString: String): ArrayList<Int?> {
    val indexList: ArrayList<Int?> = arrayListOf()
    val regex = foundString.toRegex()
    val match = regex.find(this)
    println(match?.value)

    indexList.add(match?.range?.first)
    indexList.add(match?.range?.last)
    return indexList
}

fun String.toDate(): String {
    var date = SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(this)
    return SimpleDateFormat("dd MMMM yyyy", Locale("tr", "TR")).format(date).toString()
}

fun String.toDateMonthDay(): String {
    var date = SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(this)
    return SimpleDateFormat("dd MMMM", Locale("tr", "TR")).format(date).toString()
}

fun String.toDateYear(): String {
    var date = SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(this)
    return SimpleDateFormat("yyyy", Locale("tr", "TR")).format(date).toString()
}

fun String.toHours(): String {
    var date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(this)
    return SimpleDateFormat("HH:mm", Locale("tr", "TR")).format(date).toString()
}

fun toDateFormat(text: String?): String {
    var date = ""
    if (!text.isNullOrEmpty()) {
        if (text!!.length >= 10) {
            date = (text.substring(8, 10) + "." + text.substring(5, 7) + "." + text.substring(0, 4))
        }
    }

    return date
}

fun toDateMonthOfYear(text: String): String {
    if (text.length >= 10) {
        val simpledateformat = SimpleDateFormat("MMMM")
        val date = Date(
            text.substring(0, 4).toInt(),
            text.substring(5, 7).toInt() - 1,
            text.substring(8, 10).toInt()
        )
        val monthOfYear = simpledateformat.format(date)
        val day = text.substring(0, 4)
        return day + " " + monthOfYear
    } else
        return text

}

fun toDateMonthOfYearAndDay(text: String): String {
    if (text.length >= 10) {
        val simpledateformat = SimpleDateFormat("MMMM")
        val date = Date(
            text.substring(0, 4).toInt(),
            text.substring(5, 7).toInt() - 1,
            text.substring(8, 10).toInt()
        )
        val monthOfYear = simpledateformat.format(date)
        val day = text.substring(8, 10)


        val simpledateformatDay = SimpleDateFormat("EEEE")
        val dayString =
            Date(
                text.substring(0, 4).toInt(),
                text.substring(5, 7).toInt() - 1,
                text.substring(8, 10).toInt() - 1
            )
        val dayOfWeek = simpledateformatDay.format(dayString)

        return day + " " + monthOfYear + " " + dayOfWeek
    } else
        return text
}

fun toDateDayofMonthYear(text: String?): String {  //exapmle 11 Kasın 2019
    if (text!!.length >= 10) {
        val simpledateformat = SimpleDateFormat("MMMM")
        val date = Date(
            text.substring(0, 4).toInt(),
            text.substring(5, 7).toInt() - 1,
            text.substring(8, 10).toInt()
        )
        val monthOfYear = simpledateformat.format(date)
        val day = text.substring(8, 10)
        val year = text.substring(0, 4)

        return day + " " + monthOfYear + " " + year
    } else {
        return text
    }

}

fun toDateDayofMonth(text: String?): String {  //exapmle 11 Kasım
    if (text!!.length >= 10) {
        val simpledateformat = SimpleDateFormat("MMMM")
        val date = Date(
            text.substring(0, 4).toInt(),
            text.substring(5, 7).toInt() - 1,
            text.substring(8, 10).toInt()
        )
        val monthOfYear = simpledateformat.format(date)
        val day = text.substring(8, 10)
        return day + " " + monthOfYear
    } else {
        return text
    }

}

fun toHourFormat(hour: String?): String {
    if (!hour.isNullOrEmpty())
        return hour.substring(0, 5)
    else
        return ""
}

fun priceText(tvCost: TextView, price: String) {
    if (price.endsWith(".00")) {

        tvCost.text = price.substring(0, price.length - 3) + " TL"

    } else {
        tvCost.text = price + " TL"
    }
}
