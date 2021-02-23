package 面试机试题.成都中科大旗;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Field;

/**
 * @Author: 轩轩
 * @Date: 2021/2/23 22:45
 * @description:
 */
public class 反射获取对象值 {

    public static void main(String[] args) throws IllegalAccessException {
        Reflex001 bean = new Reflex001(99, "小明");

        Object age = getPropertyValue(bean, "age");
        Object name = getPropertyValue(bean, "name");

        System.out.println("age:" + age);
        System.out.println("name:" + name);

    }

    public static Object getPropertyValue(Object obj, String propertyName) throws IllegalAccessException {
        Class<?> Clazz = obj.getClass();
        Field field;
        if ((field = getField(Clazz, propertyName)) == null)
            return null;
        field.setAccessible(true);
        return field.get(obj);
    }

    public static Field getField(Class<?> clazz, String propertyName) {
        if (clazz == null)
            return null;
        try {
            return clazz.getDeclaredField(propertyName);
        } catch (NoSuchFieldException e) {
            return getField(clazz.getSuperclass(), propertyName);
        }
    }

}

@Data
@AllArgsConstructor
class Reflex001{
    private int age;
    private String name;
}
