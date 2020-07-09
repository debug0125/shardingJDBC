package com.ryf.dbsharding.simple;

import com.ryf.dbsharding.simple.dao.OrderDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DbshardingApplication.class)
public class DbshardingApplicationTests {

	@Autowired
	OrderDao orderDao;

	@Test
	public void testInsertOrder(){
		for(int i=1;i<11;i++){
			orderDao.insertOrder(new BigDecimal(i),3L,"SUCCESS");
		}
		for(int i=1;i<11;i++){
			orderDao.insertOrder(new BigDecimal(i),4L,"SUCCESS");
		}
	}

	@Test
	public void testSelectOrderbyIds(){
		List<Long> ids = new ArrayList<>();
		ids.add(487643842846130176L); //db_1 -> order_1
		ids.add(487643842833547265L); //db_1 -> order_2
		ids.add(487643842741272576L); //db_2 -> order_1
		ids.add(487643842414116865L); //db_2 -> order_2
		List<Map> maps = orderDao.selectOrderbyIds(ids);
		System.out.println(maps);
	}

	@Test
	public void testSelectListOrderByOrderId(){
		List<Map> maps = orderDao.selectListOrderByOrderId();
		System.out.println(maps);
		System.out.println(maps.size());
	}

}
