package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dao {
	public int login(String id, String pw) {
		Connection conn = null;
        PreparedStatement pstm = null; 
        ResultSet rs = null; 
        List<UserDto> user=new ArrayList<UserDto>();
        int answer=0;
        try {

            String quary = "select count(*) FROM usertable where id='"+id+"' and pw='"+pw+"'";
            
            conn = DBConnection.getConnection();
            pstm = conn.prepareStatement(quary);
            rs = pstm.executeQuery();
            rs.next();
            answer=rs.getInt(1);
            System.out.println("Select절 완료");
            
        } catch (SQLException sqle) {
            System.out.println("Select절 에러");
            sqle.printStackTrace();
            
        }finally{
           
            try{
                if ( rs != null ){rs.close();}   
                if ( pstm != null ){pstm.close();}   
                if ( conn != null ){conn.close(); }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
            
        }
        
		return answer;
		
	}
	
	public int signUp(UserDto dto) {
		Connection conn = null; 
        PreparedStatement pstm = null;  
        int rs=0;  
        
        try {

            String quary = "insert into usertable values (userSeq.nextval,?,?,?,?)";
            
            conn = DBConnection.getConnection();
            pstm = conn.prepareStatement(quary);
            
           
            pstm.setString(1, ""+dto.getId()+"");
            pstm.setString(2, ""+dto.getPassword()+"");
            pstm.setString(3, ""+dto.getName()+"");
            pstm.setString(4, ""+dto.getMail()+"");
            
            rs = pstm.executeUpdate();
            
            System.out.println("Insert절 완료");
        } catch (SQLException sqle) {
            System.out.println("Insert절 에러");
            sqle.printStackTrace();
            
        }finally{
            try{
                if ( pstm != null ){pstm.close();}   
                if ( conn != null ){conn.close(); }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
            
        }
		return rs;
		
	}
	
	public static void insert(UserDto dto) {
		
		Connection conn = null; 
        PreparedStatement pstm = null;  
        int rs;  
        
        try {

            String quary = "insert into user1 values (?,?,?)";
            
            conn = DBConnection.getConnection();
            pstm = conn.prepareStatement(quary);
            
            
            pstm.setString(1, ""+dto.getNo()+"");
            pstm.setString(2, ""+dto.getId()+"");
            pstm.setString(3, ""+dto.getPassword()+"");
            
            rs = pstm.executeUpdate();
            
            System.out.println("Insert절 완료");
        } catch (SQLException sqle) {
            System.out.println("Insert절 에러");
            sqle.printStackTrace();
            
        }finally{
            try{
                if ( pstm != null ){pstm.close();}   
                if ( conn != null ){conn.close(); }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
            
        }
	}
	
	public static void delete(String id) {
		
		Connection conn = null; 
        PreparedStatement pstm = null;  
        int rs;  
        
        try {
     
            String quary = "delete from user1 where id=?";
            
            conn = DBConnection.getConnection();
            pstm = conn.prepareStatement(quary);
            
            
            pstm.setString(1, ""+id+"");
            
            rs = pstm.executeUpdate();
            
            System.out.println("Delete절 완료");
        } catch (SQLException sqle) {
            System.out.println("Delete절 에러");
            sqle.printStackTrace();
            
        }finally{
            try{
                if ( pstm != null ){pstm.close();}   
                if ( conn != null ){conn.close(); }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
            
        }
	}

}
