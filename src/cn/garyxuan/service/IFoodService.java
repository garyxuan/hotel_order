package cn.garyxuan.service;

import java.util.List;

import cn.garyxuan.entity.Food;
import cn.garyxuan.util.PageBean;

/**
 * 菜品service接口
 * @author garyxuan
 *
 */
public interface IFoodService {
	void add(Food food);
	
	void delete(int id);
	
	void update(Food food);
	
	List<Food> query();

	Food findById(int id);

	List<Food> query(String keyword);
	
	List<Food> findByType(int type);
	
	void getAll(PageBean<Food> pb);
}
