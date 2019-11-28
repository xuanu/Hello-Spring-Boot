package cn.zeffect.tran.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zeffect.tran.dao.UserDao;

@Service
public class TranService {

	@Autowired
	private UserDao userDao;

	@Transactional
	public boolean tran(String a, String b, double money) {
		return userDao.addMoney(b, money) && userDao.removeMoney(a, money);
	}
}
