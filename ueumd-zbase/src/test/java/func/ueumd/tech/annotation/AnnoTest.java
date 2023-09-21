package func.ueumd.tech.annotation;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * https://blog.csdn.net/u011983531/article/details/70941123
 *
 * Class.getAnnotations() 获取所有的注解，包括自己声明的以及继承的
 * Class.getAnnotation(Class< A > annotationClass) 获取指定的注解，该注解可以是自己声明的，也可以是继承的
 * Class.getDeclaredAnnotations() 获取自己声明的注解
 */

public class AnnoTest{
    /**
     * 得到类上的注解
     */
    @Test
    public void getClassAnno() {
        // 获取自身上注解
        Annotation[] deAnnos = AnnoDemo.class.getDeclaredAnnotations();

        for (Annotation annotation : deAnnos) {
            System.out.println(annotation);
        }


        Annotation[] allAnnos = AnnoDemo.class.getAnnotations();

        for (Annotation annotation : allAnnos) {
            System.out.println(annotation);
        }
    }

    /**
     * 得到方法上的注解
     */
    @Test
    public void test() {
        try {
            AnnoDemo demo = new AnnoDemo();
            Class<AnnoDemo> c = AnnoDemo.class;

            Method method = c.getMethod("output", new Class[]{});

            // 如果指定类型的注释存在于此元素上，则返回 true，否则返回 false。
            if (method.isAnnotationPresent(Anno.class)) { //RUNTIME

                //调用实例方法
                method.invoke(demo, new Object[]{});  //output something!

                //获取myTest.output方法上MyAnnotation的注解
                Anno anno = method.getAnnotation(Anno.class);

                String city = anno.city();
                String code = anno.code();

                System.out.println(city + " , " + code); // Hefei , 0551
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}