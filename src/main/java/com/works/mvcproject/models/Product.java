package com.works.mvcproject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pid;	
	private String title;
	private int price;
	private String detail;
	private String img;
	private int cid;
	private int pstatu;
	private int quantity;
	
}
