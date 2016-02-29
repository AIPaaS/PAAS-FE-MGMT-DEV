package com.aic.paas.cdev.rest;

import java.util.List;

import com.aic.paas.cdev.bean.CPcCompRoom;
import com.aic.paas.cdev.bean.PcCompRoom;

public interface PcCompRoomSvc {
	
	
	
	
	/**
	 * 不分页查询
	 * @param cdt : 条件对象
	 * @param orders : 排序字段, 多字段以逗号分隔
	 * @return 
	 */
	public List<PcCompRoom> queryList(CPcCompRoom cdt, String orders);

	
	
	
	
}
