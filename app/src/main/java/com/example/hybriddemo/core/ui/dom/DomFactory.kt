package com.example.hybriddemo.core.ui.dom

import com.eclipsesource.v8.V8Object

/**
 * Created by weihansheng.johan on 2023/1/4
 * 将标签转为对应domelement
 * @author weihansheng.johan@bytedance.com
 */
//apt中实验注解生成本工厂类
object DomFactory {
    @JvmStatic
    fun create(rootObj: V8Object): DomElement? {
        val type = rootObj.getString("type")
        when (type) {
            "text" -> {
                val domText = DomText()
                domText.parse(rootObj)
                return domText
            }
            "button" -> {
                val domButton = DomButton()
                domButton.parse(rootObj)
                return domButton
            }
            "verticalLayout" -> {
                val domVerticalLayout = DomVerticalLayout()
                domVerticalLayout.parse(rootObj)
                return domVerticalLayout
            }
        }
        return null
    }
}