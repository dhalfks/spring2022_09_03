package com.ezen.myProject.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.myProject.domain.BoardVO;
import com.ezen.myProject.domain.PagingVO;
import com.ezen.myProject.domain.UserVO;
import com.ezen.myProject.handler.PagingHandler;
import com.ezen.myProject.repository.UserDAO;
import com.ezen.myProject.service.BoardService;
import com.ezen.myProject.service.UserService;
import com.ezen.myProject.service.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board/*")
@Controller
public class BoardController {

	@Inject
	private BoardService bsv;
	@Inject
	private UserDAO userDao;
	@Inject
	private UserServiceImpl usv;
	
	
	@GetMapping("/register")
	public String boardRegisterGet() {
		return "/board/register";
	}
	// insert, update, delete => redirect처리
	
	@PostMapping("/register")
	public String register(BoardVO bvo, RedirectAttributes rAttr) {
		log.info(bvo.toString());
		
		int isOk = bsv.register(bvo);
		rAttr.addAttribute("isOk",  isOk>0 ? "1":"0");
		log.info("board register >> "+( isOk>0 ? "OK":"FAIL"));
		return "redirect:/";
	}
	
	@GetMapping("/list")
	public String list(Model model, PagingVO pvo) {
		log.info(">>>> pageNo : "+pvo.getPageNo());
		List<BoardVO> list = bsv.getList(pvo);
		model.addAttribute("list", list);
		int totalCount = bsv.getTotalCount(pvo);
		PagingHandler ph = new PagingHandler(pvo, totalCount);
		model.addAttribute("pgn", ph);
		return "/board/list";
	}
		
	@GetMapping({"/detail","/modify"})
	public void detail(Model model, @RequestParam("bno")int bno) {
		BoardVO board = bsv.getDetail(bno);
		model.addAttribute("board", board);
	}
	
	@PostMapping("/modify")
	public String modify(RedirectAttributes reAttr, BoardVO bvo ) {
		log.info("modify >>> : "+ bvo.toString());
		UserVO user = userDao.getUser(bvo.getWriter());
		int isUp = bsv.modify(bvo, user);
		log.info(">>> modify : "+(isUp >0? "ok":"fail"));
		reAttr.addFlashAttribute(isUp>0 ? "1" : "0");
		return "redirect:/board/list";  
	}
	
	@GetMapping("/remove")
	public String remove(RedirectAttributes reAttr, @RequestParam("bno")int bno, 
			HttpServletRequest request) {
//		1. HttpSession ses = request.getSession();
//		UserVO user = (UserVO)ses.getAttribute("ses");
//		log.info(user.toString());
//		2. UserVO user = userDao.getUsers((UserVO)request.getSession().getAttribute("ses"));
		UserVO user = usv.getUser(request);
		
		int isUp = bsv.remove(bno, user);
		log.info(">>> remove : "+(isUp >0? "ok":"fail"));
		return "redirect:/board/list";
	}
	
	
}
