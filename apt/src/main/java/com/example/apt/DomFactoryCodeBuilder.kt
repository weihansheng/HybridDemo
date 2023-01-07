package com.example.hybriddemo.apt


import kotlin.Throws
import com.squareup.javapoet.TypeSpec
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.TypeName
import com.squareup.javapoet.CodeBlock
import java.io.IOException
import java.util.LinkedHashMap
import javax.annotation.processing.Filer
import javax.annotation.processing.Messager
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.tools.Diagnostic

// JavaPoet https://blog.csdn.net/IO_Field/article/details/89355941
//用于生成dom工厂类代码
class DomFactoryCodeBuilder {
    private var mSupperClsName: String? = null
    private val mAnnotatedClasses: MutableMap<String, DomAnnotatedCls?> = LinkedHashMap()
    fun add(annotatedCls: DomAnnotatedCls) {
        if (mAnnotatedClasses[annotatedCls.annotatedClsElement.qualifiedName.toString()] != null) {
            return
        }
        mAnnotatedClasses[annotatedCls.annotatedClsElement.qualifiedName.toString()] = annotatedCls
    }

    fun clear() {
        mAnnotatedClasses.clear()
    }

    fun setSupperClaName(className: String?): DomFactoryCodeBuilder {
        mSupperClsName = className
        return this
    }

    @Throws(IOException::class)
    fun generateCode(messager: Messager, elements: Elements, filer: Filer?) {
        messager.printMessage(Diagnostic.Kind.NOTE, "generateCode start")
        val supperClassName = elements.getTypeElement(mSupperClsName) //supperClassName即DomElement
        val factoryClassName = supperClassName.simpleName.toString() + SUFFIX
        val pkg = elements.getPackageOf(supperClassName)
        val packageName = if (pkg.isUnnamed) null else pkg.qualifiedName.toString()
        //声明类
        val typeSpec = TypeSpec.classBuilder(factoryClassName)
            .addModifiers(Modifier.PUBLIC)
            .addMethod(newCreateMethod(messager, elements, supperClassName)) //声明create方法
            .build()
        messager.printMessage(Diagnostic.Kind.NOTE, "writeTo")
        //生产工厂类文件
        JavaFile.builder(packageName, typeSpec).build().writeTo(filer)
        messager.printMessage(Diagnostic.Kind.NOTE, "generateCode end")
    }

    private fun newCreateMethod(
        messager: Messager,
        elements: Elements,
        supperClassName: TypeElement
    ): MethodSpec {
        val v8Obj = ClassName.get("com.eclipsesource.v8", "V8Object")
        val method =
            MethodSpec.methodBuilder("create") //设置方法名.addModifiers(Modifier.PUBLIC, Modifier.STATIC)// 设置方法为静态的public
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC) //设置方法类型为public static
                .addParameter(v8Obj, "rootObj") //设置参数
                .returns(TypeName.get(supperClassName.asType())) //设置返回值类型为IFruit
        val codeBlock = CodeBlock.builder()
            .add("\$T type = rootObj.getString(\$S);\n", String::class.java, "type")
            .build()
        method.addCode(codeBlock)
        //        method.addStatement("$T type = rootObj.getString($S)", String.class, "type"); //可用Statement代替codeblock
        for (annotatedCls in mAnnotatedClasses.values) {
            val packName = elements.getPackageOf(annotatedCls!!.annotatedClsElement)
                .qualifiedName.toString() //获取Dom类的全路径包名
            val className = annotatedCls.annotatedClsElement.simpleName.toString() //获取Dom类名字
            messager.printMessage(Diagnostic.Kind.NOTE, "process annotation for $className")
            val domClass = ClassName.get(packName, className) //组装生一个完整的ClassName

            //获取注解中的type
            val type = annotatedCls.type

            //$S 和String.format中%s一样
            // $T typeName，该模板主要将Class抽象出来，用传入的TypeName指向的Class来代替。
            // $N 用于方法名或者变量名替换，也可用于类名，但是不会自动生成import；
            method.beginControlFlow("if (\$S.equals(type))", type) //开始一个控制流，判断该生产线类是否包含了指定的id
                .addStatement("\$T dom = new \$T()", domClass, domClass)
                .addStatement("dom.parse(rootObj)")
                .addStatement("return dom")
                .endControlFlow()
        }
        method.addStatement("return null")
        return method.build()
    }

    companion object {
        private const val SUFFIX = "Factory"
    }
}