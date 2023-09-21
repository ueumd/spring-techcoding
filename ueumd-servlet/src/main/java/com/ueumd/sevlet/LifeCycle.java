package com.ueumd.sevlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-17 20:10
 */
@WebServlet("/life")
public class LifeCycle extends HttpServlet {
    /**
     * 服务器停止时执行
     */
    @Override
    public void destroy() {
        super.destroy();
        System.out.println("服务销毁了");
    }

    /**
     * 初始化执行一次，后续不再执行
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        System.out.println("实例创建了");
    }

    /**
     * 会被多调用
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("被调用了");
        super.service(req, resp);
    }
}
