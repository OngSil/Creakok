package creakok.com.vo;

import java.util.ArrayList;
import java.util.List;

import creakok.com.domain.Board;

public class ListResult {
	
<<<<<<< HEAD
	private String filterBy;  // 최신순, 조회수순, 좋아요순으로 나누기 위해
	private String c_code;    // 검색할때 작성자 or 제목
	private String searchName; // 검색어
	private int currentPage;
	private long totalCount;
	private int pageSize;
	private List<Board> list;
	private long totalPageCount;
	
	public ListResult() {}
	public ListResult(int currentPage, long totalCount, int pageSize, List<Board> list, String filterBy) {
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.list = list;
		this.totalPageCount = calTotalPageCount();
		
		this.filterBy = filterBy;
	}
	
	public ListResult(int currentPage, long totalCount, int pageSize, List<Board> list, String filterBy, String c_code, String searchName) {
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.list = list;
		this.totalPageCount = calTotalPageCount();
		
		this.filterBy = filterBy;
		this.c_code = c_code;
		this.searchName = searchName;
	}
	private long calTotalPageCount() {
	      long tpc = totalCount/pageSize; 
	      if(totalCount%pageSize != 0) tpc++;
	      
	      return tpc;
	 }
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<Board> getList() {
		return list;
	}

	public void setList(ArrayList<Board> list) {
		this.list = list;
	}

	public long getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(long totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	
	public String getFilterBy() {
		return filterBy;
	}
	
	public void setFilterBy(String filterBy) {
		this.filterBy = filterBy;
	}
	public String getC_code() {
		return c_code;
	}
	public void setC_code(String c_code) {
		this.c_code = c_code;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
=======
	private String board_filterBy;  // 최신순, 조회수순, 좋아요순으로 나누기 위해
	private String board_c_code;    // 검색할때 작성자 or 제목
	private String board_searchName; // 검색어
	private int currentPage;
	private long totalCount;
	private int pageSize;
	private List<Board> list;
	private long totalPageCount;
	
	public ListResult() {}
	public ListResult(int currentPage, long totalCount, int pageSize, List<Board> list, String board_filterBy) {
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.list = list;
		this.totalPageCount = calTotalPageCount();
		
		this.board_filterBy = board_filterBy;
	}
	
	public ListResult(int currentPage, long totalCount, int pageSize, List<Board> list, String board_filterBy, String board_c_code, String board_searchName) {
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.list = list;
		this.totalPageCount = calTotalPageCount();
		
		this.board_filterBy = board_filterBy;
		this.board_c_code = board_c_code;
		this.board_searchName = board_searchName;
	}
	private long calTotalPageCount() {
	      long tpc = totalCount/pageSize; 
	      if(totalCount%pageSize != 0) tpc++;
	      
	      return tpc;
	 }
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<Board> getList() {
		return list;
	}

	public void setList(ArrayList<Board> list) {
		this.list = list;
	}

	public long getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(long totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	
	public String getBoard_filterBy() {
		return board_filterBy;
	}
	
	public void setBoard_filterBy(String board_filterBy) {
		this.board_filterBy = board_filterBy;
	}
	public String getBoard_c_code() {
		return board_c_code;
	}
	public void setBoard_c_code(String board_c_code) {
		this.board_c_code = board_c_code;
	}
	public String getBoard_searchName() {
		return board_searchName;
	}
	public void setBoard_searchName(String board_searchName) {
		this.board_searchName = board_searchName;
>>>>>>> branch 'hskim' of https://github.com/daumhch/Creakok.git
	}
}
