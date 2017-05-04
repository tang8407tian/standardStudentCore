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
import com.biz.student.vo.StudentsVO;



/**
 * Servlet implementation class UpdateStudent
 */
@WebServlet("/UpdateStudent")
public class UpdateStudent extends HttpServlet {
	private StudentService service = new StudentServiceImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");//post 乱码问题
		response.setContentType("text/html;charset=utf-8"); 
		String errormessage = null;
		
		String  ids = request.getParameter("id");
		
		String avgscore = request.getParameter("avgscore");
		
		//id
		boolean idNumber = ids.matches("[0-9]+");
		//avg
		boolean avg = avgscore.matches("[0-9]+");
		System.out.println("update---idNumber:" +idNumber+"avg:" + avg);
		if (idNumber == false || avg == false || ids.isEmpty()) {
			errormessage = ids;
			
			request.setAttribute("IDMessage", errormessage);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			
			int id = Integer.parseInt(ids.trim()) ;
			
			String name = request.getParameter("name");
			
			String birthday = request.getParameter("birthday");
			//格式化时间  string --> date
			//Date birth =  DateUtil.getDate2(birthday);
			
			
			String description = request.getParameter("description");
			
		
			Student students = new Student(id, name, birthday, description, avgscore);
			StudentsVO studentsVO = new StudentsVO(id, name, birthday, description, avgscore);
			
			service.updateStudents(studentsVO);
			
			
			
			request.getRequestDispatcher("/ListStudent").forward(request, response);

		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
