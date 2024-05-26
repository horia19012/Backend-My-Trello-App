package com.example.TaskManagement.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ContentController {


    @GetMapping("/")
    @Secured("ROLE_USER")
    public String home() {
        return ("<h1>Welcome<h1>");
    }

    @GetMapping("/user")
    public String user() {
        return ("<h1>Welcome USER!<h1>");

    }

    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome ADMIN!<h1>");

    }
}
