package cn.garyxuan.service.impl;

import java.util.List;

import cn.garyxuan.dao.IFoodTypeDao;
import cn.garyxuan.dao.impl.FoodTypeDao;
import cn.garyxuan.entity.FoodType;
import cn.garyxuan.service.IFoodTypeService;

/**
 * @author 菜系service实现
 *
 */
public class FoodTypeService implements IFoodTypeService {

	IFoodTypeDao foodTypeDao = new FoodTypeDao();
	@Override
	public void add(FoodType foodtype) {
		//检查同名菜系
		List<FoodType> list = foodTypeDao.query(foodtype.getTypeName());
		if (list != null && list.size() > 0 ) {
			for (FoodType foodType2 : list) {
				if (foodtype.getTypeName().equals(foodType2.getTypeName())) {
					System.out.println("已存在同名菜系");
					return;
				}
			}
			foodTypeDao.add(foodtype);
		} else {
			foodTypeDao.add(foodtype);
		}
	} 

	@Override
	public void delete(int id) {
		foodTypeDao.delete(id);
	}

	@Override
	public void update(FoodType foodtype) {
		foodTypeDao.update(foodtype);
	}

	@Override
	public List<FoodType> query() {
		return foodTypeDao.query();
	}

	@Override
	public FoodType findById(int id) {
		return foodTypeDao.findById(id);
	}

	@Override
	public List<FoodType> query(String keyword) {
		return foodTypeDao.query(keyword);
	}

}
