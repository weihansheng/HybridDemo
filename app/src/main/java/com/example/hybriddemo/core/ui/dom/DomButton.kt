package com.example.hybriddemo.core.ui.dom

import com.eclipsesource.v8.V8Object

/**
 * Created by weihansheng.johan on 2023/1/4
 *
 * @author weihansheng.johan@bytedance.com
 */
@com.example.apt.DomFactory(type = "button", superClass = DomElement::class)
class DomButton : DomElement() {
    @JvmField
    var text: String? = null
    override fun parse(v8Object: V8Object) {
        super.parse(v8Object)
        for (key in v8Object.keys) {
            when (key) {
                "text" -> text = v8Object.getString("text")
            }
        }
    }
}