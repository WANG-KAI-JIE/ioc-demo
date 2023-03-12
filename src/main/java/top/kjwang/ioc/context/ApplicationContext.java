package top.kjwang.ioc.context;

/**
 * @author kjwang
 * @Date 2023/3/12 09:10
 */
public interface ApplicationContext  {
    /**
     * 根据 beanId 获取对应的 Bean 实例
     * @param beanId beanId
     * @return Bean 实例
     */
    Object getBean(String beanId);
}
