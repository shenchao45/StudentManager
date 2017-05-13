package com.shenchao.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

@Service
public class StudentCourseService {
	@PersistenceContext
	private EntityManager entityManager;
}
