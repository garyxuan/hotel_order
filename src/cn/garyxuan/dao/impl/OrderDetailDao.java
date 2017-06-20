package cn.garyxuan.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.garyxuan.dao.IOrderDetailDao;
import cn.garyxuan.entity.OrderDetail;
import cn.garyxuan.util.JdbcUtil;

public class OrderDetailDao implements IOrderDetailDao {

	private QueryRunner qr = JdbcUtil.getQuerrRunner();
	@Override
	public void add(OrderDetail od) {
		String sql =" insert into orderdetail(orderId,foodId,foodCount) values(?,?,?)";
		try {
			qr.update(sql,od.getOrderId(),od.getFoodId(),od.getFoodCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<OrderDetail> query() {
		try {
			String sql ="select * from orderdetail";
			return  qr.query(sql,new BeanListHandler<OrderDetail>(OrderDetail.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<OrderDetail> findByOrderid(String id) {
		try {
			String sql ="select * from orderdetail where orderId=?";
			return  qr.query(sql,new BeanListHandler<OrderDetail>(OrderDetail.class),id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
