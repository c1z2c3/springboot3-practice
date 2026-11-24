package com.czc.crud.service;


import com.czc.crud.common.BusinessException;
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
}
