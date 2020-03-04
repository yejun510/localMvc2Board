package com.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dao.BoardDAO;
public class BoardDeleteCommand  implements BoardCommand{
	@Override
	public void execute(HttpServletResponse response , HttpServletRequest request) {
		String num = request.getParameter("num");
		BoardDAO dao = new BoardDAO();
		dao.delete(num);
	}
}
