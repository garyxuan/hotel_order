package cn.garyxuan.service;

import java.util.List;

import cn.garyxuan.entity.DinnerTable;

/**
 * 餐桌service接口
 * @author garyxuan
 *
 */
public interface IDinnerTableService {
	void add(DinnerTable dt);
	
	void delete(int id);
	
	void update(DinnerTable dt);
	
	List<DinnerTable> query();
	
	DinnerTable findById(int id);

	List<DinnerTable> query(String keyword);

	DinnerTable changeState(int id);
	
	void quitTable(int id);
	
	void bookTable(int id);
}
