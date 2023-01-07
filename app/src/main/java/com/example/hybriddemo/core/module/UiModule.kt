package com.example.hybriddemo.core.module

import android.util.Log
import com.eclipsesource.v8.V8Array
import com.example.hybriddemo.core.RenderManager
import java.util.ArrayList

/**
 * Created by weihansheng.johan on 2023/1/4
 *
 * @author weihansheng.johan@bytedance.com
 */
class UiModule : JsModule() {
    override val name: String
        get() = "\$view"
    override val functionNames: List<String>
        get() {
            val functionNames: MutableList<String> = ArrayList()
            functionNames.add("render")
            return functionNames
        }

    override fun execute(functionName: String, params: V8Array): Any? {
        when (functionName) {
            "render" -> {
                Log.d("UiModule", "render")
                val param1 = params.getObject(0)
                val rootViewObj = param1.getObject("rootView")
                RenderManager.instance.render(rootViewObj)
            }
        }
        return null
    }
}