

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.bit.mem.MemberDAO;
import kr.bit.mem.MemberVO;


@WebServlet("/mlist.do")
public class MemberListController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		MemberDAO dao=new MemberDAO();
		List<MemberVO>list=dao.memList();
		
		//Ajax--->요청-->JSON--->응답(callback)
		Gson g=new Gson();
		String json=g.toJson(list);
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.print(json);
	}

}
