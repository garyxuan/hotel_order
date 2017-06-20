package cn.garyxuan.service;

import java.util.List;

import cn.garyxuan.entity.FoodType;

/**
 * @author 菜系service接口
 *
 */
public interface IFoodTypeService {
	void add(FoodType foodtype);
	
	void delete(int id);
	
	void update(FoodType foodtype);
	
	List<FoodType> query();

	FoodType findById(int id);

	List<FoodType> query(String keyword);
}
