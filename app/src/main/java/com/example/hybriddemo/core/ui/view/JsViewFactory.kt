package com.example.hybriddemo.core.ui.view

import com.example.hybriddemo.core.ui.dom.DomElement
import com.example.hybriddemo.core.ui.dom.DomGroup
import java.util.ArrayList

/**
 * Created by weihansheng.johan on 2023/1/4
 * 将domelement转为对应的native view
 * @author weihansheng.johan@bytedance.com
 */
//TODO
object JsViewFactory {
    fun create(domElement: DomElement?): JsView<*, *>? {
        val type = domElement?.type
        when (type) {
            "text" -> {
                val jsTextView = JsTextView()
                jsTextView.setDomElement(domElement)
                return jsTextView
            }
            "button" -> {
                val buttonJsView = ButtonJsView()
                buttonJsView.setDomElement(domElement)
                return buttonJsView
            }
            "verticalLayout" -> {
                val verticalLayoutJsView = VerticalLayoutJsView()
                verticalLayoutJsView.setDomElement(domElement)
                if (domElement is DomGroup) {
                    val childrenJsView: MutableList<JsView<*, *>?> = ArrayList()
                    domElement.children?.forEach {  childModel->
                        create(childModel).let {
                            childrenJsView.add(it)
                        }
                    }
                    verticalLayoutJsView.setChildren(childrenJsView)
                }
                return verticalLayoutJsView
            }
        }
        return null
    }
}