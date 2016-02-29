package com.aic.paas.cdev.peer;

import java.util.List;

import com.aic.paas.cdev.bean.CPcProduct;
import com.aic.paas.cdev.bean.PcProduct;
import com.binary.jdbc.Page;

public interface PcProductPeer {
	
	
	
	/**
	 * 分页查询
	 * @param pageNum : 指定页码
	 * @param pageSize : 指定页行数
	 * @param cdt : 条件对象
	 * @param orders : 排序字段, 多字段以逗号分隔
	 * @return 
	 */
	public Page<PcProduct> queryPage(Integer pageNum, Integer pageSize, CPcProduct cdt, String orders);


	
	
	
	/**
	 * 不分页查询
	 * @param cdt : 条件对象
	 * @param orders : 排序字段, 多字段以逗号分隔
	 * @return 
	 */
	public List<PcProduct> queryList(CPcProduct cdt, String orders);

	
	
	
	
	/**
	 * 跟据主键查询
	 * @param id : 主键ID
	 * @return 操作员表[SYS_OP]映射对象
	 */
	public PcProduct queryById(Long id);

}
