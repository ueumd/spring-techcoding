package com.ueumd.sevlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hello")
public class ServletDemo  extends HttpServlet {
   @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
       // response.setContentType("text/html");
       System.out.println("hello world");
       response.getWriter().write("hello");
   }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}