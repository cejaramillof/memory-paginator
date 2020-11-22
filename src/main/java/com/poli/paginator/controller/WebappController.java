package com.poli.paginator.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class WebappController {

    @Value("${web.titlesite}")
    private String titlesite = "Hello World";

    @RequestMapping("paginator")
    public String paginator(Map<String, Object> model) {
        System.out.println("Ingresando al controlador web");
        model.put("titlesite", this.titlesite);
        return "paginator";
    }
}