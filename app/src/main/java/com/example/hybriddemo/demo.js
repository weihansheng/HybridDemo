const hello = "Hello ";
const title = hello + "Hybrid World!"
$view.render({
    rootView: {
        type: "verticalLayout",
        children: [
            {
                "type": "text",
                "text": title,
                "textSize": 24,
                "marginTop": 16
            },
            {
                "type": "button",
                "text": "点击打印日志",
                "marginTop": 80,
                "marginLeft": 40,
                "marginRight": 40,
                "onClick": function () {
                    console.info("success!")
                }
            }
        ]
    }
})