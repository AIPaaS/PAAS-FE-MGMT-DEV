package com.aic.paas.cdev.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aic.paas.cdev.bean.PcProduct;
import com.aic.paas.cdev.bean.PcProject;
import com.aic.paas.cdev.bean.ProjectInfo;
import com.aic.paas.cdev.peer.PcProductPeer;
import com.aic.paas.cdev.peer.PcProjectPeer;
import com.binary.framework.util.ControllerUtils;


@Controller
@RequestMapping("/dev/project")
public class PcProjectMvc {

	@Autowired
	PcProjectPeer projectPeer;
	
	@Autowired
	PcProductPeer productPeer;
	

	@RequestMapping("/queryProjectById")
	public void queryProjectById(HttpServletRequest request, HttpServletResponse response, Long id) {
		ProjectInfo info = new ProjectInfo();
		
		PcProject project = projectPeer.queryById(id);
		
		Long productId = project.getProductId();
		PcProduct product = productPeer.queryById(productId);
		
		info.setProduct(product);
		info.setProject(project);
		ControllerUtils.returnJson(request, response, info);
	}
	
	
	
	
	
}
