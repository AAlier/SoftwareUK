package com.softwareuk.ui

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.softwareuk.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replaceFragment(MainFragment())
    }
}

fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    @IdRes layoutId: Int = android.R.id.content,
    addToBackStack: Boolean = true,
    tag: String = fragment::class.java.name
) {
    supportFragmentManager
        .beginTransaction()
        .replace(layoutId, fragment, tag)
        .apply { if (addToBackStack) addToBackStack(tag) }
        .commit()
}

fun Fragment.replaceFragment(
    fragment: Fragment,
    @IdRes layoutId: Int = android.R.id.content,
    addToBackStack: Boolean = true,
    tag: String = fragment::class.java.name
) {
    requireActivity().supportFragmentManager
        .beginTransaction()
        .replace(layoutId, fragment, tag)
        .apply { if (addToBackStack) addToBackStack(tag) }
        .commit()
}