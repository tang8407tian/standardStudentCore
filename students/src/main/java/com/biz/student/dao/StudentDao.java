package com.biz.student.dao;

import com.biz.student.entity.Student;
import com.biz.student.utils.Pagination;

public interface StudentDao {
	


	// չʾ��Ϣ ��ҳ��ѯ
	@interfaceDao
	public  Pagination<Student> selectStudentMessage(String pageNo, String pageSize);
	
	//����ID��ѯ
	@interfaceDao
	public Student selectById(int id);

	// ����
	public boolean addStudent(Student student);

	// �޸�
	public void updateStudent(Student student);

	// ɾ��
	public void deleteStudent(int id);
}
