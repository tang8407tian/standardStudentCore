package com.biz.student.service;

import com.biz.student.entity.Student;
import com.biz.student.utils.Pagination;
import com.biz.student.vo.StudentsVO;

public interface  StudentService {

		//��ѯ
		public abstract Pagination<Student > selectStudents(int pageNo, int pageSize);
		
		//��ѯByID
		public abstract Student selectById(int id);
		//����
		public abstract boolean addStudents(StudentsVO studentsVO);
		
		//�޸�
		public abstract void updateStudents(StudentsVO studentsVO);
		
		//ɾ��
		public abstract void deleteStudents(String id);
}
