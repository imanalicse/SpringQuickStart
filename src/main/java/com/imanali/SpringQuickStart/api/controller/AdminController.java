package com.imanali.SpringQuickStart.api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    @GetMapping
    public String get() {
        return "Get::Admin controller";
    }
    @PostMapping
    public String post() {
        return "Post::Admin controller";
    }
    @PutMapping
    public String put() {
        return "Put::Admin controller";
    }
    @DeleteMapping
    public String delete() {
        return "Delete::Admin controller";
    }
}
