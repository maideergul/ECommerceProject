package com.works.mvcproject.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Table(name = "productcid_view")
@Entity
@Data
public class ProductCidView {
	
	@Id	
	private int pid;
	private int cid;
	private String detail;
	private String img;
	private String price;
	private String title;
	private String categoryname;

}
