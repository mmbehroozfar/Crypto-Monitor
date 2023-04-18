package com.mmb.ui.asset.mapper

import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class DateMapper @Inject constructor() {

    operator fun invoke(type: String): String {
        val outputPattern = "dd MMM yyyy - hh:mm a"

        val inputFormat = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            Locale.getDefault()
        )
        val outputFormat = SimpleDateFormat(
            outputPattern,
            Locale.getDefault()
        )

        val date = inputFormat.parse(type)
        return try {
            if (date != null) outputFormat.format(date) else ""
        } catch (e: Exception) {
            ""
        }
    }
}