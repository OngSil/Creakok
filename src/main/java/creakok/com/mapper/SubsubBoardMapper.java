package creakok.com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import creakok.com.domain.Board;
import creakok.com.domain.Creator;
import creakok.com.vo.ListResult;

public interface SubsubBoardMapper {
	List<Board> getListResult(@Param("currentPage") int currentPage, @Param("pageSize") int pageSize, @Param("filterBy") String filterBy);
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
	
	// ����
	void deleteBoard(long board_index);
	// ����
	Board selectByIndex(long board_index);
	// update
	void update(Board board);
	
	// �˻�
	List<Board> search(@Param("currentPage") int currentPage, @Param("pageSize") int pageSize, 
			@Param("filterBy") String filterBy, @Param("c_code") String c_code, 
			@Param("searchName")String searchName);

}
