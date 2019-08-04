package com.aca.rest.dugoutioc;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.aca.rest.dugoutdao.BaseballDao;
import com.aca.rest.dugoutdao.BaseballDaoDemo;
import com.aca.rest.dugoutdao.BaseballDaoImpl;
import com.aca.rest.dugoutdao.BaseballDaoMock;
import com.aca.rest.dugoutservice.BaseballService;

public class DugoutDependencyBinder extends AbstractBinder {
	
	@Override
	protected void configure() {
		System.out.println("....injecting concrete objects into dugout app");
		this.bind(BaseballService.class).to(BaseballService.class);
//		this.bind(BaseballDaoMock.class).to(BaseballDao.class);
		this.bind(BaseballDaoImpl.class).to(BaseballDao.class);
//		this.bind(BaseballDaoDemo.class).to(BaseballDao.class);
	}


}
