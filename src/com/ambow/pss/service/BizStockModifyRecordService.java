package com.ambow.pss.service;

import com.ambow.pss.model.StockModifyRecord;

import java.util.List;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/9/6 19:22
 */
public interface BizStockModifyRecordService {

    /**
     * 查询所有库存信息
     * @param keyword
     * @return
     */
    List<StockModifyRecord> queryAll(String keyword);

    /**
     * 添加库存变更记录
     * @param s
     * @return
     */
    int addStockModifyRecord(StockModifyRecord s);
}
