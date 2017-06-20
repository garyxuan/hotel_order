/**
 * 
 */
package cn.garyxuan.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.garyxuan.dao.IFoodDao;
import cn.garyxuan.entity.Food;
import cn.garyxuan.util.Condition;
import cn.garyxuan.util.JdbcUtil;
import cn.garyxuan.util.PageBean;

/**
 * 菜品dao实现
 * @author garyxuan
 *
 */
public class FoodDao implements IFoodDao {

	private QueryRunner qr = JdbcUtil.getQuerrRunner();
	private Log log = LogFactory.getLog(FoodDao.class);
	@Override
	public void add(Food food) {
		String sql = "insert into food(foodName,foodTypeId,price,vprice,remark,img) values(?,?,?,?,?,?)";
		try {			
			qr.update(sql, food.getFoodName(),food.getFoodTypeId(),food.getPrice(),food.getVprice(),food.getRemark(),food.getImg());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "delete from food where id = ?";
		try {
			qr.update(sql,id);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Food food) {
		String sql = "update food set foodName = ?, foodTypeId = ?, price = ?, vprice = ?, remark = ?, img = ? where id = ?";
		try {
			qr.update(sql, food.getFoodName(),food.getFoodTypeId(),food.getPrice(),food.getVprice(),food.getRemark(),food.getImg(),food.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Food> query() {
		String sql = "select * from food";
		try {
			return qr.query(sql, new BeanListHandler<>(Food.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Food findById(int id) {
		String sql = "select * from food where id = ?";
		try {
			return qr.query(sql, new BeanHandler<>(Food.class),id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Food> query(String keyword) {
		String sql = "select *from food where foodName like ?";
		try {
			return qr.query(sql, new BeanListHandler<>(Food.class),"%" + keyword + "%");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Food> findByType(int type) {
		String sql = "select * from food where foodTypeId = ?";
		try {
			return qr.query(sql, new BeanListHandler<>(Food.class),type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void getAll(PageBean<Food> pb) {
		System.out.println("@@@@@@@@@@@@@@@@@@@@");
		log.info("FoodDao query");
		//获取符合条件的个数
		int totalCount = getTotalCount(pb);
		pb.setTotalCount(totalCount);
		List<Object> list = new ArrayList<>();
		//设置当前页，防止提交非法页数
		System.out.println("getAll currentPage" + pb.getCurrentPage());
		if (pb.getCurrentPage() <= 0) {
			pb.setCurrentPage(1);//页数从1开始
		} else if (pb.getCurrentPage() > pb.getTotalPage()) {
			pb.setCurrentPage(pb.getTotalPage());
		} 
		//计算起始索性 从0开始
		int index = (pb.getCurrentPage() - 1) * pb.getPageCount();
		int count = pb.getPageCount();
		Condition condition = pb.getCondition();
		
		//分页查询的数据设置到pb对象中
		StringBuilder sb = new StringBuilder();
		sb.append("select");
		sb.append(" f.id,");
		sb.append(" f.foodName,");
		sb.append(" f.foodTypeId,");
		sb.append(" f.price,");
		sb.append(" f.vprice,");
		sb.append(" f.remark,");
		sb.append(" f.img,");
		sb.append(" ft.typeName");
		sb.append(" from food f");
		sb.append(" ,foodtype ft");
		sb.append(" where f.foodTypeId = ft.id");
		if (condition != null) {
			String foodName = condition.getFoodName();
			if (foodName != null && !foodName.isEmpty()) {
				sb.append(" and f.foodName like ?");
				list.add("%" + foodName + "%");
			}
			int typeId = condition.getFoodTypeId();
			if (typeId > 0) {
				sb.append(" and f.foodTypeId = ?");
				list.add(typeId);
			}
 		}
		sb.append(" limit ?, ?");
		list.add(index);
		list.add(count);
		System.out.println("index is " + index);
		try {
			if (index >= 0) {
				List<Food> pageData = qr.query(sb.toString(), new BeanListHandler<>(Food.class), list.toArray());
				System.out.println("pageData is " + pageData);
				pb.setPageData(pageData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getTotalCount(PageBean<Food> pb) {
		StringBuilder sb = new StringBuilder();
		List<Object> list = new ArrayList<>();
		sb.append("select count(*) from");
		sb.append(" food f,");
		sb.append(" foodtype ft");
		sb.append(" where f.foodTypeId = ft.id");
		Condition condition = pb.getCondition();
		if (condition != null) {
			String foodName = condition.getFoodName();
			if (foodName != null && !foodName.isEmpty()) {
				sb.append(" and f.foodName like ?");
				list.add("%" + foodName + "%");
			}
			int typeId = condition.getFoodTypeId();
			if (typeId > 0) {
				sb.append(" and f.foodTypeId = ?");
				list.add(typeId);
			}
		}
		try {
			Long count = qr.query(sb.toString(), new ScalarHandler<Long>(),list.toArray());
			return count.intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
