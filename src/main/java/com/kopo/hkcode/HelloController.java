package com.kopo.hkcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kopo.hkcode.pojo.Hello;

@Controller
public class HelloController {

    // @Autowired 
    // private Hello hello;

    @RequestMapping(value = "/helloTest", method = RequestMethod.GET)
    public String helloTest(Model model) {
        // String result = hello.sayHello();
        // model.addAttribute("msg", result);
        // hello.print();
        return "hellopojo";
    }

    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("message", "Hello World from Spring MVC!");
        return "hellopojo";
    }
}