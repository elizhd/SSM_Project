package com.pcb.management.service;

import com.pcb.management.model.PCB;

import java.util.List;

/**
 * @author : ErGouBigDevil
 * @date : 2019/5/21
 * @time : 10:36
 */
public interface IPCBService {
    int insertPCB(PCB pcb);

    int deletePCBById(Integer id);

    int updatePCB(PCB pcb);

    PCB findById(int id);
    
    List<PCB> findAll(Integer offset, Integer rows,String sort, String order);
    List<PCB> findAllData();

    List<PCB> fuzzSearch(String keyWord);

    List<PCB> accurateSearch(PCB pcb);

    int count();
}
