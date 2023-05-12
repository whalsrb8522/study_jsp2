package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import service.MemberService;
import service.MemberServiceImpl;

@WebServlet("/mem/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// log 설정
	// private static final Logger log = LoggerFactory.getLogger("클래스명.class");
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	
	private RequestDispatcher rdp;
	private MemberService msv;
	private int isOk;
	private String destPage;
	
	MemberVO mvo;
	private String id;
	private String password;
	private String name;
	private String email;
	private String phone;
//	private String regdate;
//	private String lastlogin;
       
    public MemberController() {
        msv = new MemberServiceImpl();
    }

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		
		String uri = req.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/") + 1);
		log.info("uri : " + uri);
		log.info("path : " + path);
		
		switch (path) {
		case "login":
			destPage = "/member/login.jsp";
			
			break;
		case "join":
			destPage = "/member/join.jsp";
			
			break;
		case "register":
			id = req.getParameter("id");
			password = req.getParameter("password");
			name = req.getParameter("name");
			email = req.getParameter("email");
			phone = req.getParameter("phone");
			
			mvo = new MemberVO(id, password, name, email, phone);
			
			isOk = msv.register(mvo);
			log.info("* 회원가입 : " + (isOk > 0 ? "성공" : "실패"));
			
			destPage = "/index.jsp";
			
			break;
		default:
			break;
		}
		
		rdp = req.getRequestDispatcher(destPage);
		rdp.forward(req, res);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
		//doGet(request, response);
	}
}
