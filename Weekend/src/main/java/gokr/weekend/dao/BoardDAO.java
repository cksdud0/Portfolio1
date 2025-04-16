package gokr.weekend.dao;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import gokr.weekend.common.DBConnPool;
import gokr.weekend.dto.BoardDTO;

public class BoardDAO extends DBConnPool {
		public BoardDAO() {
			super();
		}
		
		//검색 시 조건 충족 게시글 개수 반환
		public int selectCount(Map<String, Object> map) {
			int totalCount =0;
			String query = "SELECT COUNT(*) FROM community";
			if (map.get("searchWord") != null) {
					query += " WHERE " + map.get("searchField") + " "
							+ " LIKE '%" + map.get("searchWord") + "%'";
			} try { 
				stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				rs.next();
				totalCount = rs.getInt(1);
			} catch (Exception e) {
				System.out.println("게시글 개수 카운팅 중 오류 발생..");
				e.printStackTrace();
			}
			return totalCount;
		} 
		
		//검색 조건에 맞는 게시물 목록을 반환(+페이징.)
		public List<BoardDTO> selectListPage(Map<String,Object> map) {
	        List<BoardDTO> board = new Vector<BoardDTO>();
	        String query = " "
	                     + "SELECT * FROM ( "
	                     + "    SELECT Tb.*, ROWNUM rNum FROM ( "
	                     + "        SELECT * FROM COMMUNITY ";

	        if (map.get("searchWord") != null)
	        {
	            query += " WHERE " + map.get("searchField")
	                   + " LIKE '%" + map.get("searchWord") + "%' ";
	        }

	        query += "        ORDER BY cno DESC "
	               + "    ) Tb "
	               + " ) "
	               + " WHERE rNum BETWEEN ? AND ?";

	        try {
	            psmt = con.prepareStatement(query);
	            psmt.setString(1, map.get("start").toString());
	            psmt.setString(2, map.get("end").toString());
	            rs = psmt.executeQuery();

	            while (rs.next()) {
	                BoardDTO dto = new BoardDTO();

	                dto.setCno(rs.getString(1));
	                dto.setCtitle(rs.getString(2));
	                dto.setCtext(rs.getString(3));
	                dto.setCwdate(rs.getDate(4));
	                dto.setCviewcount(rs.getInt(5));
	                dto.setCwuser(rs.getString(6));
	                dto.setCpw(rs.getString(7));
	                dto.setCofile(rs.getString(8));
	                dto.setCsfile(rs.getString(9));
	                
	                board.add(dto);
	            }
	        }
	        catch (Exception e) {
	            System.out.println("게시물 조회 중 예외 발생");
	            e.printStackTrace();
	        }
	        return board;
	    }
		
	    // 게시글 작성 시 DB에 추가 (String만)
	    public int insertWrite(BoardDTO dto) {
	        int result = 0;
	        try {
	            String query = "INSERT INTO community ( "
	                         + " cno, ctitle, ctext, cwuser, cpw, cofile, csfile) "
	                         + " VALUES ( "
	                         + " seq_board_num.NEXTVAL,?,?,?,?,?,?)";
	            psmt = con.prepareStatement(query);
	            psmt.setString(1, dto.getCtitle());
	            psmt.setString(2, dto.getCtext());
	            psmt.setString(3, dto.getCwuser());
	            psmt.setString(4, dto.getCpw());
	            psmt.setString(5, dto.getCofile());
	            psmt.setString(6, dto.getCsfile());
	            result = psmt.executeUpdate();
	        }
	        catch (Exception e) {
	            System.out.println("게시물 입력 중 예외 발생");
	            e.printStackTrace();
	        }
	        return result;
	    }
	    
	    // 주어진 일련번호에 해당하는 게시물을 DTO에 담아 반환합니다.
	    public BoardDTO selectView(String cno) {
	        BoardDTO dto = new BoardDTO();  // DTO 객체 생성
	        String query = "SELECT * FROM community WHERE cno=?";  // 쿼리문 템플릿 준비
	        try {
	            psmt = con.prepareStatement(query);  // 쿼리문 준비
	            psmt.setString(1, cno);  // 인파라미터 설정
	            rs = psmt.executeQuery();  // 쿼리문 실행

	            if (rs.next()) {  // 결과를 DTO 객체에 저장
	            	dto.setCno(rs.getString(1));
	                dto.setCtitle(rs.getString(2));
	                dto.setCtext(rs.getString(3));
	                dto.setCwdate(rs.getDate(4));
	                dto.setCviewcount(rs.getInt(5));
	                dto.setCwuser(rs.getString(6));
	                dto.setCpw(rs.getString(7));
	                dto.setCofile(rs.getString(8));
	                dto.setCsfile(rs.getString(9));
	            }
	        }
	        catch (Exception e) {
	            System.out.println("게시물 상세보기 중 예외 발생");
	            e.printStackTrace();
	        }
	        return dto;  // 결과 반환
	    }
	    
	    // 주어진 일련번호에 해당하는 게시물의 조회수를 1 증가시킵니다.
	    public void updateVisitCount(String cno) {
	        String query = "UPDATE community SET "
	                     + " cviewcount=cviewcount+1 "
	                     + " WHERE cno=?"; 
	        try {
	            psmt = con.prepareStatement(query);
	            psmt.setString(1, cno);
	            psmt.executeQuery();
	        }
	        catch (Exception e) {
	            System.out.println("게시물 조회수 증가 중 예외 발생");
	            e.printStackTrace();
	        }
	    }
	    
	 // 입력한 비밀번호가 지정한 일련번호의 게시물의 비밀번호와 일치하는지 확인합니다.
	    public boolean confirmPassword(String cpw, String cno) {
	        boolean isCorr = true;
	        try {
	            String sql = "SELECT COUNT(*) FROM community WHERE cpw=? AND cno=?";
	            psmt = con.prepareStatement(sql);
	            psmt.setString(1, cpw);
	            psmt.setString(2, cno);
	            rs = psmt.executeQuery();
	            rs.next();
	            if (rs.getInt(1) == 0) {
	                isCorr = false;
	            }
	        }
	        catch (Exception e) {
	            isCorr = false;
	            e.printStackTrace();
	        }
	        return isCorr;
	    }
	    
	    // 지정한 일련번호의 게시물을 삭제합니다.
	    public int deletePost(String cno) {
	        int result = 0;
	        try {
	            String query = "DELETE FROM community WHERE cno=?";
	            psmt = con.prepareStatement(query);
	            psmt.setString(1, cno);
	            result = psmt.executeUpdate();
	        }
	        catch (Exception e) {
	            System.out.println("게시물 삭제 중 예외 발생");
	            e.printStackTrace();
	        }
	        return result;
	    }
	    
	    // 게시글 데이터를 받아 DB에 저장되어 있던 내용을 갱신합니다(파일 업로드 지원).
	    public int updatePost(BoardDTO dto) {
	        int result = 0;
	        try {
	            // 쿼리문 템플릿 준비
	            String query = "UPDATE community"
	                         + " SET ctitle=?, cwuser=?, ctext=?, cfile=? csfile=? "
	                         + " WHERE cno=? and cpw=?";

	            // 쿼리문 준비
	            psmt = con.prepareStatement(query);
	            psmt.setString(1, dto.getCtitle());
	            psmt.setString(2, dto.getCwuser());
	            psmt.setString(3, dto.getCtext());
	            psmt.setString(4, dto.getCofile());
	            psmt.setString(5, dto.getCsfile());
	            psmt.setString(6, dto.getCno());
	            psmt.setString(7, dto.getCpw());

	            // 쿼리문 실행
	            result = psmt.executeUpdate();
	        }
	        catch (Exception e) {
	            System.out.println("게시물 수정 중 예외 발생");
	            e.printStackTrace();
	        }
	        return result;
	    }
	    
	    
}
