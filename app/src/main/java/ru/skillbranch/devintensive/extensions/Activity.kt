package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard(){
    val view = this.getCurrentFocus()
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    if (view!=null)
        imm.hideSoftInputFromWindow(view.windowToken, 0 )
}

fun Activity.isKeyboardOpen(): Boolean{
    // get magic constants etc from https://stackoverflow.com/questions/4745988
    val SOFT_KEYBOARD_HEIGHT_DP_THRESHOLD = 128
    val r = Rect()
    val rootView: View = findViewById(android.R.id.content)
    rootView.getWindowVisibleDisplayFrame(r)
    val dm = rootView.resources.displayMetrics
    val heightDiff = rootView.bottom - r.bottom
    return heightDiff > SOFT_KEYBOARD_HEIGHT_DP_THRESHOLD * dm.density
}

fun Activity.isKeyboardClosed(): Boolean {
    return !isKeyboardOpen()
}