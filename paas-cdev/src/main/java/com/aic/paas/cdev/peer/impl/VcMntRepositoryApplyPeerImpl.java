package com.aic.paas.cdev.peer.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.aic.paas.cdev.bean.CVcMntRepositoryApply;
import com.aic.paas.cdev.bean.MntRepositoryApplyInfo;
import com.aic.paas.cdev.bean.VcMntRepositoryApply;
import com.aic.paas.cdev.peer.VcMntRepositoryApplyPeer;
import com.aic.paas.cdev.rest.PcProductSvc;
import com.aic.paas.cdev.rest.PcProjectSvc;
import com.aic.paas.cdev.rest.VcMntRepositoryApplySvc;
import com.aic.paas.frame.cross.bean.CWsMerchent;
import com.aic.paas.frame.cross.bean.WsMerchent;
import com.aic.paas.frame.cross.rest.MerchentSvc;
import com.binary.core.util.BinaryUtils;
import com.binary.jdbc.Page;

public class VcMntRepositoryApplyPeerImpl implements VcMntRepositoryApplyPeer {
	
	
	@Autowired
	VcMntRepositoryApplySvc mntRespApplySvc;
	
	
	@Autowired
	MerchentSvc merchentSvc;
	
	@Autowired
	PcProductSvc productSvc;
	
	@Autowired
	PcProjectSvc projectSvc;
	

	@Override
	public Page<VcMntRepositoryApply> queryPage(Integer pageNum, Integer pageSize, CVcMntRepositoryApply cdt, String orders) {
		return mntRespApplySvc.queryPage(pageNum, pageSize, cdt, orders);
	}
	
	

	@Override
	public List<VcMntRepositoryApply> queryList(CVcMntRepositoryApply cdt, String orders) {
		return mntRespApplySvc.queryList(cdt, orders);
	}

	
	
	@Override
	public VcMntRepositoryApply queryById(Long id) {
		BinaryUtils.checkEmpty(id, "id");
		return mntRespApplySvc.queryById(id);
	}
	
	
	
	
	private List<MntRepositoryApplyInfo> fillApplyInfo(List<VcMntRepositoryApply> ls) {
		List<MntRepositoryApplyInfo> infos = new ArrayList<MntRepositoryApplyInfo>();
		if(ls!=null && ls.size()>0) {
			Set<Long> mntids = new HashSet<Long>();
			for(int i=0; i<ls.size(); i++) {
				VcMntRepositoryApply apply = ls.get(i);
				Long mntId = apply.getMntId();
				
				MntRepositoryApplyInfo info = new MntRepositoryApplyInfo();
				info.setApply(apply);
				infos.add(info);
				mntids.add(mntId);
			}
			
			if(mntids.size() > 0) {
				CWsMerchent mntcdt = new CWsMerchent();
				mntcdt.setIds(mntids.toArray(new Long[0]));
				List<WsMerchent> mntls = merchentSvc.queryList(mntcdt, null);
				
				if(mntls.size() > 0) {
					Map<Long, WsMerchent> mntmap = BinaryUtils.toObjectMap(mntls, "id");
					for(int i=0; i<infos.size(); i++) {
						MntRepositoryApplyInfo info = infos.get(i);
						info.setMerchent(mntmap.get(info.getApply().getMntId()));
					}
				}
			}
		}
		return infos;
	}
	
	

	@Override
	public Page<MntRepositoryApplyInfo> queryInfoPage(Integer pageNum, Integer pageSize, CVcMntRepositoryApply cdt, String orders) {
		Page<VcMntRepositoryApply> page = queryPage(pageNum, pageSize, cdt, orders);
		List<VcMntRepositoryApply> ls = page.getData();
		List<MntRepositoryApplyInfo> infols = fillApplyInfo(ls);
		return new Page<MntRepositoryApplyInfo>(page.getPageNum(), page.getPageSize(), page.getTotalRows(), page.getTotalPages(), infols);
	}

	
	
	@Override
	public List<MntRepositoryApplyInfo> queryInfoList(CVcMntRepositoryApply cdt, String orders) {
		List<VcMntRepositoryApply> ls = queryList(cdt, orders);
		return fillApplyInfo(ls);
	}

	
	
	@Override
	public MntRepositoryApplyInfo queryInfoById(Long id) {
		BinaryUtils.checkEmpty(id, "id");
		VcMntRepositoryApply p = queryById(id);
		if(p != null) {
			List<VcMntRepositoryApply> ls = new ArrayList<VcMntRepositoryApply>();
			ls.add(p);
			return fillApplyInfo(ls).get(0);
		}
		return null;
	}

	
	


	@Override
	public void openUpSvn(Long applyId, String remark) {
		BinaryUtils.checkEmpty(applyId, "id");
		mntRespApplySvc.openUpSvn(applyId, remark);
	}



	@Override
	public void openUpGit(Long applyId, String respUrl, String remark) {
		BinaryUtils.checkEmpty(applyId, "id");
		BinaryUtils.checkEmpty(respUrl, "respUrl");
		mntRespApplySvc.openUpGit(applyId, respUrl, remark);
	}


	
	
	
	
}




