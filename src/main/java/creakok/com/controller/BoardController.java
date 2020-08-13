package creakok.com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import creakok.com.domain.Board;
import creakok.com.domain.Comment;
import creakok.com.domain.Creator;
import creakok.com.service.BoardService;
import creakok.com.vo.ListResult;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class BoardController {
	@Autowired
	private BoardService service;
	
	@RequestMapping("board_page")
	public ModelAndView getListResult(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cpStr = request.getParameter("cp");
		String psStr = request.getParameter("ps");
		String filterBy = request.getParameter("filterBy");
		
		HttpSession session = request.getSession();
		
		//(1) cp 
		int cp = 1;
		if(cpStr == null) {
			Object cpObj = session.getAttribute("cp");
			if(cpObj != null) {
				cp = (Integer)cpObj;
			}
		}else {
			cpStr = cpStr.trim();
			cp = Integer.parseInt(cpStr);
		}
		session.setAttribute("cp", cp);
		
		//(2) ps 
		int ps = 15;
		/*
		if(psStr == null) {
			Object psObj = session.getAttribute("ps");
			if(psObj != null) {
				ps = (Integer)psObj;
			}
		}else {
			psStr = psStr.trim();
			int psParam = Integer.parseInt(psStr);
			
			Object psObj = session.getAttribute("ps");
			if(psObj != null) {
				int psSession = (Integer)psObj;
				if(psSession != psParam) {
					cp = 1;
					session.setAttribute("cp", cp);
				}
			}else {
				if(ps != psParam) {
					cp = 1;
					session.setAttribute("cp", cp);
				}
			}
			
			ps = psParam;
		}
		*/
		session.setAttribute("ps", ps);
		
		String c_code = request.getParameter("c_code");
		String searchName = request.getParameter("searchName");
		
		if (c_code==null) { 
			String c_codeTemp = (String) session.getAttribute("c_code");
			if (c_codeTemp !=null) {
				c_code = c_codeTemp;
				log.info("@@@@@@@@@@@@"+ c_code);
			} else {
				c_code = "#####";
			}
		}
		session.setAttribute("c_code", c_code);
		
		if (searchName==null) { 
			String searchNameTemp = (String) session.getAttribute("searchName");
			if (searchNameTemp !=null) {
				searchName = searchNameTemp;
				log.info("@@@@@@@@@@@@"+ searchName);
			} else {
				searchName = "#####";
			}
		}
		session.setAttribute("searchName", searchName);
		
		if (filterBy==null) { 
			String filterByTemp = (String) session.getAttribute("filterBy");
			if (filterByTemp !=null) {
				filterBy = filterByTemp;
				log.info("@@@@@@@@@@@@"+ filterBy);
			} else {
				filterBy = "BOARD_INDEX";
			}
		}
		
		session.setAttribute("filterBy", filterBy);
		/*
		// �˻������� ����¡�ϱ�
		String searchName = request.getParameter("searchName");
		String searchNameTemp = (String)session.getAttribute("searchName");
		searchName = searchNameTemp;
		session.setAttribute("searchName", searchName);
		log.info("@@@@@@@@@@@@@@@@" + searchName);
		*/
		ListResult listResult = service.getListResultS(cp, ps, filterBy);
		ModelAndView mv  = new ModelAndView();
		mv.setViewName("community");
		mv.addObject("listResult", listResult);
		
		// ��� �޴��� ũ�������� �̸� ���
		List<Creator> creatorList = service.getCreatorName();
		mv.addObject("creatorList", creatorList);
		
		return mv;
	}
	
	@RequestMapping("board_content")
	public ModelAndView content(HttpServletRequest request, HttpServletResponse response, HttpSession session, 
			@RequestParam long board_index) {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("community_board_content");
		
		Board board = service.contentS(board_index);
		
		// ��� ��������
		List<Comment> commentList = (List<Comment>) service.getComment(board_index);
		mv.addObject("commentList", commentList);
		
		Cookie[] cookies = request.getCookies();
		// ���ϱ� ���� ���ο� ��Ű
        Cookie viewCookie = null;
        
        // ��Ű�� ���� ��� 
        if (cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                // Cookie�� name�� cookie + reviewNo�� ��ġ�ϴ� ��Ű�� viewCookie�� �־��� 
                if (cookies[i].getName().equals("cookie"+board_index)) { 
                    System.out.println("ó�� ��Ű�� ������ �� ����.");
                    viewCookie = cookies[i];
                }
            }
        }
        
        if (board != null) {
            System.out.println("System - �ش� ���������� �Ѿ");
            
            mv.addObject("board", board);
 
            // ���� viewCookie�� null�� ��� ��Ű�� �����ؼ� ��ȸ�� ���� ������ ó����.
            if (viewCookie == null) {    
                System.out.println("cookie ����");
                
                // ��Ű ����(�̸�, ��)
                Cookie newCookie = new Cookie("cookie"+board_index, "|" + board_index + "|");
                                
                // ��Ű �߰�
                response.addCookie(newCookie);
 
                // ��Ű�� �߰� ��Ű�� ��ȸ�� ������Ŵ
                boolean result = service.plusView(board_index);
                
                if(result) {
                    System.out.println("��ȸ�� ����");
                }else {
                    System.out.println("��ȸ�� ���� ����");
                }
            }
            // viewCookie�� null�� �ƴҰ�� ��Ű�� �����Ƿ� ��ȸ�� ���� ������ ó������ ����.
            else {
                System.out.println("cookie ����");
                
                // ��Ű �� �޾ƿ�.
                String value = viewCookie.getValue();
                
                System.out.println("cookie �� : " + value);
        
            }
         // �޴��� ũ�������� �̸� ���
    		List<Creator> creatorList = service.getCreatorName();
    		mv.addObject("creatorList", creatorList);
    		
            return mv;
        } 
        else {
        	// �޴��� ũ�������� �̸� ���
    		List<Creator> creatorList = service.getCreatorName();
    		mv.addObject("creatorList", creatorList);
    		
            return mv;
        }

	}
	
	@GetMapping("board_write")
	public ModelAndView boardWrite() {
		ModelAndView mv  = new ModelAndView();
		mv.setViewName("community_board_write");
			
		// �޴��� ũ�������� �̸� ���
		List<Creator> creatorList = service.getCreatorName();
		mv.addObject("creatorList", creatorList);
			
		return mv;
	}
	
	@PostMapping("board_write")
	public String write(Board board) {
		service.insertBoard(board);
		return "redirect: /board_content?board_index="+ board.getBoard_index();
	}
	
	@GetMapping("board_update")
	public ModelAndView update(long board_index) {
		Board board = service.getBoard(board_index);
		ModelAndView mv = new ModelAndView("community_board_update", "board", board);
				
		return mv;
	}
	
	@PostMapping("board_update")
	public String update(Board board) {
		service.edit(board);
		return "redirect:board_page";
	}
	
	// ����
	@RequestMapping("board_delete")
	public String deleteBoard(@RequestParam long board_index) {
		service.deleteBoard(board_index);
		return "redirect:board_page";
	}

	// �˻� 
	@RequestMapping("board_search")
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cpStr = request.getParameter("cp");
		String psStr = request.getParameter("ps");
		String filterBy = request.getParameter("filterBy");
		
		HttpSession session = request.getSession();		
		
		//(1) cp 
		int cp = 1;
		if(cpStr == null) {
			Object cpObj = session.getAttribute("cp");
			if(cpObj != null) {
				cp = (Integer)cpObj;
			}
		}else {
			cpStr = cpStr.trim();
			cp = Integer.parseInt(cpStr);
		}
		session.setAttribute("cp", cp);
		
		//(2) ps 
		int ps = 3;
		if(psStr == null) {
			Object psObj = session.getAttribute("ps");
			if(psObj != null) {
				ps = (Integer)psObj;
			}
		}else {
			psStr = psStr.trim();
			int psParam = Integer.parseInt(psStr);
			
			Object psObj = session.getAttribute("ps");
			if(psObj != null) {
				int psSession = (Integer)psObj;
				if(psSession != psParam) {
					cp = 1;
					session.setAttribute("cp", cp);
				}
			}else {
				if(ps != psParam) {
					cp = 1;
					session.setAttribute("cp", cp);
				}
			}
			
			ps = psParam;
		}
		session.setAttribute("ps", ps);		
		
		if (filterBy==null) { 
			String filterByTemp = (String) session.getAttribute("filterBy");
			if (filterByTemp !=null) {
				filterBy = filterByTemp;
				log.info("@@@@@@@@@@@@"+ filterBy);
			} else {
				filterBy = "BOARD_INDEX";
			}
		} else {
			log.info("@@@@@@@@@@@@"+ filterBy);
		}
		session.setAttribute("filterBy", filterBy);
		
		String c_code = request.getParameter("c_code");
		String searchName = request.getParameter("searchName");
		
		if (c_code==null) { 
			String c_codeTemp = (String) session.getAttribute("c_code");
			if (c_codeTemp !=null) {
				c_code = c_codeTemp;
				log.info("@@@@@@@@@@@@"+ c_code);
				session.setAttribute("c_code", c_code);
			} else {
				c_code = "#####";
				session.setAttribute("c_code", c_code);
			}
		} else {
			log.info("@@@@@@@@@@@@"+ c_code);
		}
		session.setAttribute("c_code", c_code);
		
		if (searchName==null) { 
			String searchNameTemp = (String) session.getAttribute("searchName");
			log.info("@@@@@@@@@@@@"+ searchNameTemp);
			if (searchNameTemp !=null) {
				searchName = searchNameTemp;
				log.info("@@@@@@@@@@@@"+ searchName);
				session.setAttribute("searchName", searchName);
			} else {
				searchName = "#####";
				session.setAttribute("searchName", searchName);
			}
		} else {
			log.info("@@@@@@@@@@@@"+ searchName);
		}
		session.setAttribute("searchName", searchName);
		
		
		System.out.println("c_code: "+c_code+", searchName: "+searchName);
		
		ListResult listResult = service.getListResultBySearchS(cp, ps, filterBy, c_code, searchName);
		listResult.setSearchName(searchName);
		listResult.setC_code(c_code);
		request.setAttribute("listResult", listResult);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("community");
		
		// ũ�������� �̸� ���
		List<Creator> creatorList = service.getCreatorName();
		mv.addObject("creatorList", creatorList);
		
		return  mv;
	}
}
