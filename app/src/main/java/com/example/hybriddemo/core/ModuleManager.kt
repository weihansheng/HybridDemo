package com.example.hybriddemo.core

import android.util.Log
import com.eclipsesource.v8.V8Object
import com.eclipsesource.v8.JavaCallback
import com.eclipsesource.v8.V8Array
import com.example.hybriddemo.core.module.ConsoleModule
import com.example.hybriddemo.core.module.JsModule
import com.example.hybriddemo.core.module.UiModule
import java.util.ArrayList

/**
 * Created by weihansheng.johan on 2023/1/4
 *
 * @author weihansheng.johan@bytedance.com
 */
class ModuleManager{

    companion object {
        val instance = ModuleManagerHolder.holder
    }

    private object ModuleManagerHolder {
        val holder= ModuleManager()
    }

    private val mModuleList: MutableList<JsModule> = ArrayList()
    private var mJsContext: JsContext? = null
    fun init(jsContext: JsContext?) {
        mJsContext = jsContext
        mModuleList.add(UiModule())
        mModuleList.add(ConsoleModule())
        registerModules()
    }

    private fun registerModules() {
        for (module in mModuleList) {
            val moduleObj = V8Object(mJsContext!!.getEngine())
            for (functionName in module.functionNames) {
                moduleObj.registerJavaMethod(JavaCallback { v8Object: V8Object, params: V8Array ->
                    Log.d("johantest", "execute fun:$functionName")
                    module.execute(functionName, params)
                }, functionName)
            }
            mJsContext!!.getEngine().add(module.name, moduleObj)
        }
    }

}