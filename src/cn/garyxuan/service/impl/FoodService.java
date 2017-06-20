package cn.garyxuan.service.impl;

import java.util.List;

import cn.garyxuan.dao.IFoodDao;
import cn.garyxuan.dao.impl.FoodDao;
import cn.garyxuan.entity.Food;
import cn.garyxuan.service.IFoodService;
import cn.garyxuan.util.PageBean;

/**
 * @author 菜品service实现
 *
 */
public class FoodService implements IFoodService {

	IFoodDao foodDao = new FoodDao();
	@Override
	public void add(Food food) {
		Food existFood = foodDao.findById(food.getId());
		if (existFood == null) {
			foodDao.add(food);
		} else {
			System.out.println("该菜品已存在");
		}
	}

	@Override
	public void delete(int id) {
		foodDao.delete(id);
	}

	@Override
	public void update(Food food) {
		foodDao.update(food);
	}

	@Override
	public List<Food> query() {
		return foodDao.query();
	}

	@Override
	public Food findById(int id) {
		return foodDao.findById(id);
	}

	@Override
	public List<Food> query(String keyword) {
		return foodDao.query(keyword);
	}

	@Override
	public List<Food> findByType(int type) {
		return foodDao.findByType(type);
	}

	@Override
	public void getAll(PageBean<Food> pb) {
		foodDao.getAll(pb);
	}

}
