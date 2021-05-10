package com.ambow.pss.service;

import com.ambow.pss.model.Category;

import java.util.List;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/9/5 16:39
 */
public interface BizCategoryService {
    /**
     * 查询所有类别
     * @param keyword 名称模糊查询
     * @return
     */
    List<Category> queryAll(String keyword);

    /**
     * 查询类别
     * @param id
     * @return 某个类别
     */
    Category queryById(int id);

    /**
     * 插入类别
     * @param c 类别
     * @return
     */
    int addCategory(Category c);

    /**
     * 更新类别
     * @param c
     * @return
     */
    int update(Category c);

    /**
     * （逻辑）删除类别
     * @param id
     * @return
     */
    int delete(int id);
}
