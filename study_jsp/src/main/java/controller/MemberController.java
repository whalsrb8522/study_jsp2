package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	private String destPage;
	private int isOk;
	HttpSession ses;
	
	MemberVO mvo;
	private String id;
	private String password;
	private String name;
	private String email;
	private String phone;
       
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
		case "sign_in_s1":
			destPage = "/member/login.jsp";
			
			break;
		case "sign_in_s2":
			try {
				id = req.getParameter("id");
				password = req.getParameter("password");
				mvo = new MemberVO(id, password);
				
				// 해당 ID, password가 DB상에 있는지 체크
				// 해당 객체(사용자)를 가져와서
				// 해당 객체(사용자를) 세션에 담기
				MemberVO loginMvo = msv.login(mvo);
				
				if(loginMvo != null) {
					ses = req.getSession();		// 세션 가져오기
					ses.setAttribute("ses", loginMvo);
					ses.setMaxInactiveInterval(10 * 60);		// 로그인 유지 시간 (초단위)
				} else {
					req.setAttribute("msg_login", 0);
				}
				
				destPage = "/index.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			break;
		case "sign_up_s1":
			destPage = "/member/join.jsp";
			
			break;
		case "sign_up_s2":
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
		case "modify_s1":
			ses = req.getSession();

			mvo = (MemberVO) ses.getAttribute("ses");
			
			MemberVO resultMvo = msv.detail(mvo);
			
			req.setAttribute("mvo", resultMvo);
			
			destPage = "/member/modify.jsp";
			
			break;
		case "modify_s2":
//			id = req.getParameter("id");
//			password = req.getParameter("password");
//			name = req.getParameter("name");
//			email = req.getParameter("email");
//			phone = req.getParameter("phone");
			
			mvo = new MemberVO(
					req.getParameter("id"), 
					req.getParameter("password"), 
					req.getParameter("name"), 
					req.getParameter("email"), 
					req.getParameter("phone"));
			
			isOk = msv.modify(mvo);
			log.info("* 회원수정 : " + (isOk > 0 ? "성공" : "실패"));
			
			destPage = "/index.jsp";
			
			break;
		case "detail":
			id = req.getParameter("id");
			
			mvo = new MemberVO(id);
			
			req.setAttribute("mvo", msv.detail(mvo));
			
			destPage = "/member/modify.jsp";
			
			break;
		case "logout":
			try {
				ses = req.getSession();
				
				// 마지막 로그인 시간 기록
				mvo = (MemberVO) ses.getAttribute("ses");
				id = mvo.getId();
				log.info("* logout id : " + id);
				
				// 로그인정보를 주고 마지막 로그인 시간 기록
				isOk = msv.lastlogin(id);
				
				ses.invalidate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			destPage = "/index.jsp";
			
			break;
		case "delete":
			ses = req.getSession();
			mvo = (MemberVO) ses.getAttribute("ses");
			
			id = req.getParameter("id");
			
			isOk = msv.delete(id);
			
			if (isOk > 0) {
				if (id.equals((mvo.getId()))) {
					ses.invalidate();
				}
				req.setAttribute("msg_delete", 1);
			} else {
				req.setAttribute("msg_delete", 0);
			}
			
			destPage = "/index.jsp";
			
			break;
		case "list":
			List<MemberVO> list = msv.list();
			
			req.setAttribute("list", list);
			
			destPage = "/member/list.jsp";
			
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
