package com.kopo.hkcode.product;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class ProductServiceTest {
    public static void main(String[] args) {

        // 1. beans_product.xml 로드
        ApplicationContext context = new GenericXmlApplicationContext(
            "file:src/main/webapp/WEB-INF/spring/beans_product.xml");

        // 2. ProductService 빈 가져오기(주입하는거 가장 중요)
        ProductService productService =(ProductService)context.getBean("productService");
        //호출 
        productService.getMaxQtyByRegion("A42");

        // 3. DB 조회 실행
        String result = productService.getMaxQtyByRegion("A42");
        System.out.println("결과: " + result);
    }
}












