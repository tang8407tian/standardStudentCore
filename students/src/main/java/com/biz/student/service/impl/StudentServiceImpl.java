package com.biz.student.service.impl;




import org.springframework.stereotype.Component;

import com.biz.student.dao.StudentDao;
import com.biz.student.dao.Impl.StudentDaoImpl;
import com.biz.student.entity.Student;
import com.biz.student.service.StudentService;
import com.biz.student.utils.Pagination;
import com.biz.student.vo.StudentsVO;

@Component("StudentServiceImpl")
public class StudentServiceImpl implements  StudentService {


	private  StudentDao students = new StudentDaoImpl();
	
	 
	//²éÑ¯
	@Override
	public Pagination<Student> selectStudents(int pageNo, int pageSize) {
		String pageNub = pageNo + "";
		String pagesize = pageSize + "";
		
		Pagination<Student> stu = null;
		
		stu = students.selectStudentMessage(pageNub, pagesize);
	
		
		return stu;
	}
	
	
	//Ìí¼Ó
	@Override
	public boolean addStudents(StudentsVO studentsVO) {
		
		Student student =  new Student(studentsVO.getId(), studentsVO.getName(), studentsVO.getBirthday(), studentsVO.getDescription(), studentsVO.getAvgscore());
		
		
		return  students.addStudent(student);
	}

	//ÐÞ¸Ä
	@Override
	public void updateStudents(StudentsVO studentsVO) {
		
	Student student = 	new Student(studentsVO.getId(), studentsVO.getName(), studentsVO.getBirthday(), studentsVO.getDescription(), studentsVO.getAvgscore());
		
		students.updateStudent(student);
	}
	
	
	//É¾³ý
	@Override
	public void deleteStudents(String  id) {
		int ids = Integer.parseInt(id); 
		students.deleteStudent(ids);
	}


	@Override
	public Student selectById(int id) {
		
		return students.selectById(id);
		 
	}

	
	
}
