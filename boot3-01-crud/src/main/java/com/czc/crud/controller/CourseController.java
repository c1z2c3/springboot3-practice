package com.czc.crud.controller;

import com.czc.crud.common.PageResult;
import com.czc.crud.common.Result;
import com.czc.crud.dto.CourseCreateDTO;
import com.czc.crud.entity.Course;
import com.czc.crud.service.CourseService;
import com.czc.crud.vo.CourseDetailVO;
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

    @GetMapping
    public Result<PageResult<Course>> page(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "created_at") String sortBy,
            @RequestParam(defaultValue = "desc") String dir) {
        return Result.ok(courseService.page(page, size, status, keyword, sortBy, dir));
    }

    @GetMapping("/{id}/detail")
    public Result<CourseDetailVO> detailVo(@PathVariable Long id) {

        return Result.ok(courseService.detailVo(id));
    }



    @GetMapping("/{id}/detail-join")
    public Result<CourseDetailVO> detailVoByJoin(@PathVariable Long id) {
        return Result.ok(courseService.detailVoByJoin(id));
    }



}
