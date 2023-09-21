package com.thread;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-18 8:30
 */


interface IMarry {
    void HappyMarry();
}

// 真实角色
class You implements IMarry {

    @Override
    public void HappyMarry() {
        System.out.println("结婚了，要开心...");
    }
}

// 婚庆公司代理
class WeddingCompany implements IMarry {
    private IMarry target;

    public WeddingCompany(IMarry target) {
        this.target = target;
    }

    private void after() {
        System.out.println("结婚之后， 收尾款");
    }

    private void before() {
        System.out.println("结婚之前， 布置现场");
    }

    @Override
    public void HappyMarry() {
        before();
        this.target.HappyMarry();
        after();
    }
}

/**
 * 好处
 * 代理对象可以做很多真实对象做不了的事情
 * 真实对象只做自己专注的事情
 */
public class StaticProxy {
    public static void main(String[] args) {

        new Thread(() -> System.out.println("I Love You")).start();

        WeddingCompany weddingCompany = new WeddingCompany(new You());
        weddingCompany.HappyMarry();
    }
}