package cn.garyxuan.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.garyxuan.dao.IFoodTypeDao;
import cn.garyxuan.entity.FoodType;
import cn.garyxuan.util.JdbcUtil;

/**
 * 菜系dao实现
 * @author garyxuan
 *
 */
public class FoodTypeDao implements IFoodTypeDao{
	private QueryRunner qr = JdbcUtil.getQuerrRunner();
	
	@Override
	public void add(FoodType foodtype) {
		String sql ="insert into foodtype(typeName) values(?)";
		try {
			qr.update(sql, foodtype.getTypeName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql ="delete from foodtype where id=?";
		try {
			qr.update(sql,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(FoodType foodtype) {
		String sql ="update foodtype set typeName = ? where id = ?";
		try {
			qr.update(sql,foodtype.getTypeName(),foodtype.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<FoodType> query() {
		String sql ="select * from foodtype";
		try {
			return  qr.query(sql,new BeanListHandler<FoodType>(FoodType.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public FoodType findById(int id) {
		String sql ="select * from foodtype where id =?";
		try {
			return qr.query(sql,new BeanHandler<FoodType>(FoodType.class), id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<FoodType> query(String keyword) {
		try {
			String sql ="select * from foodtype where typeName like ?";
			return qr.query(sql,new BeanListHandler<FoodType>(FoodType.class) , "%"+keyword+"%");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	
}
