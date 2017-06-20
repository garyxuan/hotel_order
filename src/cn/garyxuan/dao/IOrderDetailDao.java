package cn.garyxuan.dao;

import java.util.List;

import cn.garyxuan.entity.OrderDetail;

/**
 * 订单详情dao接口
 * @author garyxuan
 *
 */
public interface IOrderDetailDao {
	
	void add(OrderDetail od);
	
	List<OrderDetail> query();
	
	List<OrderDetail> findByOrderid(String id);
}
