package creakok.com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import creakok.com.domain.Board;
import creakok.com.domain.Creator;

public interface DarkKnightBoardMapper {
	List<Board> getListResult(@Param("currentPage") int currentPage, @Param("pageSize") int pageSize);
	int count();
	Board content(long board_index);
	void insert(Board board);
	// �Խù� �ۼ�
	public int insertBoard(Board board);
	// ��ȸ�� +1
	public boolean plusView(long board_index);
	// ���ƿ� +1
	public boolean plusLike(long board_index);
	// ũ�������� �̸� ��ȸ
	public List<Creator> getCreatorName();
	
	Board selectByIndex(long board_index);
	void update(Board board);
	
	// for Ajax
	List<Board> selectBySubject(String board_subject);
	List<Board> selectByName(String member_name);
}
