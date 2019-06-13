package com.pcb.management.service.impl;

import com.pcb.management.dao.IPCBDao;
import com.pcb.management.model.PCB;
import com.pcb.management.service.IPCBService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : ErGouBigDevil
 * @date : 2019/5/21
 * @time : 10:37
 */
@Service("PCBService")
public class PCBServiceImpl implements IPCBService {

    @Resource
    private IPCBDao PCBDao;

    @Override
    public int insertPCB(PCB pcb) {
        return PCBDao.insert(pcb);
    }

    @Override
    public int deletePCBById(Integer id) {
        return PCBDao.delete(id);
    }

    @Override
    public int updatePCB(PCB pcb) {
        return PCBDao.update(pcb);
    }

    @Override
    public PCB findById(int id) {
        return PCBDao.findById(id);
    }

    @Override
    public List<PCB> findAll(Integer offset,
                             Integer rows, String sort, String order) {
        return PCBDao.findAll(offset, rows, sort, order);
    }

    @Override
    public List<PCB> findAllData() {
        return PCBDao.findAllData();
    }

    @Override
    public List<PCB> fuzzSearch(String keyWord) {
        return PCBDao.fuzzSearch(keyWord);
    }

    @Override
    public List<PCB> accurateSearch(PCB pcb) {
        return PCBDao.accurateSearch(pcb);
    }

    @Override
    public int count() {
        return PCBDao.count();
    }


}
