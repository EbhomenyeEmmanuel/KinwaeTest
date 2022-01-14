package com.example.kinwaetest.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Shows a short Toast with a String Parameter.
 */
fun Context.shortToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
/**
 * Shows a short Toast with an Int(Resource Value) Parameter.
 */
fun Context.shortToast(@StringRes msg: Int) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

/**
 * Shows a long Toast with a String Parameter.
 */
fun Context.longToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

/**
 * Shows a long Toast with an Int(Resource Value) Parameter.
 */
fun Context.longToast(@StringRes msg: Int) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

/**
 * Utility method for observing once.
 */
fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T?) {
            observer.onChanged(t)
            removeObserver(this)
        }
    })
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}

/**
 * A generic extension utility method for converting a list to a flow.
 */
fun <T> List<T>.toFlow(): Flow<List<T>> =
    flow {
        while (true) {
            emit(this@toFlow)
            delay(1000)
        }
    }