package uz.javokhirdev.geometrycalculator.helpers

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

fun <T> FragmentActivity.start(it: Class<T>) {
    val intent = Intent(this, it)
    startActivity(intent)
}

fun Activity.link(url: String) = intentData(Intent.ACTION_VIEW, url)

fun Activity.intentData(action: String, uriString: String) {
    try {
        val intent = Intent(action)
        intent.data = Uri.parse(uriString)
        startActivity(intent)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}

fun Activity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow((currentFocus ?: View(this)).windowToken, 0)
}

fun View.beVisibleIf(isVisible: Boolean) = if (isVisible) beVisible() else beGone()

fun View.beVisible() {
    isVisible = true
}

fun View.beGone() {
    isVisible = false
}

fun View.backTint(color: Int) {
    backgroundTintList = ContextCompat.getColorStateList(context, color)
}

fun RecyclerView.flex(): RecyclerView {
    val flexboxLayoutManager = FlexboxLayoutManager(context)
    flexboxLayoutManager.flexDirection = FlexDirection.ROW
    flexboxLayoutManager.justifyContent = JustifyContent.FLEX_START

    isFocusable = false
    isNestedScrollingEnabled = false
    setHasFixedSize(false)
    layoutManager = flexboxLayoutManager

    return this
}