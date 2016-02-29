package com.aic.paas.cdev.bean;

import java.io.Serializable;

import com.aic.paas.frame.cross.bean.WsMerchent;

public class MntRepositoryApplyInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	/** 申请单 **/
	private VcMntRepositoryApply apply;
	
	
	/** 申请租户 **/
	private WsMerchent merchent;

	
	

	public VcMntRepositoryApply getApply() {
		return apply;
	}


	public void setApply(VcMntRepositoryApply apply) {
		this.apply = apply;
	}


	public WsMerchent getMerchent() {
		return merchent;
	}


	public void setMerchent(WsMerchent merchent) {
		this.merchent = merchent;
	}
	
	
	
	
	

}
