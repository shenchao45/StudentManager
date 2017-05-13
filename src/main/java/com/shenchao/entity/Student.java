package com.shenchao.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Created by shenchao on 2017/4/5.
 */


@Entity
public class Student {
	public enum UserType{
		STUDENT,TEACHER,ADMIN
	}
    @Id
    private String snumber;
    private String sname;
    private char ssex;//男为true
    private String sclass;
    private String spassword;
    @Enumerated(EnumType.STRING)
    private UserType userType = UserType.STUDENT;
	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@OneToMany(cascade=CascadeType.REMOVE,mappedBy="student")
    private List<StudentCourse> studentCourses;
    public List<StudentCourse> getStudentCourses() {
		return studentCourses;
	}

	public void setStudentCourses(List<StudentCourse> studentCourses) {
		this.studentCourses = studentCourses;
	}

	public String getSpassword() {
        return spassword;
    }

    public void setSpassword(String spassword) {
        this.spassword = spassword;
    }

    public String getSnumber() {
        return snumber;
    }

    public void setSnumber(String snumber) {
        this.snumber = snumber;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public char getSsex() {
        return ssex;
    }

    public void setSsex(char ssex) {
        this.ssex = ssex;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }
}
