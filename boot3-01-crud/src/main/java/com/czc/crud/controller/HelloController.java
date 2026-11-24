package com.czc.crud.controller;

import com.czc.crud.common.BusinessException;
import com.czc.crud.common.ErrorCode;
import com.czc.crud.common.PageResult;
import com.czc.crud.common.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import com.czc.crud.dto.CourseCreateDTO;
import jakarta.validation.Valid;
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
    @PostMapping("/validate")
    public Result<String> validate(@Valid @RequestBody CourseCreateDTO dto) {
        return Result.ok(dto.getTitle());
    }

    @GetMapping("/biz")
    public Result<String> biz(){
        throw new BusinessException(ErrorCode.NOT_FOUND);
    }

    @GetMapping("/boom")
    public Result<String> boom(){
        int zero = 0;
        int x = 1 / zero;
        return Result.ok("never");
    }

    @GetMapping("/page-demo")
    public Result<PageResult<String>> pageDemo(){
        return Result.ok(PageResult.of(List.of("a", "b", "c"), 10, 1, 3));
    }
}