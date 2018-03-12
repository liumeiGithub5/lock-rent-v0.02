package com.yifushidai.webApi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liumei on 2017/6/8 15:58.
 * desc: 设置首页
 */
@Controller
class IndexController {
    @RequestMapping("/index")
    public String index() {
        return "forward:/login.html";
    }
}
