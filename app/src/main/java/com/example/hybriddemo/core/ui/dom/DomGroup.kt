package com.example.hybriddemo.core.ui.dom

import com.eclipsesource.v8.V8Object
import java.util.ArrayList

/**
 * Created by weihansheng.johan on 2023/1/4
 *
 * @author weihansheng.johan@bytedance.com
 */
open class DomGroup : DomElement() {
    @JvmField
    var children: List<DomElement?>? = null
    override fun parse(v8Object: V8Object) {
        super.parse(v8Object)
        val childElements: MutableList<DomElement?> = ArrayList()
        val childrenObj = v8Object.getArray("children")
        for (i in 0 until childrenObj.length()) {
//            childElements.add(DomFactory.create(childrenObj.getObject(i))) // 手动写工厂模式
            childElements.add(DomElementFactory.create(childrenObj.getObject(i))) //apt生成工厂模式
            children = childElements
        }
    }
}