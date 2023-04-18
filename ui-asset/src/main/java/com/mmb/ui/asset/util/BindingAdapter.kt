package com.mmb.ui.asset.util

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import coil.load
import com.google.android.material.appbar.MaterialToolbar
import com.mmb.ui.asset.R
import com.mmb.ui.asset.model.AssetSummary
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@BindingAdapter("loadImage")
fun ImageView.loadImage(url: String?) {
    load(url) {
        placeholder(R.drawable.baseline_circle_24)
        error(R.drawable.baseline_circle_24)
    }
}

@BindingAdapter("goneUnless")
fun View.goneUnless(value: Boolean) {
    isGone = !value
}

@BindingAdapter("favoriteIcon")
fun ImageButton.favoriteIcon(isFavorite: Boolean) {
    if (isFavorite)
        load(R.drawable.baseline_favorite_24)
    else
        load(R.drawable.baseline_favorite_border_24)
}

@BindingAdapter("assetName")
fun TextView.assetName(asset: AssetSummary) {
    text = buildString {
        append(asset.id)
        append(" ")
        append("(${asset.name})")
    }
}

@BindingAdapter("isLoading")
fun SwipeRefreshLayout.isLoading(value: Boolean) {
    isRefreshing = value
}

@BindingAdapter("isSearchEnable")
fun MaterialToolbar.isSearchEnable(value: Boolean) {
    menu.findItem(R.id.favorite).isVisible = !value
    menu.findItem(R.id.more).isVisible = !value
}

@BindingAdapter("isFavoriteEnable")
fun MaterialToolbar.isFavoriteEnable(value: Boolean) {
    val icon =
        if (value) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24
    menu.findItem(R.id.favorite).setIcon(icon)
}

@BindingAdapter("setLastFetchedData")
fun TextView.setLastFetchedData(value: Long) {
    val time = if (value > 1) {
        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault())
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = value
        formatter.format(calendar.time)
    } else {
        context.getString(R.string.never)
    }

    text = context.getString(R.string.last_fetch_data, time)
}
