package cn.garyxuan.entity;


/**
 * 订单详情
 * @author garyxuan
 *
 */
public class OrderDetail {
	private String id;//详情编号
	private String orderId;//订单编号 外键：引用订单表的主键
	private int foodId;//菜品编号
	private int foodCount;//菜品数量
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public int getFoodCount() {
		return foodCount;
	}
	
	//TODO 待办事项示例
	public void setFoodCount(int foodCount) {
		this.foodCount = foodCount;
	}
	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", orderId=" + orderId + ", foodId="
				+ foodId + ", foodCount=" + foodCount + "]";
	}
	
}
