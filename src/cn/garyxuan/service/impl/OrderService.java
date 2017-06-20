/**
 * 
 */
package cn.garyxuan.service.impl;

import java.util.List;

import cn.garyxuan.dao.IDinnerTableDao;
import cn.garyxuan.dao.IOrderDao;
import cn.garyxuan.dao.impl.DinnerTableDao;
import cn.garyxuan.dao.impl.OrderDao;
import cn.garyxuan.entity.DinnerTable;
import cn.garyxuan.entity.Order;
import cn.garyxuan.service.IOrderService;
import cn.garyxuan.util.PageBean;

/**
 * @author 订单service实现
 *
 */
public class OrderService implements IOrderService {

	IOrderDao orderDao = new OrderDao();
	@Override
	public void update(Order order) {
		orderDao.update(order);
	}

	@Override
	public List<Order> query() {
		// TODO Auto-generated method stub
		return orderDao.query();
	}

	@Override
	public void add(Order order) {
		//检查餐桌合法性
		IDinnerTableDao tableDao = new DinnerTableDao();
		DinnerTable table = tableDao.findById(order.getTableId());
		if (table == null) {
			System.out.println("使用不存在的餐桌下单！");
		} else {
			if (table.getTableStatus() == DinnerTable.BOOKED) {
				System.out.println("使用已预订的餐桌下单！");
			} else {
				orderDao.add(order);
			}
		}
	}

	@Override
	public int getCount() {
		return orderDao.getCount();
	}

	@Override
	public void getAll(PageBean<Order> pb) {
		orderDao.getAll(pb);
	}

}
