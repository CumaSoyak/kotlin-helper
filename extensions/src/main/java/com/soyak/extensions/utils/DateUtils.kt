package com.soyak.extensions.utils

import android.annotation.SuppressLint
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


object DateUtils {

    val c = Calendar.getInstance()
    var day = c.get(Calendar.DAY_OF_MONTH)
    var month = c.get(Calendar.MONTH)
    var year = c.get(Calendar.YEAR)


    var d = Date()
    @SuppressLint("SimpleDateFormat")
    fun currentDate(): String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        return simpleDateFormat.format(d)
    }

    @SuppressLint("SimpleDateFormat")
    fun currentTime(): String {
        val simpleDateFormat = SimpleDateFormat("HH:mm:ss")
        return simpleDateFormat.format(d)

    }

    fun timeDiff(date: String, time: String): Boolean {
        var retriveTimeStatus: Boolean = false
        //  val dateStart = "01/14/2012 09:29:58"
        val dateStart = currentDate().replace("-", "/") + " " + currentTime() + " PM"
        val dateStop = date.replace("-", "/") + " " + time + " PM"
        try {
            val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
            val dateObj1 = sdf.parse(dateStart)
            val dateObj2 = sdf.parse(dateStop)
            val crunchifyFormatter = DecimalFormat("###,###")
            val diff = dateObj2.getTime() - dateObj1.getTime()
            val diffDays = (diff / (24 * 60 * 60 * 1000)).toInt()
            println("difference between days: " + diffDays)
            val diffhours = (diff / (60 * 60 * 1000)).toInt()
            System.out.println("difference between hours: " + crunchifyFormatter.format(diffhours))
            /**Randevu saatine 12 saaten az varsa "Ertele tuşunu kaldır"**/
            if (diffhours >= 0 && diffhours < 12) {
                retriveTimeStatus = true
            }
            /**Randevu zamanını geçmiş ise diffhours eksili dönüyor ve  her zaman "Ertele tuşunu kaldır"*/
            if (diffhours < 0) {
                retriveTimeStatus = true
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return retriveTimeStatus
    }

}