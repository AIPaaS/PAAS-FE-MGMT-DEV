package com.aic.paas.cdev.peer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.aic.paas.cdev.bean.CPcProject;
import com.aic.paas.cdev.bean.PcProject;
import com.aic.paas.cdev.peer.PcProjectPeer;
import com.aic.paas.cdev.rest.PcProjectSvc;
import com.binary.core.util.BinaryUtils;
import com.binary.jdbc.Page;

public class PcProjectPeerImpl implements PcProjectPeer {
	
	
	@Autowired
	PcProjectSvc projectSvc;

	
	
	@Override
	public Page<PcProject> queryPage(Integer pageNum, Integer pageSize, CPcProject cdt, String orders) {
		return projectSvc.queryPage(pageNum, pageSize, cdt, orders);
	}

	
	
	@Override
	public List<PcProject> queryList(CPcProject cdt, String orders) {
		return projectSvc.queryList(cdt, orders);
	}
	
	

	@Override
	public PcProject queryById(Long id) {
		BinaryUtils.checkEmpty(id, "id");
		return projectSvc.queryById(id);
	}
	
	
	
	

}
