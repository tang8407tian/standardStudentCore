package com.biz.student.dao.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.biz.student.dao.StudentDao;
import com.biz.student.entity.Student;
import com.biz.student.redisConnection.Redis;
import com.biz.student.utils.Pagination;

import redis.clients.jedis.Jedis;


@Component("StudentDaoImpl")
public class StudentDaoImpl implements StudentDao{
	static Redis redis = new Redis();
	static Jedis jedis = redis.getJedis();
	
	
	/**
	 * 查询分页排序
	 */
	@Override
	public Pagination<Student> selectStudentMessage(String pageNo, String pageSize) {
		List< Student > studentslist = new ArrayList<Student>();

		//当前页码
		long  startpage = Long.parseLong(pageNo);
		
		//每一页的起始编号
		long start = startpage;
		//每一页的结束编号
		long end = Long.parseLong(pageSize) -1;
		
		//每一页之间的间隔数 ：每页显示多少数据
		int number= 4;
		
		//当为第一页时
		if (startpage ==  1) {
			start = 0;
			end = start + number -1;
		}
		
		//页数大于1时
		if(startpage >1){
			start = (startpage-1)*number;
			end = start + number -1 ;
		}
		//System.out.println("dao: "+ "start: " + start + " end: " + end);

		Set<String> set =  jedis.zrevrange("stu", start, end) ; //jedis.zrange("stu", start, end);
		
		
		Iterator<String> it = set.iterator();
		while(it.hasNext()){
			String id = it.next();
			String name = jedis.hget( id, "name");
			String birthday  = jedis.hget(id , "birthday");
			String description = jedis.hget(id, "description");
			String avgscore = jedis.hget(id, "avgscore");
			
			int ids = Integer.parseInt(id);
			
			Student seStudent = new Student(ids, name, birthday, description, avgscore);
		
			studentslist.add(seStudent);
		}
		
		//统计总数
		long total = jedis.zcard("stu");
		int totalRecord =(int)total;
		
		
		int starts = Integer.parseInt(pageNo);
		int ends = Integer.parseInt(pageSize);
		//
		Pagination<Student> studnetPage = new Pagination<Student>(studentslist, totalRecord, starts, ends);
		
		
		return studnetPage;
		
		
		
	}

	@Override
	public Student selectById(int id) {

		String ids = id+"";
		Student Student=null;
		if( !ids.isEmpty()){
			//获取数据
			String name = jedis.hget( ids, "name");
			String birthday  = jedis.hget(ids , "birthday");
			String description = jedis.hget(ids, "description");
			String avgscore = jedis.hget(ids, "avgscore");
			
			int ido = Integer.parseInt(ids);
			//格式化时间  string-->date
			//Date birth = DateUtil.getDate2(birthday);
			 Student = new Student(ido, name, birthday, description, avgscore);
			
		
			 System.out.println( "name" + name + "  studnetName : " +Student.getName() );
	}
	
		
	return Student ;
	}

	@Override
	public boolean addStudent(Student student) {
		String  id = student.getId() +""; 
	 
		 //判断ID是否存在
		 if (jedis.exists(id)) {
				return false;
			} else {
				HashMap<String,String> hashMap = new HashMap<String,String>();
				 hashMap.put("name", student.getName());
				 hashMap.put("birthday", student.getBirthday() );
				 hashMap.put("description", student.getDescription());
				 hashMap.put("avgscore", student.getAvgscore());
				
				 
				// System.out.println("daoAdd : " +students.getBirthday() );
				 
				double avgscores = Double.parseDouble(student.getAvgscore());
			
				//把学生ID储存到zset中根据平均分数排序
				 jedis.zadd("stu" , avgscores, id);
				 
				 //根据ID把学生信息添加到hash中
				jedis.hmset(id, hashMap);
				
				return true;
			}
		 
		
		
	
	}

	@Override
	public void updateStudent(Student student) {
		
		int id = student.getId();
		String ids = id + "";
		String name = student.getName();
		String   birthday = student.getBirthday();
		String description = student.getDescription();
		String avgscore = student.getAvgscore();
		
		jedis.hset(ids, "id", ids);
		jedis.hset(ids, "name", name);
		jedis.hset(ids, "birthday",birthday);
		jedis.hset(ids, "description", description);
		jedis.hset(ids, "avgscore", avgscore);
		
		
		double avgscores = Double.parseDouble(student.getAvgscore());

		jedis.zadd("stu", avgscores, ids);
		
		
	}

	@Override
	public void deleteStudent(int id) {
		String ids = id + "";
		//根据ID删除zset里面内容    zerm key member
		jedis.zrem("stu", ids);	
		//根据ID删除hash里面内容
		jedis.del(ids);
		
	}

}
