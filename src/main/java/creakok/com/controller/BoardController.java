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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import creakok.com.domain.Board;
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
		
		ListResult listResult = service.getListResultS(cp, ps);
		ModelAndView mv  = new ModelAndView();
		mv.setViewName("community");
		mv.addObject("listResult", listResult);
		
		// ũ�������� �̸� ���
		List<Creator> creatorList = service.getCreatorName();
		mv.addObject("creatorList", creatorList);
		
		return mv;
	}
	
	@RequestMapping("board_page_byView")
	public ModelAndView getListResultByView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cpStr = request.getParameter("cp");
		String psStr = request.getParameter("ps");
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
		
		
		ListResult listResult = service.getListResultByViewS(cp, ps);
		ModelAndView mv  = new ModelAndView();
		mv.setViewName("community");
		mv.addObject("listResult", listResult);
		
		// ũ�������� �̸� ���
		List<Creator> creatorList = service.getCreatorName();
		mv.addObject("creatorList", creatorList);
		
		return mv;
	}
	
	@RequestMapping("board_page_byLike")
	public ModelAndView getListResultByLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cpStr = request.getParameter("cp");
		String psStr = request.getParameter("ps");
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
		
		
		ListResult listResult = service.getListResultByLikeS(cp, ps);
		ModelAndView mv  = new ModelAndView();
		mv.setViewName("community");
		mv.addObject("listResult", listResult);
		
		// ũ�������� �̸� ���
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
	
	
	// for Ajax �˻� 
	
	@GetMapping("board_search01")
	public @ResponseBody List<Board> search01(String board_subject) {
		List<Board> list = service.selectBySubjectS(board_subject);
		return list; // xml, json
	}
	
	@PostMapping("board_search02")
	public @ResponseBody List<Board> search02(String member_name) {
		List<Board> list = service.selectByNameS(member_name);
		return list; // xml, json
	}

}
