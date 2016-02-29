package com.aic.paas.cdev.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aic.paas.cdev.bean.CPcCompRoom;
import com.aic.paas.cdev.bean.PcCompRoom;
import com.aic.paas.cdev.peer.PcCompRoomPeer;
import com.aic.paas.frame.cross.bean.DropRecord;
import com.aic.paas.frame.util.ComponentUtil;
import com.binary.framework.util.ControllerUtils;

@Controller
@RequestMapping("/res/comproom")
public class PcCompRoomMvc {
	
	@Autowired
	PcCompRoomPeer pcCompRoomPeer;
	

	@RequestMapping("/getCompRoomDropList")
    public void getCompRoomDropList(HttpServletRequest request, HttpServletResponse response, Boolean addEmpty, Boolean addAttr) {
		CPcCompRoom cdt = new CPcCompRoom();
        cdt.setStatus(1);
       List<PcCompRoom> list = pcCompRoomPeer.queryList(cdt, "ROOM_CODE, ID");
      
       List<DropRecord> dropList = ComponentUtil.toDropList(list, "ID", "roomName", addAttr, addEmpty);
       ControllerUtils.returnJson(request, response, dropList);
    }
	
	
}
