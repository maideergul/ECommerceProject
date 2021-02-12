package com.works.mvcproject.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import lombok.Data;

@Entity
@Data
@NamedStoredProcedureQuery(name = "CartProc.getCart", procedureName = "cart_proc", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_statu", type = Integer.class) })

public class CartProc {

	@Id
	private int pid;
	private int cid;
	private String detail;
	private String img;
	private String price;
	private String title;
	private int pstatu;
	private int quantity;

}
