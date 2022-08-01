package edu.ln.tour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminIndexController {
    @RequestMapping("/pages")
    public String main() {
        //跳转到main.html页面
        return "admin/pages/main";
    }
}
