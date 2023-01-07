package com.example.apt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by weihansheng.johan on 2023/1/7
 *
 * @author weihansheng.johan@bytedance.com
 */
@Retention(RetentionPolicy.CLASS) //该注解只保留到编译时
@Target(ElementType.TYPE) //该注解只作用与类、接口、枚举
public @interface DomFactory {
    String type();
    Class superClass();
}
