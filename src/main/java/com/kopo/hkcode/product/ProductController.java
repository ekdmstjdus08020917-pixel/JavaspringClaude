package com.kopo.hkcode.product;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {

    @PostMapping("/getMaxQty")
    @ResponseBody
    public Map<String, Object> getMaxQty(@RequestParam("regionid") String regionid) {

        Map<String, Object> response = new HashMap<>();
        String productgroup = "haiteam";

        try {
            int maxQty = 500;
            response.put("regionId", regionid);
            response.put("productGroup", productgroup);
            response.put("maxQty", maxQty);
            response.put("status", "success");
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
        }

        return response;
    }
}