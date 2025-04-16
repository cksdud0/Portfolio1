package gokr.weekend.dao;

import gokr.weekend.common.DBConnPool;
import gokr.weekend.dto.WebzineDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class WebzineDAO extends DBConnPool {

    // 조회 및 조회수 증가
    public WebzineDTO selectArticle(String wno) {
        WebzineDTO dto = new WebzineDTO();
        try {
            // 조회수 증가
            String updateQuery = "UPDATE webzine SET wviewcount = wviewcount + 1 WHERE wno = ?";
            psmt = con.prepareStatement(updateQuery);
            psmt.setString(1, wno);
            psmt.executeUpdate();
            psmt.close();

            // 데이터 조회
            String selectQuery = "SELECT * FROM webzine WHERE wno = ?";
            psmt = con.prepareStatement(selectQuery);
            psmt.setString(1, wno);
            rs = psmt.executeQuery();
            if (rs.next()) {
                dto.setWno(rs.getString("wno"));
                dto.setWtitle(rs.getString("wtitle"));
                dto.setWtext(rs.getString("wtext"));
                dto.setWofile(rs.getString("wofile"));
                dto.setWsfile(rs.getString("wsfile"));
                dto.setWwdate(rs.getDate("wwdate")); // Date 타입으로 수정
                dto.setWviewcount(rs.getInt("wviewcount"));
                dto.setUno(rs.getInt("uno"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    // 수정
    public int updateArticle(WebzineDTO dto) {
        int result = 0;
        try {
            String query = "UPDATE webzine SET wtitle = ?, wtext = ? WHERE wno = ?";
            psmt = con.prepareStatement(query);
            psmt.setString(1, dto.getWtitle());
            psmt.setString(2, dto.getWtext());
            psmt.setString(3, dto.getWno()); // String으로 수정
            result = psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 삭제
    public int deleteArticle(String wno) {
        int result = 0;
        try {
            String query = "DELETE FROM webzine WHERE wno = ?";
            psmt = con.prepareStatement(query);
            psmt.setString(1, wno); // String으로 수정
            result = psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}