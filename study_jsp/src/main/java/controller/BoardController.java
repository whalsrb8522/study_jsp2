package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import service.BoardService;
import service.BoardServiceImpl;

@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(MemberController.class);

	private RequestDispatcher rdp;
	private BoardService bsv;
	private String destPage;
	private int isOk;
	
	private BoardVO bvo;
	private int bno;
	private String title;
	private String writer;
	private String content;
	
    public BoardController() {
    	bsv = new BoardServiceImpl();
    }

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/") + 1);
		log.info("* uri : " + uri);		
		log.info("* path : " + path);
		
		switch (path) {
		case "list":
			List<BoardVO> list = new ArrayList<BoardVO>();
			
			list = bsv.list();
			req.setAttribute("list", list);
			
			destPage = "/board/list.jsp";
			
			break;
		case "register_s1":
			destPage = "/board/register.jsp";
			
			break;
		case "register_s2":
			title = req.getParameter("title");
			writer = req.getParameter("writer");
			content =req.getParameter("content");
			bvo = new BoardVO(title, writer, content);
			
			isOk = bsv.register(bvo);
			log.info("* 게시글 등록 : " + (isOk > 0 ? "성공" : "실패"));
			
			destPage = "/brd/list";
			
			break;
		case "detail":
			bno = Integer.parseInt(req.getParameter("bno"));
			bvo = bsv.detail(bno);
			
			req.setAttribute("bvo", bvo);
		
			destPage ="/board/detail.jsp";
			
			break;
		case "modify_s1":
			bno = Integer.parseInt(req.getParameter("bno"));
			bvo = bsv.detail(bno);
			
			req.setAttribute("bvo", bvo);
		
			destPage ="/board/modify.jsp";
			
			break;
		case "modify_s2":
			bno = Integer.parseInt(req.getParameter("bno"));
			title = req.getParameter("title");
			content = req.getParameter("content");
			bvo = new BoardVO(bno, title, content);
			
			isOk = bsv.modify(bvo);
			log.info("* 게시글 수정 : " + (isOk > 0 ? "성공" : "실패"));
			
			destPage = "/brd/detail?bno = " + bno; 
			
			break;
		}
		
		rdp = req.getRequestDispatcher(destPage);
		rdp.forward(req, res);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet(request, response);
		service(request, response);
	}
}
