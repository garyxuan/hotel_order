package cn.garyxuan.service;

import java.util.List;

import cn.garyxuan.entity.OrderDetail;

/**
 * @author 订单详情service接口
 *
 */
public interface IOrderDetailService {
	void add(OrderDetail od);
	
	List<OrderDetail> query();
	
	List<OrderDetail> findByOrderid(String id);
}
