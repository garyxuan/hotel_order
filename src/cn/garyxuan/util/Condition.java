package cn.garyxuan.util;

/**
 * 筛选条件实体
 * @author garyxuan
 *
 */
public class Condition {
	private String foodName;
	private int foodTypeId;
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public int getFoodTypeId() {
		return foodTypeId;
	}
	public void setFoodTypeId(int foodTypeId) {
		this.foodTypeId = foodTypeId;
	}
	@Override
	public String toString() {
		return "Condition [foodName=" + foodName + ", foodTypeId="
				+ foodTypeId + "]";
	}
}
