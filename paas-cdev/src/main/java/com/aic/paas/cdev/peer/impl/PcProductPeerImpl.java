package com.aic.paas.cdev.peer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.aic.paas.cdev.bean.CPcProduct;
import com.aic.paas.cdev.bean.PcProduct;
import com.aic.paas.cdev.peer.PcProductPeer;
import com.aic.paas.cdev.rest.PcProductSvc;
import com.binary.core.util.BinaryUtils;
import com.binary.jdbc.Page;

public class PcProductPeerImpl implements PcProductPeer {
	
	
	@Autowired
	PcProductSvc productSvc;

	
	
	@Override
	public Page<PcProduct> queryPage(Integer pageNum, Integer pageSize, CPcProduct cdt, String orders) {
		return productSvc.queryPage(pageNum, pageSize, cdt, orders);
	}

	
	
	@Override
	public List<PcProduct> queryList(CPcProduct cdt, String orders) {
		return productSvc.queryList(cdt, orders);
	}

	
	
	@Override
	public PcProduct queryById(Long id) {
		BinaryUtils.checkEmpty(id, "id");
		return productSvc.queryById(id);
	}
	
	
	

}
