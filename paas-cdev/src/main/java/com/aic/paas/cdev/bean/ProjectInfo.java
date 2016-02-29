package com.aic.paas.cdev.bean;

import java.io.Serializable;

public class ProjectInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	/** 工程 **/
	private PcProject project;
	
	/** 产品 **/
	private PcProduct product;


	public PcProject getProject() {
		return project;
	}


	public void setProject(PcProject project) {
		this.project = project;
	}


	public PcProduct getProduct() {
		return product;
	}


	public void setProduct(PcProduct product) {
		this.product = product;
	}
	

}
