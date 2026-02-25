package com.scaler.ProductServiceDemo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//This calls will be hosting a set of HTTP API's
@RestController
@RequestMapping("/say")
public class SampleController {

    @GetMapping("/hello/{name}/{times}")
    public String sayHello(@PathVariable("name") String name, @PathVariable("times") int times) {
        String names = "";
        while (times != 0) {
            names += "Hello " + name + " ";
            times--;
        }
        System.out.println(names);
        return names;
    }

    @GetMapping("/bye")
    public String sayBye() {
        return "Bye Everyone!";
    }
}


// start.spring.io --> to create spring boot application for free