package com.example.hybriddemo.core.ui.view

import android.view.View
import com.example.hybriddemo.core.ui.dom.DomGroup
import java.util.ArrayList

/**
 * Created by weihansheng.johan on 2023/1/4
 *
 * @author weihansheng.johan@bytedance.com
 */
abstract class JsViewGroup<T : View?, D : DomGroup?> : JsView<T?, D>() {
    var mChildren: List<JsView<*, *>?> = ArrayList()
    fun setChildren(children: List<JsView<*, *>?>) {
        mChildren = children
    }
}