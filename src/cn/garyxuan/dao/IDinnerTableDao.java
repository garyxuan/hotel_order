package cn.garyxuan.dao;

import java.util.List;

import cn.garyxuan.entity.DinnerTable;

/**
 * 餐桌dao接口
 * @author garyxuan
 *
 */
public interface IDinnerTableDao {
	void add(DinnerTable dt);
	
	void delete(int id);
	
	void update(DinnerTable dt);
	
	List<DinnerTable> query();

	DinnerTable findById(int id);

	List<DinnerTable> query(String keyword);
	
	void quitTable(int id);
	
	void bookTable(int id);
}
