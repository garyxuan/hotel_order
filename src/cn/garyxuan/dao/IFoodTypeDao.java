package cn.garyxuan.dao;

import java.util.List;

import cn.garyxuan.entity.FoodType;

/**
 * @author 菜系dao接口
 *
 */
public interface IFoodTypeDao {
	void add(FoodType foodtype);
	
	void delete(int id);
	
	void update(FoodType foodtype);
	
	List<FoodType> query();

	FoodType findById(int id);

	List<FoodType> query(String keyword);
}
