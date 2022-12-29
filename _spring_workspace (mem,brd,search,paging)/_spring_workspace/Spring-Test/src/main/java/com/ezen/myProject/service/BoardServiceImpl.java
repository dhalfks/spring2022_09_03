package com.ezen.myProject.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.myProject.domain.BoardVO;
import com.ezen.myProject.domain.PagingVO;
import com.ezen.myProject.domain.UserVO;
import com.ezen.myProject.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO bdao;

	@Override
	public int register(BoardVO bvo) {
		log.info(">>> board register check2");
		return bdao.insertBoard(bvo);
	}

	@Override
	public List<BoardVO> getList() {
		log.info(">>> board list check2");
		return bdao.selectBoardList();
	}

	@Override
	public BoardVO getDetail(int bno) {
		log.info(">>> board detail check2");
		bdao.readCount(bno);
		return bdao.selectBoardDetail(bno);
	}

	@Override
	public int modify(BoardVO bvo, UserVO user) {
		log.info(">>> board modify check2");
		BoardVO tmpBoard = bdao.selectBoardDetail(bvo.getBno());
		if(user == null || !user.getId().equals(tmpBoard.getWriter())) {
			return 0;
		}
		return bdao.updateBoard(bvo);
	}

	@Override
	public int remove(int bno, UserVO user) {
		log.info(">>> board remove check2");
		BoardVO tmpBoard = bdao.selectBoardDetail(bno);
		if(user == null || !user.getId().equals(tmpBoard.getWriter())) {
			return 0;
		}
		return bdao.deleteBoard(bno);
	}

	@Override
	public List<BoardVO> getList(PagingVO pvo) {
		log.info(">>> board Paging List check2");
		return bdao.selectBoardListPaging(pvo);
	}

	@Override
	public int getTotalCount() {
		log.info(">>> board totalCount check2");
		return bdao.selectTotalCount();
	}

	@Override
	public int getTotalCount(PagingVO pvo) {
		log.info(">>> board search totalCount check2");
		return bdao.searchTotalCount(pvo);
	}
	
}
