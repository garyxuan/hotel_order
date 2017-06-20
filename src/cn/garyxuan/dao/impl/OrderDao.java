package cn.garyxuan.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.garyxuan.dao.IOrderDao;
import cn.garyxuan.entity.Order;
import cn.garyxuan.util.JdbcUtil;
import cn.garyxuan.util.PageBean;

public class OrderDao implements IOrderDao{
	private QueryRunner qr = JdbcUtil.getQuerrRunner();
	@Override
	//order表名前带上数据路名   因为order是sql的一个关键字 ！！！！否则报语法错误 认为没有写表名 没有写排序条件
	public void add(Order order) {
		String sql ="insert into hotel_order.order(id,tableId,orderDate,totalPrice) values(?,?,?,?)";
		try {
			qr.update(sql,order.getId(),order.getTableId(),order.getOrderDate(),order.getTotalPrice());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getCount(){
		String sql ="select count(*) from hotel_order.order";
		try {
			Long count = qr.query(sql, new ScalarHandler<Long>());
			return count.intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public void update(Order order) {
		String sql = "update hotel_order.order set orderStatus =? where id=?";
		try {
			qr.update(sql,order.getOrderStatus(),order.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}

	@Override
	public List<Order> query() {
		String sql = "select * from hotel_order.order";
		try {
			return qr.query(sql, new BeanListHandler<>(Order.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void getAll(PageBean<Order> pb) {
		
		//2. 查询总记录数;  设置到pb对象中
		int totalCount = this.getTotalCount();
		System.out.println("getAll totalCount is " + totalCount);
		pb.setTotalCount(totalCount);
		
		/*
		 * 问题： jsp页面，如果当前页为首页，再点击上一页报错！
		 *              如果当前页为末页，再点下一页显示有问题！
		 * 解决：
		 * 	   1. 如果当前页 <= 0;       当前页设置当前页为1;
		 * 	   2. 如果当前页 > 最大页数；  当前页设置为最大页数
		 */
		// 判断
		System.out.println("pb.getCurrentPage()" + pb.getCurrentPage());
		if (pb.getCurrentPage() <=0) {
			pb.setCurrentPage(1);					    // 把当前页设置为1
		} else if (pb.getCurrentPage() > pb.getTotalPage()){
			pb.setCurrentPage(pb.getTotalPage());		// 把当前页设置为最大页数
		}
		
		if (pb.getTotalPage() == 0) {
			pb.setCurrentPage(0);
		}
		
		//1. 获取当前页： 计算查询的起始行、返回的行数
		int currentPage = pb.getCurrentPage();
		System.out.println("currentPage=" + currentPage);
		int index = (currentPage -1 ) * pb.getPageCount() < 0 ? 0 : (currentPage -1 ) * pb.getPageCount();		// 查询的起始行
		int count = pb.getPageCount();							// 查询返回的行数
		System.out.println("count=" + count);
		
		
		//3. 分页查询数据;  把查询到的数据设置到pb对象中
		String sql = "select * from hotel_order.order limit ?,?";
		
		try {
			// 根据当前页，查询当前页数据(一页数据)
			List<Order> pageData = qr.query(sql, new BeanListHandler<Order>(Order.class), index, count);
			// 设置到pb对象中
			pb.setPageData(pageData);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getTotalCount() {
		String sql = "select count(*) from hotel_order.order";
		try {
			// 执行查询， 返回结果的第一行的第一列
			Long count = qr.query(sql, new ScalarHandler<Long>());
			return count.intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
