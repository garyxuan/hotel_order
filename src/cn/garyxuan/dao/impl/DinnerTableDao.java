package cn.garyxuan.dao.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.garyxuan.dao.IDinnerTableDao;
import cn.garyxuan.entity.DinnerTable;
import cn.garyxuan.util.JdbcUtil;

/**
 * 餐桌dao实现
 * @author garyxuan
 *
 */
public class DinnerTableDao implements IDinnerTableDao {

	private QueryRunner qr = JdbcUtil.getQuerrRunner();
	@Override
	public void add(DinnerTable dt) {
		String sql = "insert into dinnertable(tableName) values(?)";
		try {
			qr.update(sql, dt.getTableName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "delete from dinnertable where id = ?";
		try {
			qr.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(DinnerTable dt) {
		String sql = "update dinnertable set tableStatus = ?,orderDate = ? where id = ?";
		try {
			qr.update(sql, dt.getTableStatus(),dt.getOrderDate(),dt.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<DinnerTable> query() {
		String sql = "select * from dinnertable";
		try {
			return qr.query(sql, new BeanListHandler<>(DinnerTable.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public DinnerTable findById(int id) {
		String sql = "select *from dinnertable where id = ?";
		try {
			return qr.query(sql, new BeanHandler<>(DinnerTable.class),id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<DinnerTable> query(String keyword) {
		String sql = "select *from dinnertable where tableName like ?";
		try {
			return qr.query(sql, new BeanListHandler<>(DinnerTable.class),"%" + keyword + "%");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void quitTable(int id) {
		String sql = "update dinnertable set tableStatus = ?, orderDate = ? where id = ?";
		try {
			qr.update(sql,DinnerTable.FREE,null,id);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void bookTable(int id) {
		String sql = "update dinnertable set tableStatus = ?, orderDate = ? where id = ?";
		try {
			qr.update(sql,DinnerTable.BOOKED,new Timestamp(new Date().getTime()),id);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
