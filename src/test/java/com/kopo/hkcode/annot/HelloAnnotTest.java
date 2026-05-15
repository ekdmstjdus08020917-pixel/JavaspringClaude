package com.kopo.hkcode.annot;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloAnnotTest {

    public static void main(String[] args) {

        // 1. beans_annt.xml 로드
        ApplicationContext context =
            new GenericXmlApplicationContext(
                "file:src/main/webapp/WEB-INF/spring/beans_annt.xml");

        // 2. hello 빈 가져오기
        // Hello hello = (Hello) context.getBean("hello");
           Hello hello1 = context.getBean(Hello.class);
           Hello hello2 = context.getBean(Hello.class);

        // 3. 실행 및 확인
        hello2.print();
        System.out.println(hello1==hello2);
        Hello hello3 = new Hello();
        Hello hello4 = new Hello();
        System.out.println(hello3==hello4);
        
    }
   
}