package com.kopo.hkcode.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class ProductService {

    private final DataSource dataSource;
    private final List<String> managerList;

    public ProductService(DataSource dataSource, List<String> managerList) {
        this.dataSource = dataSource;
        this.managerList = managerList;
    }

    // 기존 메서드
    public String getMaxQtyByRegion(String regionId) {
        String result = "";
        String sql = "SELECT MAX(QTY) FROM kopo_channel_seasonality_new WHERE regionid = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, regionId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int maxQty = rs.getInt(1);
                    result = "지역 [" + regionId + "]의 최대 판매량은 " + maxQty + "개 입니다.";
                }
            }
        } catch (Exception e) {
            result = "DB 조회 에러 발생: " + e.getMessage();
            e.printStackTrace();
        }
        if (managerList != null && !managerList.isEmpty()) {
            result += " (조회 담당자: " + managerList.get(0) + ")";
        }
        return result;
    }

 // 2015년도 데이터 건수 조회
    public String getCountByYear2015() {
        String result = "";
        String sql = "SELECT COUNT(*) FROM kopo_channel_seasonality_new WHERE YEARWEEK LIKE '2015%'";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt(1);
                result = "2015년도 데이터는 총 " + count + "건 입니다.";
            }
        } catch (Exception e) {
            result = "DB 조회 에러: " + e.getMessage();
            e.printStackTrace();
        }
        return result;
    	}
	}

