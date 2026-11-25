package com.czc.crud.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CourseDetailVO {

    private Long id;
    private String title;
    private BigDecimal price;
    private Integer status;
    private LocalDateTime createdAt;

    private String categoryName;
    private List<LessonVO> lessons;
}
