package com.qfedu.convert;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
// 为了在参数上直接使用各种验证注解，@Validated修饰类
@Validated
public class ConvertController {

    // @DateTimeFormat 针对具体的参数执行转换格式
    // @DateTimeFormat 可以修饰方法中参数，也可以修饰实体类中的属性
    @RequestMapping("/convert1.do")
    public String convertDate(String name, int age, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date birth){
        System.out.println(name);
        System.out.println(age);
        System.out.println(birth);

        return "index";
    }
    @RequestMapping("/convert2.do")
    public String convertDate2(int age, Date birth){
        System.out.println(birth);
        System.out.println(age);
        return "index";
    }

    // 针对控制器类
//    @InitBinder  // 了解
//    public void initBind(WebDataBinder binder){
//        // 注册日期转换器
//        // 第一个参数：表示要转换成的类型
//        // 第二个参数：创建转换器的对象
//        binder.registerCustomEditor(Date.class,
//                // 第一个参数：转换格式
//                // 第二个参数：是否可以为空
//                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
//    }

    // 验证的注解写在类的属性上，在需要验证的对象的后面，必须紧跟着BindReulst类型的参数
    @RequestMapping("/validate1.do")
    public String validate(@Valid User user, BindingResult result){
        // 如果验证不通过，捕获到错误
        if(result.hasErrors()){
            List<ObjectError> allErrors = result.getAllErrors();
            for(ObjectError error : allErrors){
                FieldError err = (FieldError)error;
                // 获取验证异常的属性
                System.out.println(err.getField());
                // 获取验证异常的提示信息
                System.out.println(err.getDefaultMessage());
            }
        }
        return "index";
    }

    // 验证不通过，包BindException
    @RequestMapping("/validate3.do")
    public String validate3(@Valid User user){

        return "index";
    }
    // 验证不通过，报ConstraintViolationException
    @RequestMapping("/validate2.do")
    public String validate2(@NotEmpty String name){
        return "index";
    }
    // 异常处理方法
//    @ExceptionHandler(ConstraintViolationException.class)
//    public String constraintViolationExceptione(ConstraintViolationException e){
//        Iterator<ConstraintViolation<?>>
//                iterator = e.getConstraintViolations().iterator();
//        String message = null;
//        if (iterator.hasNext()) {
//            message = iterator.next().getMessage();
//            System.out.println(message);
//        }
//        return "erorr";
//    }

}
