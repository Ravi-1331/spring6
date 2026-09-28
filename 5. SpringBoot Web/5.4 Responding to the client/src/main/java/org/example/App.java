package org.example;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class App {

    public static void main(String[] args) throws LifecycleException {

        System.out.println("Hello World!");

        Tomcat tomcat = new Tomcat();

        tomcat.setPort(8081);
        tomcat.getConnector();

        Context context = tomcat.addContext("", null);

        Tomcat.addServlet(context, "HelloServlet", new HelloServlet());

        context.addServletMappingDecoded("/hello", "HelloServlet");

        tomcat.start();

        System.out.println("Embedded Tomcat started on port 8081");
        System.out.println("http://localhost:8081/hello");

        tomcat.getServer().await();
    }
}