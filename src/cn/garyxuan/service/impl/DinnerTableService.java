/**
 * 
 */
package cn.garyxuan.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import cn.garyxuan.dao.IDinnerTableDao;
import cn.garyxuan.dao.impl.DinnerTableDao;
import cn.garyxuan.entity.DinnerTable;
import cn.garyxuan.service.IDinnerTableService;

/**
 * 餐桌service实现
 * @author garyxuan
 *
 */
public class DinnerTableService implements IDinnerTableService{

	IDinnerTableDao tableDao = new DinnerTableDao();
	@Override
	public void add(DinnerTable dt) {
		//检查是否有重名桌子
		List<DinnerTable> list = tableDao.query(dt.getTableName());
		if (list != null && list.size() > 0) {
			for (DinnerTable dinnerTable : list) {
				if (dt.getTableName().equals(dinnerTable.getTableName())) {
					System.out.println("已存在同名的桌子！");
					return;
				}
			}
			tableDao.add(dt);
		} else {
			tableDao.add(dt);
		}
	}

	@Override
	public void delete(int id) {
		tableDao.delete(id);
	}

	@Override
	public void update(DinnerTable dt) {
		//检查是否有重名桌子
		List<DinnerTable> list = tableDao.query(dt.getTableName());
		if (list != null && list.size() > 0) {
			System.out.println("已存在同名的桌子！");
		} else {
			tableDao.update(dt);
		}
	}

	@Override
	public List<DinnerTable> query() {
		return tableDao.query();
	}

	@Override
	public DinnerTable findById(int id) {
		return tableDao.findById(id);
	}

	@Override
	public List<DinnerTable> query(String keyword) {
		return tableDao.query(keyword);
	}

	@Override
	public DinnerTable changeState(int id) {
		DinnerTable table = tableDao.findById(id);
		if (table != null) {
			if (table.getTableStatus() == DinnerTable.BOOKED) {
				table.setTableStatus(DinnerTable.FREE);
				table.setOrderDate(null);
			} else if (table.getTableStatus() == DinnerTable.FREE) {
				table.setTableStatus(DinnerTable.BOOKED);
				table.setOrderDate(new Timestamp(new Date().getTime()));
			}
			tableDao.update(table);
		} else {
			System.out.println("该餐桌已存在");
		}
		return table;
	}

	@Override
	public void quitTable(int id) {
		tableDao.quitTable(id);
	}

	@Override
	public void bookTable(int id) {
		//未预定的桌子才可以预定
		DinnerTable table = tableDao.findById(id);
		if (table.getTableStatus() == DinnerTable.FREE) {
			System.out.println("预定空闲的餐桌");
			tableDao.bookTable(id);
		} else {
			System.out.println("该餐桌已被预订");
		}
	}
	
}
