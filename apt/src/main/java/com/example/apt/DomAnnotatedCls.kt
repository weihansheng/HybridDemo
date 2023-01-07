package com.example.hybriddemo.apt

import com.example.apt.DomFactory
import javax.lang.model.element.TypeElement
import javax.lang.model.type.DeclaredType
import javax.lang.model.type.MirroredTypeException

// 用于保存获取到的每个类相关的属性
class DomAnnotatedCls(  //被注解类元素
    val annotatedClsElement: TypeElement
) {
    //被注解的类的父类的完全限定名称（即类的绝对路径）
    var supperClsQualifiedName: String? = null
    //被注解类的父类类名
    var supperClsSimpleName: String? = null
    //被注解的类的对应的type
    var type: String? = null

    init {
        val annotation = annotatedClsElement.getAnnotation(
            DomFactory::class.java
        )
        type = annotation.type
        try {
            //直接获取Factory中的supperClass参数的类名和完全限定名字，如果是源码上的注解，会抛异常
            supperClsSimpleName = annotation.superClass.simpleName
            supperClsQualifiedName = annotation.superClass.qualifiedName
        } catch (mte: MirroredTypeException) {
            //如果获取异常，通过mte可以获取到上面无法解析的superClass元素
            val classTypeMirror = mte.typeMirror as DeclaredType
            val classTypeElement = classTypeMirror.asElement() as TypeElement
            supperClsQualifiedName = classTypeElement.qualifiedName.toString()
            supperClsSimpleName = classTypeElement.simpleName.toString()
        }
        if (type == null || type == "") { //判断是否存在ID，不存在则抛出异常
            throw ProcessingException(
                annotatedClsElement,
                "type() in @%s for class %s is null or empty! that's not allowed",
                DomFactory::class.java.simpleName, annotatedClsElement.qualifiedName.toString()
            )
        }
        if (supperClsSimpleName == null || supperClsSimpleName === "") { //判断是否存在父类接口，不存在抛出异常
            throw ProcessingException(
                annotatedClsElement,
                "superClass() in @%s for class %s is null or empty! that's not allowed",
                DomFactory::class.java.simpleName, annotatedClsElement.qualifiedName.toString()
            )
        }
    }
}