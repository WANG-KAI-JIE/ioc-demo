package top.kjwang.ioc.context;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author kjwang
 * @Date 2023/3/12 09:12
 */
public class ClassPathXmlApplicationContext implements ApplicationContext{
    private Map<String,Object> iocContainer = new HashMap<>();

    public ClassPathXmlApplicationContext() {
        try {
            String path = Objects.requireNonNull(this.getClass().getResource("/applicationContext.xml")).getPath();
            path = URLDecoder.decode(path, StandardCharsets.UTF_8);
            SAXReader reader = new SAXReader();

            Document document = reader.read(new File(path));
            List<Node> beans = document.getRootElement().selectNodes("bean");
            for (Node node : beans) {
                Element ele = (Element) node;
                String id = ele.attributeValue("id");
                String className = ele.attributeValue("class");
//                通过反射创建实例
                Class<?> c = Class.forName(className);
                Object obj = c.getDeclaredConstructor().newInstance();

                List<Node> properties = ele.selectNodes("property");
                for (Node p : properties) {
                    Element property = (Element) p;
                    String name = property.attributeValue("name");
                    String value = property.attributeValue("value");

                    String setMethodName = "set" + name.substring(0,1).toUpperCase() + name.substring(1);
                    System.out.println("正在准备执行：" + setMethodName);
                    Method setMethod = c.getMethod(setMethodName, String.class);
                    //通过setter方法注入属性的数据
                    setMethod.invoke(obj,value);
                }
                iocContainer.put(id, obj);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(iocContainer);
        System.out.println("IoC 容器初始化完毕");
    }
    @Override
    public Object getBean(String beanId){
        return iocContainer.get(beanId);
    }
}
