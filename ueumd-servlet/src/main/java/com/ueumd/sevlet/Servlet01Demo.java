package com.ueumd.sevlet;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-17 19:32
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 实现Servlet
 * 1. 创建普通Java类
 * 2. 实现Servlet的规范，继承HttpServlet
 * 3. 重写service方法，用来处理请求 (重写方法默认快捷键ctrl + o)
 * 4. 设置注解，指定访问路径
 */

@WebServlet("/servlet1")
public class Servlet01Demo extends HttpServlet {
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.service(req, resp);
//        System.out.println("Servlet Service Service");
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        super.doGet(request, response);
        System.out.println("==============");
        response.getWriter().write("hello 1123");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        super.doPost(request, response);
    }
}
