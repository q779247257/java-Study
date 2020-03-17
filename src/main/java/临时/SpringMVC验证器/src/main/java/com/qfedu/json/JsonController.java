package com.qfedu.json;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@ResponseBody
public class JsonController {

    //@ResponseBody
    @RequestMapping("/json1.do")
    public Person json1(){
        Person p = new Person();
        p.setName("haha");
        p.setAge(20);
        p.setBirth(new Date());
        return p;
    }

}
