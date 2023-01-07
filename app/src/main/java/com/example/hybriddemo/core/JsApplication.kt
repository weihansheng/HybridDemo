package com.example.hybriddemo.core

import android.content.Context
import android.view.ViewGroup

/**
 * Created by weihansheng.johan on 2023/1/4
 * 整合动态化引擎需要的所有模块
 * @author weihansheng.johan@bytedance.com
 */
class JsApplication {
    private var mJsContext: JsContext? = null
    fun run(jsBundle: JsBundle?) {
        mJsContext?.runApplication(jsBundle!!)
    }

    companion object {
        fun init(context: Context?, containerView: ViewGroup?): JsApplication {
            val jsApplication = JsApplication()
            val jsContext = JsContext()
            jsApplication.mJsContext = jsContext
            RenderManager.instance.init(context, containerView)
            ModuleManager.instance.init(jsContext)
            return jsApplication
        }
    }
}