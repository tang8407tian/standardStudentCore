package com.biz.student.dao;

import com.biz.student.entity.Student;
import com.biz.student.utils.Pagination;

public interface StudentDao {
	


	// 展示信息 分页查询
	@interfaceDao
	public  Pagination<Student> selectStudentMessage(String pageNo, String pageSize);
	
	//根据ID查询
	@interfaceDao
	public Student selectById(int id);

	// 增加
	public boolean addStudent(Student student);

	// 修改
	public void updateStudent(Student student);

	// 删除
	public void deleteStudent(int id);
}
