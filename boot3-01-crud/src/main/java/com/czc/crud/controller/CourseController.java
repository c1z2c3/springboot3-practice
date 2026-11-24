package com.czc.crud.controller;

import com.czc.crud.common.Result;
import com.czc.crud.dto.CourseCreateDTO;
import com.czc.crud.entity.Course;
import com.czc.crud.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public Result<Long> create(@Valid @RequestBody CourseCreateDTO dto) {
        return Result.ok(courseService.create(dto));
    }

    @GetMapping("/{id}")
    public Result<Course> detail(@PathVariable Long id) {
        return Result.ok(courseService.detail(id));
    }

    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id,@Valid @RequestBody CourseCreateDTO dto) {
        courseService.update(id, dto);
        return Result.ok(null);
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        courseService.delete(id);
        return Result.ok(null);
    }

}
