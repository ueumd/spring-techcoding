package org.example.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-28 13:54
 */

interface IRent {
    /**
     * 租房
     */
    void rent();
}

// 房东
class Landlord implements IRent{

    @Override
    public void rent() {
        System.out.println("房东要出租房子");
    }
}

// 房产中介 代理（静态）
class HousingAgency implements IRent{

    private Landlord landlord;

    public HousingAgency() {
    }

    public HousingAgency(Landlord landlord) {
        this.landlord = landlord;
    }

    @Override
    public void rent() {
        seeHouse();
        landlord.rent();
        fare();
        hetong();
    }
    //看房
    public void seeHouse(){
        System.out.println("中介带你看房");
    }

    //收中介费
    public void fare(){
        System.out.println("收中介费");
    }
    //签合同
    public void hetong(){
        System.out.println("签合同");
    }
}


/**
 * 基于Proxy类和InvocationHandler 实现动态代理
 */
class ProxyInvocationHandler implements InvocationHandler {

    //被代理的接口
    private IRent rent;

    public void setRent(IRent rent){
        this.rent = rent;
    }

    // 生成得到代理类
    public Object getProxy() {
        return Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                rent.getClass().getInterfaces(),
                this
        );
    }

    /**
     * 处理代理实例，并返回结果
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    public  Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        Object result = method.invoke(rent, args);
        //在方法调用后调用
//        seeHouse();
//        heTong();
        return result;
    }

    public void seeHouse(){
        System.out.println("看房子");
    }
    public void heTong(){
        System.out.println("签合同");
    }
}


/**
 * 基于Proxy类和InvocationHandler 实现动态代理
 * - Proxy：生成动态代理实例的
 * - InvocationHandler：调用处理程序并返回结果的
 *
 * 万能的自动生成代理类
 */
class ProxyInvocationHandler3 implements InvocationHandler{
    // 1. 被代理的接口
    public Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    // 2. 生成得到的代理类（代理谁）
    public Object getProxy() {
        return Proxy.newProxyInstance(
                this.getClass().getClassLoader(),

                // 注意这里是 target
                this.target.getClass().getInterfaces(),
                this
        );
    }


    // 3.处理代理实例，并返回结果（代用代理程序）
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
       // 动态获取方法名称
        System.out.println(method.getName());
        Object result = method.invoke(target, args);
        return result;
    }
}
class ProxyRent {
    public static void main(String[] args) {

//        staticProxy();
//        System.out.println("\n-------------------");
//        DynamicProxy();

        dynamicProxy3();
    }

    private static void staticProxy(){
        // 房东出租房子
        Landlord landlord = new Landlord();

        //代理,中介帮房东，但是呢？代理角色一般会有一些附属操作！
        HousingAgency  agency = new HousingAgency(landlord);

        //你不用面对房东，直接找中介租房即可！
        agency.rent();
    }

    private static void DynamicProxy() {
        //创建真实角色
        Landlord landlord = new Landlord();

        //创建代理角色 不存在
        ProxyInvocationHandler proxyInvocationHandler = new ProxyInvocationHandler();

        //设置要代理的对象
        proxyInvocationHandler.setRent(landlord);

        //获取代理对象，并强制转换
        IRent proxy = (IRent)proxyInvocationHandler.getProxy();

        //调用
        proxy.rent();
    }

    private static void dynamicProxy3() {
        //创建真实角色
        Landlord landlord = new Landlord();

        //创建代理角色 不存在
        ProxyInvocationHandler3 pih = new ProxyInvocationHandler3();

        //设置要代理的对象
        pih.setTarget(landlord);

        //获取代理对象，并强制转换
        IRent proxy = (IRent) pih.getProxy();
        proxy.rent();
    }
}
