package com.example.hybriddemo.core.module

import com.eclipsesource.v8.V8Array

/**
 * Created by weihansheng.johan on 2023/1/4
 *
 * @author weihansheng.johan@bytedance.com
 */
abstract class JsModule {
    abstract val name: String
    abstract val functionNames: List<String>
    abstract fun execute(functionName: String, params: V8Array): Any?
}