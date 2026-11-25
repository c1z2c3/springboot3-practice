package com.czc.crud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czc.crud.entity.Course;
import com.czc.crud.vo.CourseDetailVO;
import org.apache.ibatis.annotations.Param;

public interface CourseMapper extends BaseMapper<Course> {
    CourseDetailVO selectDetailById(@Param("id")Long id);

}
