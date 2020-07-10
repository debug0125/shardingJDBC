package com.ryf.dbsharding.simple;

import com.ryf.dbsharding.simple.dao.OrderDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DbshardingApplication.class)
public class DbshardingApplicationTests {

	@Autowired
	OrderDao orderDao;

	@Test
	public void testInsertOrder(){
		for(int i=1;i<5;i++){
			orderDao.insertOrder(new BigDecimal(i),1L,"SUCCESS",new Date());
		}
		for(int i=1;i<5;i++){
			orderDao.insertOrder(new BigDecimal(i),2L,"SUCCESS",new Date());
		}
	}

	@Test
	public void testSelectOrderbyIds(){
		List<Long> ids = new ArrayList<>();
		ids.add(488330710763962368L); //db_1 -> order_1
		ids.add(488330710776545281L); //db_1 -> order_2
//		ids.add(487643842741272576L); //db_2 -> order_1
//		ids.add(488310734850097153L); //db_2 -> order_2
		List<Map> maps = orderDao.selectOrderbyIds(ids);
		System.out.println(maps);
	}

	@Test
	public void testSelectOrderbyIdsAndUserId(){
		List<Long> ids = new ArrayList<>();
		ids.add(488330710763962368L); //db_1 -> order_1
		ids.add(488330710776545281L); //db_1 -> order_2
//		ids.add(487643842741272576L); //db_2 -> order_1
//		ids.add(488310734850097153L); //db_2 -> order_2
		List<Map> maps = orderDao.selectOrderbyIdsAndUserId(ids,2L);
		System.out.println(maps);
	}

	@Test
	public void testSelectOrderbyIdsAndUserIds(){
		List<Long> ids = new ArrayList<>();
		ids.add(488330710763962368L); //db_1 -> order_1
//		ids.add(488330710776545281L); //db_1 -> order_2
		ids.add(488330710726213632L); //db_2 -> order_1
//		ids.add(488310734850097153L); //db_2 -> order_2
		List<Long> userIds = new ArrayList<>();
		userIds.add(1L);
		userIds.add(2L);
		List<Map> maps = orderDao.selectOrderbyIdsAndUserIds(ids,userIds);
		System.out.println(maps);
	}

	@Test
	public void testSelectListOrderByOrderId(){
		List<Map> maps = orderDao.selectListOrderByOrderId();
		System.out.println(maps);
		System.out.println(maps.size());
	}

}
