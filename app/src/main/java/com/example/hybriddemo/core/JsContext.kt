package com.example.hybriddemo.core

import com.eclipsesource.v8.V8

/**
 * Created by weihansheng.johan on 2023/1/4
 * @author weihansheng.johan@bytedance.com
 */
class JsContext {
    private val mEngine:V8 by lazy {
        V8.createV8Runtime()
    }

    fun getEngine() :V8 {
        return mEngine
    }
    fun runApplication(jsBundle: JsBundle) {
        val result = mEngine.executeStringScript(jsBundle.appJavaScript)
    }


}