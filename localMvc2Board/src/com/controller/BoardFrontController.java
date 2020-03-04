package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.BoardCommand;
import com.service.BoardListCommand;
import com.service.BoardRetrieveCommand;
import com.service.BoardWriteCommand;

@WebServlet("*.do")
public class BoardFrontController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String com = requestURI.substring(contextPath.length());
		BoardCommand command = null;
		String nextPage = null;

		// 목록보기
		if (com.equals("/list.do")) {
			command = new BoardListCommand();
			command.execute(request, response);
			nextPage = "list.jsp";
		}
		// 글쓰기 폼
		if (com.equals("/writeui.do")) {
			nextPage = "write.jsp";
		}

		// 글쓰기
		if (com.equals("/write.do")) {
			command = new BoardWriteCommand();
			command.execute(request, response);
			nextPage = "list.do";
		}

		// 글 자세히 보기
		if (com.equals("/retrieve.do")) {
			command = new BoardRetrieveCommand();
			command.execute(request, response);
			nextPage = "retrieve.jsp";
		}

		RequestDispatcher dis = request.getRequestDispatcher(nextPage);
		dis.forward(request, response);

	}

}
