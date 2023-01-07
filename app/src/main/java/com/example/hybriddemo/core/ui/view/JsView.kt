package com.example.hybriddemo.core.ui.view

import android.content.Context
import android.view.View
import com.example.hybriddemo.core.ui.dom.DomElement

/**
 * Created by weihansheng.johan on 2023/1/4
 *
 * @author weihansheng.johan@bytedance.com
 */
//抽象基类 将DomElement与Android的View一一对应起来
abstract class JsView<V : View?, D : DomElement?> {
    protected var mDomElement: D? = null
    protected var mNativeView: V? = null
    fun setDomElement(domElement: DomElement) {
        mDomElement = domElement as D
    }

    abstract val type: String
    abstract fun createViewInternal(context: Context?): V
    open fun createView(context: Context?): V {
        val view = createViewInternal(context)
        mNativeView = view
        return view
    }
}