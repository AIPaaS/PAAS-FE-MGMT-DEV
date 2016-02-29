package com.aic.paas.cdev.peer;

import java.util.List;

import com.aic.paas.cdev.bean.CPcProject;
import com.aic.paas.cdev.bean.PcProject;
import com.binary.jdbc.Page;

public interface PcProjectPeer {
	
	
	
	
	/**
	 * 分页查询
	 * @param pageNum : 指定页码
	 * @param pageSize : 指定页行数
	 * @param cdt : 条件对象
	 * @param orders : 排序字段, 多字段以逗号分隔
	 * @return 
	 */
	public Page<PcProject> queryPage(Integer pageNum, Integer pageSize, CPcProject cdt, String orders);


	
	
	
	/**
	 * 不分页查询
	 * @param cdt : 条件对象
	 * @param orders : 排序字段, 多字段以逗号分隔
	 * @return 
	 */
	public List<PcProject> queryList(CPcProject cdt, String orders);

	
	
	
	
	/**
	 * 跟据主键查询
	 * @param id : 主键ID
	 * @return 操作员表[SYS_OP]映射对象
	 */
	public PcProject queryById(Long id);
	
	

}
