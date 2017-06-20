package cn.garyxuan.service;

import java.util.List;

import cn.garyxuan.entity.Order;
import cn.garyxuan.util.PageBean;

/**
 * @author 订单service接口
 *
 */
public interface IOrderService {
	void update(Order order);
	
	List<Order> query();

	void add(Order order);
	
	int getCount();

	void getAll(PageBean<Order> pb);
}
