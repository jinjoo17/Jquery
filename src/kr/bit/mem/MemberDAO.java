package kr.bit.mem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class MemberDAO {//JDBC
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	
	public Connection getConnect(){
		String url="jdbc:mysql://127.0.0.1:3306/mysql";
		String user="root";
		String password="!BitCom0228";
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			conn=DriverManager.getConnection(url,user,password);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return conn;
	}
	
	public int memInsert(MemberVO vo){
		//                             일련번호,이름,나이,이메일,소개
		String SQL="insert into member(irum,age,email,intro) values(?,?,?,?)";
		conn=getConnect();
		int cnt=0;
		try{
			ps=conn.prepareStatement(SQL);
			ps.setString(1,vo.getIrum());
			ps.setInt(2,vo.getAge());
			ps.setString(3,vo.getEmail());
			ps.setString(4, vo.getIntro());
			cnt=ps.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return cnt;
	}
	public List<MemberVO> memList(){
		List<MemberVO> list= new ArrayList<MemberVO>();
		String SQL="select*from member";
		conn=getConnect();
		try{
			ps=conn.prepareStatement(SQL);
			rs=ps.executeQuery();
			while(rs.next()){
				int num=rs.getInt("num");
				String irum=rs.getString("irum");
			    int age=rs.getInt("age");
			    String email=rs.getString("email");
			    String intro=rs.getString("intro");
				//묶고(VO)-->담고(List)
			    MemberVO vo=new MemberVO();
			    vo.setNum(num);
			    vo.setIrum(irum);
			    vo.setAge(age);
			    vo.setEmail(email);
			    vo.setIntro(intro);
			    list.add(vo);
			    
			       
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	//memDel(int num)
	public int memDel(int num){
		String SQL="delete from member where num=?";
		conn=getConnect();
		int cnt=0;
		try{
			ps=conn.prepareStatement(SQL);
			ps.setInt(1, num);
		    cnt=ps.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return cnt;
	}
	public MemberVO memView(int num){
		
		String SQL="select*from member where num=?";
		conn=getConnect();
		MemberVO vo=null;
		try{
			ps=conn.prepareStatement(SQL);
			ps.setInt(1,num);
			rs=ps.executeQuery();
			
			if(rs.next()){
				num=rs.getInt("num");
				String irum=rs.getString("irum");
			    int age=rs.getInt("age");
			    String email=rs.getString("email");
			    String intro=rs.getString("intro");
				//묶고(VO)
			    vo=new MemberVO();
			    vo.setNum(num);
			    vo.setIrum(irum);
			    vo.setAge(age);
			    vo.setEmail(email);
			    vo.setIntro(intro);
			 }
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return vo;
	}
	
	public int memUpdate(MemberVO vo){
		//                             일련번호,이름,나이,이메일,소개
		String SQL="update member set age=?,email=?,intro=? where num=?";
		conn=getConnect();
		int cnt=0;
		try{
			ps=conn.prepareStatement(SQL);
			ps.setInt(1,vo.getAge());
			ps.setString(2,vo.getEmail());
			ps.setString(3, vo.getIntro());
			ps.setInt(4,vo.getNum());
			cnt=ps.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return cnt;
	}
}
