package com.shenchao.test;
import com.shenchao.Config;
import com.shenchao.entity.Course;
import com.shenchao.entity.Student;
import com.shenchao.entity.StudentCourse;
import com.shenchao.repository.StudentCourseRepository;
import com.shenchao.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by shenchao on 2017/4/5.
 */
@ContextConfiguration(classes = Config.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(false)
public class SpringJunit4Test {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentCourseRepository studentCourseRepository;
    @Test
    public void test() throws SQLException {
        System.out.println(dataSource.getConnection());
    }
    @Test
    public void testSave(){
        Student student = new Student();
        studentRepository.save(student);
    }

    @Test
    public void testQuery(){
        List<Student> all = studentRepository.findAll();
        System.out.println(all);
    }

    @Test
    public void testSave1(){
        Student student = new Student();
        student.setSclass("z1411");
        student.setSname("沈超");
        student.setSpassword("123456");
        student.setSsex('男');
        student.setSnumber("14200135111");

        Course course = new Course();
        course.setCname("计算机组成原理");
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setCourse(course);
        studentCourse.setStudent(student);
        studentCourse.setScore(21);

        studentCourseRepository.saveAndFlush(studentCourse);
    }
    
    @Test
	public void testQuery2() throws Exception {
    	Course course = new Course();
    	course.setCid(1);
		Student student = new Student();
		student.setSnumber("14200135111");
		StudentCourse studentCourse = studentCourseRepository.findByStudentAndCourse(student, course);
		System.out.println(studentCourse.getScore());
	}

}
