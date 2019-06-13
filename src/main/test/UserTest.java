import com.pcb.management.model.User;
import com.pcb.management.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author : Haodong Zhao
 * @date : 2019/5/19
 * @time : 19:04
 */

@RunWith(JUnit4ClassRunner.class)// 添加spring测试方案
@ContextConfiguration("/spring-mybatis.xml")// 指定spring配置文件位置
public class UserTest {
    @Autowired
    IUserService userService;

    @Test
    public void findUserByName(){
        User user = userService.findUserByName("admin");
        System.out.println(user.toString());

    }

    @Test
    public void findUserByNameAndPassword() {
        User user = userService.findByNameAndPassword("admin","admin");
        System.out.println(user.toString());

    }

    @Test
    public void insertUser(){
        User insertUser = new User(0,"jack2","jack","test@qq.com",false);
        int rows = userService.insertUser(insertUser);
        if (rows > 0) {
            System.out.println("您成功修改了" + rows + "条数据！");
        } else {
            System.out.println("执行修改操作失败！！！");
        }
    }


}
