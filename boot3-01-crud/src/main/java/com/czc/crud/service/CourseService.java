package com.czc.crud.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czc.crud.common.BusinessException;
import com.czc.crud.common.PageResult;
import com.czc.crud.dto.CourseCreateDTO;
import com.czc.crud.entity.Course;
import com.czc.crud.mapper.CourseMapper;
import com.czc.crud.common.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseMapper courseMapper;

    public Long create(CourseCreateDTO dto) {
        Course c = new Course();
        c.setCategoryId(dto.getCategoryId());
        c.setTitle(dto.getTitle());
        c.setPrice(dto.getPrice());
        c.setStatus(dto.getStatus());
        courseMapper.insert(c);
        return c.getId();
    }

    public Course detail(Long id){
        Course c = courseMapper.selectById(id);
        if (c == null){
            throw new BusinessException(ErrorCode.NOT_FOUND);
        }
        return c;
    }

    public void update(Long id, CourseCreateDTO dto) {
        Course c = detail(id);
        c.setCategoryId(dto.getCategoryId());
        c.setTitle(dto.getTitle());
        c.setPrice(dto.getPrice());
        c.setStatus(dto.getStatus());
        courseMapper.updateById(c);

    }

    public void delete(Long id){
        detail(id);
        courseMapper.deleteById(id);
    }

    public PageResult<Course> page(int page, int size, Integer status, String keyword,
    String sortBy, String dir) {
        int safePage = Math.max(page, 1);
        int safeSize = Math.min(Math.max(size, 1), 50);
        LambdaQueryWrapper<Course> w = new LambdaQueryWrapper<Course>()
                .eq(status != null, Course::getStatus, status)
                .like(keyword != null && !keyword.isBlank(), Course::getTitle, keyword);

        boolean asc = "asc".equalsIgnoreCase(dir);
        switch(sortBy == null?"" : sortBy) {
            case "price" -> w.orderBy(true, asc, Course::getPrice);
            case "id" -> w.orderBy(true, asc, Course::getId);
            default -> w.orderBy(true, asc, Course::getCreatedAt);

        }
        Page<Course> p = courseMapper.selectPage(new Page<>(safePage, safeSize), w);
        return PageResult.of(p);
    }
}
