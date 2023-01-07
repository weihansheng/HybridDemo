package com.example.hybriddemo.core.ui.view

import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import com.example.hybriddemo.core.ui.dom.DomVerticalLayout

/**
 * Created by weihansheng.johan on 2023/1/4
 *
 * @author weihansheng.johan@bytedance.com
 */
class VerticalLayoutJsView : JsViewGroup<LinearLayout?, DomVerticalLayout?>() {
    override val type: String
        get() = "verticalLayout"

    override fun createViewInternal(context: Context?): LinearLayout {
        val linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.gravity = Gravity.CENTER_HORIZONTAL

        for (jsView in mChildren) {
            jsView?.createView(context)?.let {
                linearLayout.addView(it)
            }

        }
        return linearLayout
    }
}