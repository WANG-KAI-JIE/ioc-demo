package top.kjwang;

import top.kjwang.ioc.context.ApplicationContext;
import top.kjwang.ioc.context.ClassPathXmlApplicationContext;

/**
 * @author kjwang
 * @Date ${DATE} ${TIME}
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext();
        Object sweetApple = ac.getBean("sweetApple");
    }
}