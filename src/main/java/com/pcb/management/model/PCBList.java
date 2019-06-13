package com.pcb.management.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;

/**
 * @author : ErGouBigDevil
 * @date : 2019/5/24
 * @time : 21:31
 */
public class PCBList {
    private int total;
    private List<PCB> rows;

    public List<PCB> getRows() {
        return rows;
    }

    public void setRows(List<PCB> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteDateUseDateFormat);
    }
}
