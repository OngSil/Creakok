package creakok.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import creakok.com.domain.Board;
import creakok.com.domain.Comment;
import creakok.com.domain.Creator;
import creakok.com.mapper.CreatorBoardMapper;
import creakok.com.vo.ListResult;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class CreatorBoardServiceImpl implements CreatorBoardService {
	@Autowired
	private CreatorBoardMapper mapper;
	
	@Override
	public ListResult getListResultS(int currentPage, int pageSize, String filterBy, String creator_name) {
		List<Board> list = (List<Board>)mapper.getListResult(currentPage, pageSize, filterBy, creator_name);
		int count = mapper.count();
		return new ListResult(currentPage, count, pageSize, list, filterBy);
	}
	
	@Override
	public Board contentS(long board_index) {
		Board board = mapper.content(board_index);
		return board;
	}
	
	@Override
	public void insertS(Board board) {
		mapper.insert(board);
	}
	
	@Override
	public int insertBoard(Board board) {
		return mapper.insertBoard(board);
	}
	
	@Override
	public Board getBoard(long board_index) {
		return mapper.selectByIndex(board_index);
	}
	
	@Override
	public boolean plusView(long board_index) {
		return mapper.plusView(board_index);
	}
	
	@Override
	public boolean plusLike(long board_index) {
		return mapper.plusLike(board_index);
	}
	// 모든 크리에이터 정보 조회 
	@Override
	public List<Creator> getCreatorName() {
		return mapper.getCreatorName();
	}
	// 글삭제
	@Override
	public void deleteBoard(long board_index) {
		mapper.deleteBoard(board_index);
	}
	// 글수정
	@Override
	public void edit(Board board) {
		mapper.update(board);
	}
	// 글검색조회
	@Override
	public ListResult getListResultBySearchS(int currentPage, int pageSize, String filterBy, String c_code, String searchName) {
		List<Board> list = (List<Board>)mapper.search(currentPage, pageSize, filterBy, c_code, searchName);
		int countBySearch = mapper.countBySearch(c_code, searchName);
		log.info("@@@@@@@@@@@@@@@@@@@@"+countBySearch+"@@@@@@@@@@@");
		return new ListResult(currentPage, countBySearch, pageSize, list, filterBy);
	}
	//크리에이터 정보 조회(굿즈에서 사용)
	@Override
	public Creator getContentByCreator(String creator_name) {
		return mapper.getContentByCreator(creator_name);
	}
}
