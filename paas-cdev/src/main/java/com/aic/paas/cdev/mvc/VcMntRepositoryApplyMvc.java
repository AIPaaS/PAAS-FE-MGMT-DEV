package com.aic.paas.cdev.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aic.paas.cdev.bean.CVcMntRepositoryApply;
import com.aic.paas.cdev.bean.MntRepositoryApplyInfo;
import com.aic.paas.cdev.peer.VcMntRepositoryApplyPeer;
import com.binary.framework.util.ControllerUtils;
import com.binary.jdbc.Page;



@Controller
@RequestMapping("/vs/mntrespapply")
public class VcMntRepositoryApplyMvc {
	
	
	
	@Autowired
	VcMntRepositoryApplyPeer mntRespApplyPeer;
	
	
	@RequestMapping("/queryInfoPage")
	public void queryInfoPage(HttpServletRequest request, HttpServletResponse response, Integer pageNum, Integer pageSize, CVcMntRepositoryApply cdt, String orders) {
		Page<MntRepositoryApplyInfo> page = mntRespApplyPeer.queryInfoPage(pageNum, pageSize, cdt, orders);
		ControllerUtils.returnJson(request, response, page);
	}


	
	@RequestMapping("/queryInfoList")
	public void queryInfoList(HttpServletRequest request, HttpServletResponse response, CVcMntRepositoryApply cdt, String orders) {
		List<MntRepositoryApplyInfo> ls = mntRespApplyPeer.queryInfoList(cdt, orders);
		ControllerUtils.returnJson(request, response, ls);
	}

	
	@RequestMapping("/queryInfoById")
	public void queryInfoById(HttpServletRequest request, HttpServletResponse response, Long id) {
		MntRepositoryApplyInfo app = mntRespApplyPeer.queryInfoById(id);
		ControllerUtils.returnJson(request, response, app);
	}
	
	
	
	@RequestMapping("/openUpSvn")
	public void openUpSvn(HttpServletRequest request, HttpServletResponse response, Long applyId, String remark) {
		mntRespApplyPeer.openUpSvn(applyId, remark);
		ControllerUtils.returnJson(request, response, true);
	}
	
	
	@RequestMapping("/openUpGit")
	public void openUpGit(HttpServletRequest request, HttpServletResponse response, Long applyId, String respUrl, String remark) {
		mntRespApplyPeer.openUpGit(applyId, respUrl, remark);
		ControllerUtils.returnJson(request, response, true);
	}
	
	

}


