/**
 * 
 */
package cn.garyxuan.entity;

import java.sql.Timestamp;

/**
 * 餐桌实体
 * @author garyxuan
 *
 */

public class DinnerTable {
	//餐桌状态
	public static final int FREE = 0;//空闲
	public static final int BOOKED = 1;//预订
	
	private int id;//餐桌编号
	private int tableStatus;//餐桌使用状态
	private String tableName;//餐桌名称
	private Timestamp orderDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getTableStatus() {
		return tableStatus;
	}
	public void setTableStatus(int tableStatus) {
		this.tableStatus = tableStatus;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Timestamp getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "DinnerTable [id=" + id + ", tableName=" + tableName + ", tableStatus=" + tableStatus + ", orderDate=" + orderDate + "]";
	}
}
