package uz.javokhirdev.geometrycalculator.helpers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.SyncStateContract
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import uz.javokhirdev.geometrycalculator.R
import kotlin.math.pow
import kotlin.math.roundToInt

fun <T> FragmentActivity.start(it: Class<T>) {
    val intent = Intent(this, it)
    startActivity(intent)
}

fun Activity.link(url: String) = intentData(Intent.ACTION_VIEW, url)

fun Activity.share() {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain"
    shareIntent.putExtra(Intent.EXTRA_TEXT, PLAY_STORE)
    startActivity(shareIntent)
}

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

fun TextView.color(colorId: Int) = setTextColor(ContextCompat.getColor(context, colorId))

fun TextView.colorOf(full: String, substring: String) {
    try {
        val start = full.indexOf(substring)

        val spannable = SpannableString(full)
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorGray600)),
            start,
            start + substring.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        text = spannable
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun EditText.onTextChanged(block: String.() -> Unit) =
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            block(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

fun Int.round(): String = toInt().toString()
fun Int.pow(num: Int = 2): Int = toDouble().pow(num).roundToInt()

fun Double.round(): String {
    var multiplier = 1.0
    repeat(2) { multiplier *= 10 }
    val num = kotlin.math.round(this * multiplier) / multiplier
    return num.toString()
}