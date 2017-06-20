/**
 * 
 */
package cn.garyxuan.service.impl;

import java.util.List;

import cn.garyxuan.dao.IFoodDao;
import cn.garyxuan.dao.IOrderDetailDao;
import cn.garyxuan.dao.impl.FoodDao;
import cn.garyxuan.dao.impl.OrderDetailDao;
import cn.garyxuan.entity.Food;
import cn.garyxuan.entity.OrderDetail;
import cn.garyxuan.service.IOrderDetailService;

/**
 * @author ll
 *
 */
public class OrderDetailService implements IOrderDetailService {

	IOrderDetailDao orderDetailDao = new OrderDetailDao();
	@Override
	public void add(OrderDetail od) {
		//检查菜系合法性
		IFoodDao foodDao = new FoodDao();
		Food food = foodDao.findById(od.getFoodId());
		if (food == null) {
			System.out.println("使用不存在的菜品");
		} else {
			orderDetailDao.add(od);
		}
	}

	@Override
	public List<OrderDetail> query() {
		return orderDetailDao.query();
	}

	@Override
	public List<OrderDetail> findByOrderid(String id) {
		return orderDetailDao.findByOrderid(id);
	}

}
