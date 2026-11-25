package com.czc.crud.vo;

import com.czc.crud.entity.Lesson;
import lombok.Data;

@Data
public class LessonVO {

    private Long id;
    private String title;
    private Integer sort;
    private Integer durationSec;

    public static LessonVO from(Lesson l) {
        LessonVO vo = new LessonVO();
        vo.setId(l.getId());
        vo.setTitle(l.getTitle());
        vo.setSort(l.getSort());
        vo.setDurationSec(l.getDurationSec());
        return vo;
    }
}