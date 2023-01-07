package com.example.hybriddemo.core

import android.content.Context
import android.view.ViewGroup
import com.eclipsesource.v8.V8Object
import com.example.hybriddemo.core.ui.dom.DomElementFactory
import com.example.hybriddemo.core.ui.dom.DomFactory
import com.example.hybriddemo.core.ui.view.JsViewFactory


/**
 * Created by weihansheng.johan on 2023/1/4
 * 管理DSL的解析、DomElement树的创建、JsView树的创建和Native View的渲染
 * @author weihansheng.johan@bytedance.com
 */
class RenderManager {
    companion object {
        val instance = RenderManagerHolder.holder
    }
    private object RenderManagerHolder {
        val holder= RenderManager()
    }


    private var mContext: Context? = null
    private var mContainerView: ViewGroup? = null
    fun init(context: Context?, containerView: ViewGroup?) {
        mContext = context
        mContainerView = containerView
    }

    fun render(rootViewObj: V8Object) {
        // js to dom
//        val rootDomElement = DomFactory.create(rootViewObj)
        val rootDomElement = DomElementFactory.create(rootViewObj)
        // dom to jsview
        val rootJsView = JsViewFactory.create(rootDomElement)
        if (rootJsView != null) {
            // jsview to view
            val rootView = rootJsView.createView(mContext)
            mContainerView!!.addView(rootView)
        }
    }


}