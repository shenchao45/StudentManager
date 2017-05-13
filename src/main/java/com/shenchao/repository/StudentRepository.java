package com.shenchao.repository;

import com.shenchao.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by shenchao on 2017/4/5.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student,String> {
	Student findBySnumberAndSpassword(String snumber,String spassword);
}
