import com.pcb.management.dao.IPCBDao;
import com.pcb.management.model.PCB;
import com.pcb.management.service.IPCBService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : Haodong Zhao
 * @date : 2019/5/21
 * @time : 10:50
 */


@RunWith(JUnit4ClassRunner.class)// 添加spring测试方案
@ContextConfiguration("/spring-mybatis.xml")// 指定spring配置文件位置
public class PCBTest {
    @Autowired
    IPCBService PCBService;

    @Test
    public void insertPCB(){
        PCB insertPCB = new PCB(0,"TEST","TEST","TEST");
        int rows = PCBService.insertPCB(insertPCB);
        if (rows > 0) {
            System.out.println("您成功添加了" + rows + "条数据！");

        } else {
            System.out.println("执行添加操作失败！！！");
        }

    }

    @Test
    public void findById(){
        PCB pcb =  PCBService.findById(22);
        System.out.println(pcb.toString());
    }

    @Test
    public void deletePCBById(){
        int rows = PCBService.deletePCBById(23);
        if (rows > 0) {
            System.out.println("您成功删除了" + rows + "条数据！");
        } else {
            System.out.println("执行删除操作失败！！！");
        }
        //int i = 1/0;
    }

    @Test
    public void updatePCB(){
        PCB updatePCB = new PCB(23,"test","test","test");
        int rows = PCBService.updatePCB(updatePCB);
        if (rows > 0) {
            System.out.println("您成功修改了" + rows + "条数据！");
        } else {
            System.out.println("执行修改操作失败！！！");
        }
        System.out.println(PCBService.findById(23));
    }

    @Test
    public void findAll(){
        List<PCB> list = PCBService.findAll(0,5,"id", "desc");
        System.out.println(list);
        for(PCB pcb:list)
            System.out.println(pcb);

    }

    @Test
    public void count(){
        System.out.println(PCBService.count());
    }

    @Test
    public void accurateSearch(){
        List<PCB> list = PCBService.accurateSearch(new PCB(0,"TEST","TEST","TEST"));
        for(PCB pcb:list)
            System.out.println(pcb);

    }

    @Test
    public void fuzzSearch(){
        List<PCB> list = PCBService.fuzzSearch("PLC");
        for(PCB pcb:list)
            System.out.println(pcb);

    }

    @Test
    public void findAllData(){
        List<PCB> list = PCBService.findAllData();
        for(PCB pcb:list)
            System.out.println(pcb);
    }

}
