package com.ctmhoang.userfront.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{
    //Spring known that used ThymeLeaf temp engine -> return automatically recognize as  template name
    @RequestMapping("/")
    public String home()
    {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index()
    {
        return "index";
    }

}
