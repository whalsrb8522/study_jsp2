package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import handler.FileHandler;
import handler.PagingHandler;
import net.coobird.thumbnailator.Thumbnails;
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
	private String savePath;
	
	private BoardVO bvo;
	private int bno;
	private String title;
	private String writer;
	private String content;
	private String image; 
	
    public BoardController() {
    	bsv = new BoardServiceImpl();
    }

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	res.setCharacterEncoding("UTF-8");
    	
		String uri = req.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/") + 1);
		log.info(">>> uri : " + uri);		
		log.info(">>> path : " + path);
		
		switch (path) {
//		case "list":
//			List<BoardVO> list = new ArrayList<BoardVO>();
//			
//			list = bsv.list();
//			req.setAttribute("list", list);
//			
//			destPage = "/board/list.jsp";
//			
//			break;
		case "register_s1":
			destPage = "/board/register.jsp";
			break;
		case "register_s2":
//			title = req.getParameter("title");
//			writer = req.getParameter("writer");
//			content =req.getParameter("content");
//			bvo = new BoardVO(title, writer, content);
//			
//			isOk = bsv.register(bvo);
//			log.info(">>> 게시글 등록 : " + (isOk > 0 ? "성공" : "실패"));
//			
//			destPage = "/brd/page";
			
			try {
				// 파일을 업로드할 물리적인 경로
				savePath = getServletContext().getRealPath("_fileUpload");
				log.info(">>> savePath : " + savePath);
				File fileDir = new File(savePath);
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				// 파일의 저장위치를 담고있는 객체를 저장
				fileItemFactory.setRepository(fileDir);		
				// 파일 저장을 위한 임시 메모리 용량 설정 : byte 단위
				fileItemFactory.setSizeThreshold(2 * 1024 * 1024);		
				
				bvo = new BoardVO();
				// multipart/form-data 형식으로 넘어온 request 객체를 다루기 쉽게 변환해주는 역할
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				
				List<FileItem> itemList = fileUpload.parseRequest(req);
				log.info(">>> itemList : " + itemList);
				for(FileItem item : itemList) {
					log.info(">>> item : " + item);
					switch (item.getFieldName()) {
					case "title":
						bvo.setTitle(item.getString("UTF-8"));
						break;
					case "writer":
						bvo.setWriter(item.getString("UTF-8"));
						break;
					case "content":
						bvo.setContent(item.getString("UTF-8"));
						break;
					case "image":
						// 이미지 유무 체크
						if (item.getSize() > 0) {		// 데이터의 크기를 이용하여 유무 결정
							// 경로를 포함한 파일 이름
							String fileName = item.getName().substring(item.getName().lastIndexOf("/") + 1);
							fileName = System.currentTimeMillis() + "_" + fileName;
							log.info(">>> fileName : " + fileName);
							
							File uploadFilePath = new File(fileDir + File.separator + fileName);
							log.info(">>> uplodaFilePath : " + uploadFilePath);
							
							//저장
							try {
								item.write(uploadFilePath);
								bvo.setImage(fileName);
								
								// 섬네일 작업 : 리스트 페이지에서 트래픽 과다사용 방지
								Thumbnails.of(uploadFilePath).size(75,  75).toFile(new File(fileDir + File.separator + "th_" + fileName));
							} catch (Exception e) {
								log.info(">>> File writer on disk fail");
								e.printStackTrace();
							}
						}
						break;
					}
				}
				
				isOk = bsv.register(bvo);
				log.info(">>> 게시글 등록 : " + (isOk > 0 ? "성공" : "실패"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			destPage = "page";
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
//			bno = Integer.parseInt(req.getParameter("bno"));
//			title = req.getParameter("title");
//			content = req.getParameter("content");
//			bvo = new BoardVO(bno, title, content);
//			
//			isOk = bsv.modify(bvo);
//			log.info(">>> 게시글 수정 : " + (isOk > 0 ? "성공" : "실패"));
//			
//			destPage = "/brd/detail?bno = " + bno; 
			
			try {
				savePath = getServletContext().getRealPath("_fileUpload");
				File fileDir = new File(savePath);
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir);
				fileItemFactory.setSizeThreshold(2 * 1024 * 1024);
				
				bvo = new BoardVO();
				
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				log.info(">>> fileUpload : " + fileUpload);
				
				List<FileItem> itemList = fileUpload.parseRequest(req);
				log.info(">>> itemList : " + itemList);
				 
				String old_file = null;
				for(FileItem item : itemList) {
					log.info(">>> item : " + item.getString("UTF-8"));
					switch (item.getFieldName()) {
					case "bno":
						bvo.setBno(Integer.parseInt(item.getString("UTF-8")));
						break;
					case "title":
						bvo.setTitle(item.getString("UTF-8"));
						break;
					case "content":
						bvo.setContent(item.getString("UTF-8"));
						break;
					case "image":
						old_file = item.getString("UTF-8");
						break;
					case "newImage":
						if(item.getSize() > 0) {		// 새로운 파일이 등록됨
							if(old_file != null) {
								FileHandler fileHandler = new FileHandler();
								isOk = fileHandler.deleteFile(old_file, savePath);
							}
							
							String fileName = item.getName().substring(item.getName().lastIndexOf(File.separator) + 1);
							log.info(">>> newFileName : " + fileName);
							
							//실제 저장 이름
							fileName = System.currentTimeMillis() + "_" + fileName;
							File uploadFilePath = new File(fileDir + File.separator + fileName);
							
							try {
								item.write(uploadFilePath);
								bvo.setImage(fileName);
								log.info(">>> bvo.image : " + bvo.getImage());
								
								Thumbnails.of(uploadFilePath).size(75,  75).toFile(new File(fileDir + File.separator + "th_" + fileName));
							} catch (Exception e) {
								log.info(">>> File update on disk fail");
								e.printStackTrace();
							}
						} else {		// 새로운 파일을 넣지 않았다면
							// 기존 파일을 다시 bvo 객체에 저장
							bvo.setImage(old_file);
						}
						break;
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			isOk = bsv.modify(bvo);
			
			destPage = "page"; 
			break;
		case "delete":
			bno = Integer.parseInt(req.getParameter("bno"));
			image = bsv.getFileName(bno);
			
			if (bvo.getImage() != null) {
				savePath = getServletContext().getRealPath("_fileUpload");
				File fileDir = new File(savePath);
				
				File deleteFilePath = new File(fileDir + File.separator + image);
				File deleteThumbFilePath = new File(fileDir + File.separator + "th_" + image);
				log.info(">>> deleteFilePath : " + deleteFilePath);
				log.info(">>> deleteThumbFilePath : " + deleteThumbFilePath);
				
				deleteFilePath.delete();
				deleteThumbFilePath.delete();
			} 
			
			isOk = bsv.delete(bno);
			log.info(">>> 게시글 삭제 : " + (isOk > 0 ? "성공" : "실패"));
			
			destPage = "/brd/page";
			 
			break;
		case "page":
			try {
				int pageNo = 1;
				int qty = 10;
				
				String type = req.getParameter("type");
				String keyword = req.getParameter("keyword");
				
				log.info(">>> type : " + type);
				log.info(">>> keyword : " + keyword);
				
				if(req.getParameter("pageNo") != null) {
					pageNo = Integer.parseInt(req.getParameter("pageNo"));
					qty = Integer.parseInt(req.getParameter("qty"));
				}
				
				
				PagingVO pgvo = new PagingVO(pageNo, qty);
				pgvo.setType(type);
				pgvo.setKeyword(keyword);
				
				log.info(">>> pgvo : " + pgvo.toString());
				
				//전체 페이지 개수
				int totCount = bsv.getTotal(pgvo);
				log.info(">>> totCount : " + totCount);
				
				//limit를 이용한 select List를 호출 (startPage, qty
				// 한페이지에 나와야 하는 리스트
				List<BoardVO> boardList = bsv.getPageList(pgvo); 
				log.info(">>> boardList size : " + boardList.size());
				PagingHandler ph = new PagingHandler(pgvo, totCount);
				req.setAttribute("pgh", ph);
				req.setAttribute("list", boardList);
				destPage="/board/list.jsp";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
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
