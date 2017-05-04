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
import com.biz.student.utils.Pagination;



/**
 * Servlet implementation class ListStudent
 */
@WebServlet("/ListStudent")
public class ListStudent extends HttpServlet {
	private StudentService service = new StudentServiceImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//��ҳ
		int pageSize=4;//ÿһҳ��������
		int pageNo = -1;
		String pageNoText = request.getParameter("pageNo");//pageNo:��ǰҳ��
		if (pageNoText == null) {
			pageNo=1;   //��ҳ��Ϊ��  Ĭ��Ϊ��һҳ
		} else {
			//��Ȼ��Ϊ��ǰҳ��
			pageNo = Integer.parseInt(pageNoText);
		}
		
		/*if(pageNo >1){
			pageNo = (pageNo-1)*3;
			pageSize = pageNo +2;
		}*/
		
		/*pageSize = pageNo;*/
		
		
		System.out.println("servlet: " + "pageNo: " + pageNo + " pageSize: " + pageSize );
		
		Pagination<Student> studentPage = service.selectStudents(pageNo, pageSize);
		
		request.setAttribute("studentPage", studentPage);
		
		request.getRequestDispatcher("studentList.jsp").forward(request, response);

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doGet(request, response);
	}

}
