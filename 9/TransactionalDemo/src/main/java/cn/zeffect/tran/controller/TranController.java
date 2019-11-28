package cn.zeffect.tran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.zeffect.tran.service.TranService;

@RestController
public class TranController {

	@Autowired
	private TranService tranService;

	@RequestMapping("/test")
	public boolean testTran() throws Exception {
		return tranService.tran("1", "2", 200);
	}
}
