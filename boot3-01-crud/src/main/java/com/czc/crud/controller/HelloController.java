package com.czc.crud.controller;

import com.czc.crud.common.Result;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/{name}")
    public Result<String> path(@PathVariable String name) {

        return Result.ok("path: " + name);
    }

    @GetMapping
    public Result<String> query(@RequestParam(defaultValue = "world") String name) {
        return Result.ok("query: " + name);
    }

    @PostMapping
    public Result<String> body(@RequestBody Map<String, String> body) {
        return Result.ok("body: " + body.get("name"));
    }
}