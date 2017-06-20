/**
 * 
 */
package cn.garyxuan.entity;

/**
 * 菜系实体
 * @author garyxuan
 *
 */
public class FoodType {
	private int id;//编号
	private String typeName;//名称
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Override
	public String toString() {
		return "FoodType [id=" + id + ", typeName=" + typeName + "]"; 
	}
}
