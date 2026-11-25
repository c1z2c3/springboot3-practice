package com.czc.crud.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@TableName("t_lesson")
public class Lesson {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long courseId;
    private String title;
    private Integer sort;
    private Integer durationSec;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    @TableLogic
    private Integer deleted;
}
