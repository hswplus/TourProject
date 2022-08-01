package edu.ln.tour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
@CrossOrigin
@Controller
@RequestMapping("/travel")
public class IndexController {
    @RequestMapping("/pages")
    public String index() {
        //跳转到index.html页面
        return "travel/pages/index";
    }
}