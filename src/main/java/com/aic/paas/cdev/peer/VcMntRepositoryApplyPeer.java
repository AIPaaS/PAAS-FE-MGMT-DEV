package com.aic.paas.cdev.peer;

import java.util.List;

import com.aic.paas.cdev.bean.CVcMntRepositoryApply;
import com.aic.paas.cdev.bean.MntRepositoryApplyInfo;
import com.aic.paas.cdev.bean.VcMntRepositoryApply;
import com.binary.jdbc.Page;

public interface VcMntRepositoryApplyPeer {
	
	
	
	
	/**
	 * 分页查询
	 * @param pageNum : 指定页码
	 * @param pageSize : 指定页行数
	 * @param cdt : 条件对象
	 * @param orders : 排序字段, 多字段以逗号分隔
	 * @return 
	 */
	public Page<VcMntRepositoryApply> queryPage(Integer pageNum, Integer pageSize, CVcMntRepositoryApply cdt, String orders);


	
	
	
	/**
	 * 不分页查询
	 * @param cdt : 条件对象
	 * @param orders : 排序字段, 多字段以逗号分隔
	 * @return 
	 */
	public List<VcMntRepositoryApply> queryList(CVcMntRepositoryApply cdt, String orders);

	
	
	
	
	/**
	 * 跟据主键查询
	 * @param id : 主键ID
	 * @return 操作员表[SYS_OP]映射对象
	 */
	public VcMntRepositoryApply queryById(Long id);
	
	
	
	
	
	
	/**
	 * 分页查询
	 * @param pageNum : 指定页码
	 * @param pageSize : 指定页行数
	 * @param cdt : 条件对象
	 * @param orders : 排序字段, 多字段以逗号分隔
	 * @return 
	 */
	public Page<MntRepositoryApplyInfo> queryInfoPage(Integer pageNum, Integer pageSize, CVcMntRepositoryApply cdt, String orders);


	
	
	
	/**
	 * 不分页查询
	 * @param cdt : 条件对象
	 * @param orders : 排序字段, 多字段以逗号分隔
	 * @return 
	 */
	public List<MntRepositoryApplyInfo> queryInfoList(CVcMntRepositoryApply cdt, String orders);

	
	
	
	
	/**
	 * 跟据主键查询
	 * @param id : 主键ID
	 * @return 操作员表[SYS_OP]映射对象
	 */
	public MntRepositoryApplyInfo queryInfoById(Long id);
	
	
		
	
	/**
	 * 开通SVN
	 * @param applyId 申请单ID
	 * @param remark 备注
	 * @return
	 */
	public void openUpSvn(Long applyId, String remark);
	
		
	
	/**
	 * 开通
	 * @param applyId 申请单ID
	 * @param respUrl 开通版本库URL
	 * @param remark 备注
	 * @return
	 */
	public void openUpGit(Long applyId, String respUrl, String remark);
	
	
	

}
