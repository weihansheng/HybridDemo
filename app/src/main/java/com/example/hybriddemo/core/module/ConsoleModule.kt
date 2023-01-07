package com.example.hybriddemo.core.module

import android.util.Log
import com.eclipsesource.v8.V8Array
import java.util.ArrayList

/**
 * Created by weihansheng.johan on 2023/1/5
 *
 * @author weihansheng.johan@bytedance.com
 */
// console.info方法的抽象
class ConsoleModule : JsModule() {
    override val name: String
        get() = "console"
    override val functionNames: List<String>
        get() {
            val functions: MutableList<String> = ArrayList()
            functions.add("info")
            return functions
        }

    override fun execute(functionName: String, params: V8Array): Any? {
        when (functionName) {
            "info" -> Log.i("Javascript Console", params.getString(0))
        }
        return null
    }
}