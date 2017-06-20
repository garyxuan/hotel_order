/**
 * 
 */
package cn.garyxuan.entity;

import java.sql.Timestamp;

/**
 * 订单实体
 * @author garyxuan
 *
 */
public class Order {
	
	//结账状态
	public static final int WAIT_TO_PAY = 0;//未结账
	public static final int PAYED = 1;//已结账
	
	private String id;//编号
	private int tableId;//餐桌编号 外键  引用餐桌表主键
	private Timestamp orderDate;//下单时间
	private double totalPrice;//总价
	private int orderStatus;//结账状态
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	public Timestamp getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", tableId=" + tableId + ", orderDate="
				+ orderDate + ", totalPrice=" + totalPrice + ", orderStatus="
				+ orderStatus + "]";
	}
	
}
