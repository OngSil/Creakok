package creakok.com.service;

import java.util.List;

import creakok.com.domain.Board;
import creakok.com.domain.Creator;
import creakok.com.domain.Like;
import creakok.com.vo.ListResult;

public interface BoardService {
	ListResult getListResultS(int currentPage, int pageSize);
	// ��ȸ�� ����
	ListResult getListResultByViewS(int currentPage, int pageSize);
	// ���ƿ�� ����
	ListResult getListResultByLikeS(int currentPage, int pageSize);
	Board contentS(long board_index);
	void insertS(Board board);
	// �Խù� �ۼ�
	public int insertBoard(Board board);
	// ��ȸ�� +1
	public boolean plusView(long board_index);
	// ���ƿ� +1
	public boolean plusLike(long board_index);
	// ũ�������� �̸� ��ȸ
	public List<Creator> getCreatorName();
	// ����
	void deleteBoard(long board_index);
	
	// �˻� ����
	Board getBoard(long board_index);
	void edit(Board board);
	
	List<Board> selectBySubjectS(String board_subject);
	List<Board> selectByNameS(String member_name);
}

