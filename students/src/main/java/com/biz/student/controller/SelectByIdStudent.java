package com.biz.student.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.student.entity.Student;
import com.biz.student.service.StudentService;
import com.biz.student.service.impl.StudentServiceImpl;



/**
 * Servlet implementation class SelectByIdStudent
 */
@WebServlet("/SelectByIdStudent")
public class SelectByIdStudent extends HttpServlet {
	private StudentService service = new StudentServiceImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String ids = request.getParameter("id");
	
		System.out.println("idservlet : " + ids);
		
		int id = Integer.parseInt(ids);
		
		//查询后的数据
		Student students = service.selectById(id);
		
		
		System.out.println("servletByid name" + students.getName());
		//把数据放入域中
		request.setAttribute("ByidStudent", students);
		
		
		request.getRequestDispatcher("update.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
