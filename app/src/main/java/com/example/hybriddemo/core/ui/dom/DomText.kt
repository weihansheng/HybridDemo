package com.example.hybriddemo.core.ui.dom

import com.eclipsesource.v8.V8Object
import android.text.TextUtils

/**
 * Created by weihansheng.johan on 2023/1/4
 *
 * @author weihansheng.johan@bytedance.com
 */
@com.example.apt.DomFactory(type = "text", superClass = DomElement::class)
class DomText : DomElement() {
    @JvmField
    var text: String? = null
    @JvmField
    var textSize = 0
    @JvmField
    var textColor = "#000000"
    override fun parse(v8Object: V8Object) {
        super.parse(v8Object)
        for (key in v8Object.keys) {
            when (key) {
                "text" -> text = v8Object.getString("text")
                "textSize" -> {
                    var textSize = v8Object.getInteger("textSize")
                    if (textSize == 0) textSize = 16
                    this.textSize = textSize
                }
                "textColor" -> {
                    var textColor = v8Object.getString("textColor")
                    if (TextUtils.isEmpty(textColor)) {
                        textColor = "#000000"
                    }
                    this.textColor = textColor
                }
            }
        }
    }
}