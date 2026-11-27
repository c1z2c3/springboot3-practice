package com.czc.crud.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.czc.crud.entity.Category;
import com.czc.crud.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;

    public List<Category> list() {
        return categoryMapper.selectList(
                new LambdaQueryWrapper<Category>().orderByAsc(Category::getSort));
    }
}
