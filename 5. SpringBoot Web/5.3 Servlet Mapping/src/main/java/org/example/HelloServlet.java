package org.example;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("In Service");
//        res.getWriter().println("Hello world");

    }

}
