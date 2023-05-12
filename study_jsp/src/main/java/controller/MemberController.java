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
	
	private String id;
	private String password;
	private String name;
	private String email;
	private int phone;
	private String regdate;
	private String lastlogin;
       
    public MemberController() {
        msv = new MemberServiceImpl();
    }

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		
		String uri = req.getRequestURI();
		log.info(uri);
		
		switch (uri) {
		case "/mem/login":
			break;
		case "/mem/join":
			break;
		default:
			break;
		}
		
		rdp = req.getRequestDispatcher(destPage);
		rdp.forward(req, res);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
