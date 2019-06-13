package com.pcb.management.dao;

import com.pcb.management.model.PCB;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : ErGouBigDevil
 * @date : 2019/5/21
 * @time : 9:55
 */
public interface IPCBDao {
    int insert(PCB pcb);

    int delete(Integer id);

    int update(PCB pcb);

    PCB findById(int id);

    List<PCB> findAll(@Param(value = "offset") Integer offset,
                      @Param(value = "rows") Integer rows,
                      @Param(value = "sort") String sort,
                      @Param(value = "order") String order);

    List<PCB> findAllData();

    List<PCB> fuzzSearch(String keyWord);
    List<PCB> accurateSearch(PCB pcb);

    int count();

}
