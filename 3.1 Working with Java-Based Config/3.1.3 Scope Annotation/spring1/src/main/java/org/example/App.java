package org.example;

import org.example.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        // when we will use java based config approach
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Desktop dt = context.getBean(Desktop.class);
        dt.compile();

        Desktop dt1 = context.getBean(Desktop.class);
        dt1.compile();











                  //when we will use XML config approch

//        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");  // create a container
//        Alien obj1 = context.getBean("alien1",Alien.class);
////    	obj1.setAge(21);
//        System.out.println(obj1.getAge());
//
//        obj1.code();
////
////    	Alien obj2 = (Alien) context.getBean("alien1");
////    	System.out.println(obj2.age);
////    	//obj2.code();
//
//
////        Computer com=	context.getBean( Desktop.class);
//        //Computer is interface hence it is also allowed
////        Computer com=	context.getBean( Computer.class);
//
//
//        //Remember when some bean injected on other bean
//        //does not  matter lazy-init is true or false object  get created
////    	Desktop obj=(Desktop)context.getBean("com2",Desktop.class);
////        Desktop obj= context.getBean( Desktop.class);
    }
}
