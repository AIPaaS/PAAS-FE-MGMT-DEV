package com.aic.paas.cdev.peer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.aic.paas.cdev.bean.CPcCompRoom;
import com.aic.paas.cdev.bean.PcCompRoom;
import com.aic.paas.cdev.peer.PcCompRoomPeer;
import com.aic.paas.cdev.rest.PcCompRoomSvc;

public class PcCompRoomPeerImpl implements PcCompRoomPeer{
	
	@Autowired
	PcCompRoomSvc pcComputerSvc;

	
	public List<PcCompRoom> queryList(CPcCompRoom cdt, String orders) {
		return pcComputerSvc.queryList(cdt, orders);
	}

	

}
