package cn.garyxuan.util;

import java.util.List;
import cn.garyxuan.util.Condition;;

/**
 * 分页bean
 * @author garyxuan
 *
 */
public class PageBean<T> {
	private int currentPage;//当前页
	private int pageCount = 10;//查询返回的行数 设置固定值3
	private int totalPage;//总页数
	private int totalCount;//总记录数
	private List<T> pageData;//分页查询到的数据
	private Condition condition;//查询条件
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getTotalPage() {
		if (totalCount % pageCount == 0)
			totalPage =  totalCount / pageCount;
		else
			totalPage = totalCount / pageCount + 1;
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<T> getPageData() {
		return pageData;
	}
	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}
	public Condition getCondition() {
		return condition;
	}
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	
	@Override
	public String toString() {
		return "PageBean [currentPage=" + currentPage + ", pageCount=" + pageCount + ", totalPage=" + totalPage + ", totalCount=" + totalCount + ", pageData=" + pageData + ", condition=" + condition + "]";
	}
	
	
}
