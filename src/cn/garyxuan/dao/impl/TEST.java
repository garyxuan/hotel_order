/**
 * 
 */
package cn.garyxuan.dao.impl;

import java.util.Date;

import java.sql.Timestamp;

import cn.garyxuan.dao.IDinnerTableDao;
import cn.garyxuan.dao.IFoodDao;
import cn.garyxuan.dao.IFoodTypeDao;
import cn.garyxuan.dao.IOrderDao;
import cn.garyxuan.dao.IOrderDetailDao;
import cn.garyxuan.entity.DinnerTable;
import cn.garyxuan.entity.Food;
import cn.garyxuan.entity.Order;
import cn.garyxuan.service.IOrderService;
import cn.garyxuan.service.impl.OrderService;
import cn.garyxuan.util.Condition;
import cn.garyxuan.util.PageBean;

/**
 * @author ll
 *
 */
public class TEST {
	public static void main(String[] args) {
		IOrderDao orderdao = new OrderDao();
		IOrderService orderService = new OrderService(); 
		IFoodDao dao = new FoodDao();
		IDinnerTableDao dinnerTableDao = new DinnerTableDao();
		IFoodTypeDao foodTypeDao = new FoodTypeDao();
		IOrderDetailDao orderDetailDao = new OrderDetailDao();
		System.out.println(dao.query());
		System.out.println(orderdao.query());
		System.out.println(foodTypeDao.query());
		System.out.println(orderdao.getCount());
		System.out.println(orderDetailDao.query());
		
		Order order = new Order();
		Date now = new Date();
		order.setId(String.valueOf(now.getTime()));
		order.setOrderDate(new Timestamp(now.getTime()));
		order.setOrderStatus(0);
		order.setTableId(2);
		order.setTotalPrice(500.0);
		orderService.add(order);
	
		
//		System.out.println(orderdao.getCount());
//		System.out.println(orderdao.query());
		
		
		
	}
}
