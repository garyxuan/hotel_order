package cn.garyxuan.dao;

import java.util.List;

import cn.garyxuan.entity.Order;
import cn.garyxuan.util.PageBean;

public interface IOrderDao {

	void update(Order order);
	
	List<Order> query();

	void add(Order order);
	
	int getCount();

	void getAll(PageBean<Order> pb);

	int getTotalCount();
}
