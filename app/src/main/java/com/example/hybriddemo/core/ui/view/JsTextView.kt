package com.example.hybriddemo.core.ui.view

import android.content.Context
import android.graphics.Color
import android.widget.TextView
import android.view.Gravity
import com.example.hybriddemo.core.ui.dom.DomText

/**
 * Created by weihansheng.johan on 2023/1/4
 *
 * @author weihansheng.johan@bytedance.com
 */
class JsTextView : JsView<TextView, DomText?>() {
    override val type: String
        get() = "text"

    override fun createViewInternal(context: Context?): TextView {
        val textView = TextView(context)
        textView.gravity = Gravity.CENTER
        textView.text = mDomElement!!.text
        textView.textSize = mDomElement!!.textSize.toFloat()
        textView.setTextColor(Color.parseColor(mDomElement!!.textColor))
        return textView
    }
}