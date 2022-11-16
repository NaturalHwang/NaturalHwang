package hufs.capstone.domain;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {
    //아이디 존재x : -1, 비번 틀림 : 0, 모두 일치 : 1
    private MemberDAO(){
    }
    private static MemberDAO instance = new MemberDAO();

    public static MemberDAO getInstance(){
        return instance;
    }
    public Connection getConnection()throws Exception{
        Connection conn = null;
        Context initContext = new InitialContext();
        Context envContent = (Context) initContext.lookup("url");
        DataSource ds = (DataSource) envContent.lookup("url");
        conn = ds.getConnection();
        return conn;
    }
    public int memberCheck(String id, String password){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result = -1;

        String sql = "select id, pwd from Member where id=?";
        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);

            rs = pstmt.executeQuery();

            if(rs.next()){
                String pwd_in_db = rs.getString("password");
                if(pwd_in_db !=null && pwd_in_db.equals(pwd_in_db)){
                    // pwd가 조회되면 입력받은 id가 존재
                    result = 1;
                } else result = 0;
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }
                if(pstmt != null){
                    pstmt.close();
                }
                if(rs != null){
                    rs.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }
    public MemberVO getUser(String id){
        MemberVO mVO = null; // 데이터가 없을 경우 null 값을 반환.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from Member where id=?";

		try {
			conn = getConnection(); // DB 연결 시도
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				mVO = new MemberVO();
				mVO.setUsername(rs.getString("name"));
				mVO.setId(rs.getInt("id"));
				mVO.setPassword(rs.getString("password"));
				mVO.setMail(rs.getString("email"));
				mVO.setPhone(rs.getString("phone"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return mVO;
	}
}

