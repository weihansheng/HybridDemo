package com.example.hybriddemo.apt

import java.lang.Exception
import javax.lang.model.element.Element

/**
 * 注解解析异常
 */
class ProcessingException(var element: Element, msg: String?, vararg args: Any?) : Exception(
    String.format(msg ?: "", *args)
)