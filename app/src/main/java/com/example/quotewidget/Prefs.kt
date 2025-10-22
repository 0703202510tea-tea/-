package com.example.quotewidget

import android.content.Context
import android.content.SharedPreferences

private const val PREF_NAME = "quote_prefs"
private const val KEY_TEXT = "widget_text"

object Prefs {
    private fun prefs(ctx: Context): SharedPreferences =
        ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveText(ctx: Context, text: String) {
        prefs(ctx).edit().putString(KEY_TEXT, text).apply()
    }

    fun getText(ctx: Context): String =
        prefs(ctx).getString(KEY_TEXT, "点击编辑设置句子") ?: "点击编辑设置句子"
}
