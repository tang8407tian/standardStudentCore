package com.biz.student.service;

import com.biz.student.entity.Student;
import com.biz.student.utils.Pagination;
import com.biz.student.vo.StudentsVO;

public interface  StudentService {

		//查询
		public abstract Pagination<Student > selectStudents(int pageNo, int pageSize);
		
		//查询ByID
		public abstract Student selectById(int id);
		//增加
		public abstract boolean addStudents(StudentsVO studentsVO);
		
		//修改
		public abstract void updateStudents(StudentsVO studentsVO);
		
		//删除
		public abstract void deleteStudents(String id);
}
