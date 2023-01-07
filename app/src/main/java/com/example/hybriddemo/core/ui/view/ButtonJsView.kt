package com.example.hybriddemo.core.ui.view

import android.content.Context
import android.view.View
import android.widget.Button
import com.example.hybriddemo.core.ui.dom.DomButton

/**
 * Created by weihansheng.johan on 2023/1/4
 *
 * @author weihansheng.johan@bytedance.com
 */
class ButtonJsView : JsView<Button, DomButton>() {
    override val type: String
        get() = "button"

    override fun createViewInternal(context: Context?): Button {
        val button = Button(context)
        button.setOnClickListener { v: View? ->
            mDomElement?.onClick?.call(
                mDomElement?.onClick?.runtime, null
            )
        }
        button.text = mDomElement?.text ?: ""
        button.textSize = 16f
        return button
    }
}