package com.works.mvcproject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int aid;
	private String name;
	private String mail;
	private String pass;
	private String remember;

}