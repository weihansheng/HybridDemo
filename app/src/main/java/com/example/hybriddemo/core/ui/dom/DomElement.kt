package com.example.hybriddemo.core.ui.dom

import com.eclipsesource.v8.V8Function
import com.eclipsesource.v8.V8Object

/**
 * Created by weihansheng.johan on 2023/1/4
 *
 * @author weihansheng.johan@bytedance.com
 */
open class DomElement {
    @JvmField
    var type: String? = null
    var marginTop = 0
    var marginBottom = 0
    var marginLeft = 0
    var marginRight = 0
    @JvmField
    var onClick: V8Function? = null
    open fun parse(v8Object: V8Object) {
        // 公共参数
        for (key in v8Object.keys) {
            when (key) {
                "type" -> type = v8Object.getString("type")
                "marginTop" -> marginTop = v8Object.getInteger("marginTop")
                "marginBottom" -> marginBottom = v8Object.getInteger("marginBottom")
                "marginLeft" -> marginLeft = v8Object.getInteger("marginLeft")
                "marginRight" -> marginRight = v8Object.getInteger("marginRight")
                "onClick" -> onClick = v8Object["onClick"] as V8Function
                else -> {}
            }
        }
    }

}