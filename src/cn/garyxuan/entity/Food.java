/**
 * 
 */
package cn.garyxuan.entity;

import java.util.HashMap;

import com.sun.javafx.collections.MappingChange.Map;

/**
 * 菜品实体
 * @author garyxuan
 *
 */
public class Food {
	private int id;//编号
	private String foodName;//名称
	private int foodTypeId;//所属菜系，外键，引入菜系表id
	private double price;//价格
	private double vprice;//会员价格
	private String remark;//评论
	private String img;//图片
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getVprice() {
		return vprice;
	}
	public void setVprice(double vprice) {
		this.vprice = vprice;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	@Override
	public String toString() {
		return "Food [id=" + id + ", foodName=" + foodName + ", foodTypeId="
				+ foodTypeId + ", price=" + price + ", vprice=" + vprice
				+ ", remark=" + remark + ", img=" + img + "]";
	}
	
	
	/**
	 * 项目中有使用Food对象作为Map的key 所以必须重写hasCode 和   equals方法
	 */
	@Override
	public int hashCode() {
		return this.id;
	}
	
	@Override
	public boolean equals(Object obj) {
		Food food = (Food)obj;
		return food.getId() == this.id;
	}
}
