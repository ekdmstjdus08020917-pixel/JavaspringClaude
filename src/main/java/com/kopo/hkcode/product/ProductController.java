package com.kopo.hkcode.product;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/getMaxQty")
    public Map<String, Object> getMaxQty(
            @RequestParam("regionid") String regionid,
            @RequestParam("productgroup") String productgroup) {

        String result = productService.getMaxQtyByRegion(regionid, productgroup);
        Map<String, Object> response = new HashMap<>();
        response.put("regionId", regionid);
        response.put("productGroup", productgroup);
        response.put("maxQty", result);
        response.put("status", "success");
        return response;
    }
}