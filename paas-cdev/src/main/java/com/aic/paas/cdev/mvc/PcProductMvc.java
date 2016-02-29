package com.aic.paas.cdev.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aic.paas.cdev.bean.PcProduct;
import com.aic.paas.cdev.peer.PcProductPeer;
import com.binary.framework.util.ControllerUtils;


@Controller
@RequestMapping("/dev/product")
public class PcProductMvc {
	
	@Autowired
	PcProductPeer productPeer;
	
	
	
	@RequestMapping("/queryProductById")
	public void queryProductById(HttpServletRequest request, HttpServletResponse response, Long id) {
		PcProduct p = productPeer.queryById(id);
		ControllerUtils.returnJson(request, response, p);
	}
	
	
	
}
