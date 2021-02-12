package com.works.mvcproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.works.mvcproject.models.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	
	Admin findByMailAndPass(String mail, String pass);	
}