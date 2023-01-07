package com.example.hybriddemo

import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.eclipsesource.v8.V8
import com.example.hybriddemo.core.JsApplication
import com.example.hybriddemo.core.JsBundle


class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = "johantest"
        val JS_CODE =  "const hello = \"Hello \";\n" +
                "const title = hello + \"Hybrid World\"\n" +
                "\$view.render({\n" +
                "    rootView: {\n" +
                "        type: \"verticalLayout\",\n" +
                "        children: [\n" +
                "            {\n" +
                "                \"type\": \"text\",\n" +
                "                \"text\": title,\n" +
                "                \"textSize\": 24,\n" +
                "                \"textColor\": \"#ff0000\",\n" +
                "                \"marginTop\": 32\n" +
                "            },\n" +
                "            {\n" +
                "                \"type\": \"button\",\n" +
                "                \"text\": \"点击打印日志\",\n" +
                "                \"marginTop\": 80,\n" +
                "                \"marginLeft\": 40,\n" +
                "                \"marginRight\": 40,\n" +
                "                \"onClick\": function () {\n" +
                "                    console.info(\"success!\")\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "})";

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val runtime = V8.createV8Runtime()
//        val console = V8Object(runtime)
//        console.registerJavaMethod(JavaCallback { v8Object: V8Object?, params: V8Array ->
//            val msg = params.getString(0)
//            Log.i(TAG, msg)
//            null
//        }, "info")
//        runtime.add("console", console)
        val containerView = findViewById<FrameLayout>(R.id.js_container_view)
        var jsBundle = JsBundle().apply {
            appJavaScript = JS_CODE
        }
        val jsApplication = JsApplication.init(this, containerView)

        findViewById<Button>(R.id.btn_start).setOnClickListener {
//            val cost = measureTimeMillis {
//                var result = runtime.executeIntegerScript("var i=0; i++; i")
//                println("johantest result:$result")
//                runtime.executeVoidScript("function add(a, b) { return a + b }");
//                var args = V8Array(runtime).push(1).push(2)
//                var result2 = runtime.executeIntegerFunction("add", args)
//                println("johantest result2:$result2")
//
//                var jsBundle = JsBundle().apply {
//                    appJavaScript = "'hello, world'"
//                }
//                var jsContext = JsContext()
//                jsContext.runApplication(jsBundle)
//            }
//            println("johantest cost:$cost")

            jsApplication.run(jsBundle)

        }

    }
}