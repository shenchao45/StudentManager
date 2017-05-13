package com.shenchao.repository;

import com.shenchao.entity.Course;
import com.shenchao.entity.Student;
import com.shenchao.entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by shenchao on 2017/4/5.
 */
@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse,Integer>,JpaSpecificationExecutor {
	@Query("FROM StudentCourse sc where sc.student=?1 and sc.course=?2")
	public StudentCourse findByStudentAndCourse(Student student,Course course);
}
