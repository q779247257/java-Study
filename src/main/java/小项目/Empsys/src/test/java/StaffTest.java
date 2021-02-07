import com.fasterxml.jackson.core.JsonEncoding;
import com.xuan.entity.Staff;
import com.xuan.service.StaffService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.function.Consumer;

/**
 * @Author: 轩轩
 * @Date: 2020/3/19 15:43
 * @description:
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")//加载Spring的配置文件
public class StaffTest {
    @Autowired
    private StaffService staffService;

    @Test
    public void StaffTest(){
        Staff staff1 = new Staff();
        staff1.setAge(18);
        staff1.setName("玄虚那");
        staff1.setPhone("15719a");
        staff1.setSex("男");
        Staff insert = staffService.insert(staff1);
        System.out.println("insert:"+insert);
        List<Staff> list = staffService.queryAll();


        list.stream().forEach(item ->{
            System.out.println("intem:"+item);
        });


        list.stream().forEach(new Consumer<Staff>() {
            @Override
            public void accept(Staff staff) {
                System.out.println("staff_1"+staff);
            }
        });
    }
}
