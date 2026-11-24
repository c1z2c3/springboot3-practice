package com.czc.crud.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;

import java.time.LocalDateTime;


@Data
@TableName("t_lession")
public class Lession {

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
