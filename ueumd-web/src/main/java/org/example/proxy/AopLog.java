package org.example.proxy;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-28 15:14
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 静态代理
 * dao -> service -> controller -> front
 *
 * 代理模式的好处：
 * 可以使真实角色的操作更加存粹！不用去关注一些公共的业务
 * 2. 公共交给了代理角色，实现了业务的分工
 * 公共业务发生扩展的时候，方便集中管理
 *
 * 缺点：
 * 一个真实角色就会产生一个代理角色，代码量会翻倍 开发效率变低
 *
 * 动态代理
 * 在程序运行时，运用反射机制动态创建而成
 * 动态代理是代理一个接口下的多个实现类
 * 动态代理不知道要代理什么东西，只有在运行时才知道
 *
 * 与静态代理类对照的是动态代理类，动态代理类的字节码在程序运行时由Java反射机制动态生成，无需程序员手工编写它的源代码。
 * 动态代理类不仅简化了编程工作，而且提高了软件系统的可扩展性，因为Java反射机制可以生成任意类型的动态代理类。
 * java.lang.reflect 包中的Proxy类和InvocationHandler接口提供了生成动态代理类的能力。
 */

// 业务接口
interface IUserService {
    void add();

    void delete();

    void update();

    void query();
}

// 原来业务类
class UserServiceImpl implements IUserService {
    @Override
    public void add() {
        System.out.println("新增用户");
    }

    @Override
    public void delete() {
        System.out.println("删除用户");
    }

    @Override
    public void update() {
        System.out.println("修改用户");
    }

    @Override
    public void query() {
        System.out.println("查询用户");
    }
}

// 静态代理
// 有一天，公司领导要求我为 原来业务类的所有方法新增日志输出功能
// 代理
class UserServiceImplProxy implements IUserService {
    private IUserService userService;

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void add() {
        log("add");
        userService.add();
    }

    @Override
    public void delete() {
        log("add");
        userService.delete();
    }

    @Override
    public void update() {
        log("add");
        userService.update();
    }

    @Override
    public void query() {
        log("add");
        userService.query();
    }

    //日志方法
    public void log(String msg) {
        System.out.println("【Debug 】使用了" + msg + "方法");
    }
}



/**
 * 基于Proxy类和InvocationHandler 实现动态代理
 * - Proxy：生成动态代理实例的
 * - InvocationHandler：调用处理程序并返回结果的
 *
 * 万能的自动生成代理类
 *
 * 在程序运行时，运用反射机制动态创建而成
 * 动态代理是代理一个接口下的多个实现类
 * 动态代理不知道要代理什么东西，只有在运行时才知道
 */
class ProxyInvocationHandler2 implements InvocationHandler {
    // 1. 被代理的接口
    public Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    // 2. 生成得到的代理类（代理谁）
    public Object getProxy() {
        return Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                this.target.getClass().getInterfaces(),
                this
        );
    }


    // 3.处理代理实例，并返回结果（代用代理程序）
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 动态获取方法名称
        log(method.getName());
        Object result = method.invoke(target, args);
        return result;
    }

    public void log(String msg) {
        System.out.println("【Debug 】使用了" + msg + "方法");
    }
}
 class AopLog {
    public static void main(String[] args) {
        // staticProxy();
        DynamicProxy2();
    }

    private static void staticProxy() {
        UserServiceImplProxy userServiceImplProxy = new UserServiceImplProxy();

        userServiceImplProxy.setUserService(new UserServiceImpl());
        userServiceImplProxy.add();
    }

    private static void DynamicProxy2() {
        //创建真实角色
        UserServiceImpl userService = new UserServiceImpl();

        //代理角色，不存在
        ProxyInvocationHandler2 pih = new ProxyInvocationHandler2();

        //设置要代理的对象
        pih.setTarget(userService);

        //获取代理对象
        IUserService proxy = (IUserService) pih.getProxy();

        //执行方法
        proxy.add();
    }
}
