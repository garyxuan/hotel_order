package cn.garyxuan.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.garyxuan.entity.DinnerTable;
import cn.garyxuan.entity.Food;
import cn.garyxuan.entity.Order;
import cn.garyxuan.entity.OrderDetail;
import cn.garyxuan.service.IDinnerTableService;
import cn.garyxuan.service.IFoodService;
import cn.garyxuan.service.IOrderDetailService;
import cn.garyxuan.service.IOrderService;
import cn.garyxuan.service.impl.DinnerTableService;
import cn.garyxuan.service.impl.FoodService;
import cn.garyxuan.service.impl.OrderDetailService;
import cn.garyxuan.service.impl.OrderService;
import cn.garyxuan.util.PageBean;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/Order")
public class OrderServlet extends BaseServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private IOrderDetailService orderDetailService =  new OrderDetailService();
	private IOrderService orderService =  new OrderService();
	private IFoodService foodService = new FoodService();
	private IDinnerTableService tableService = new DinnerTableService();
	public Object getOrderDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("orderId");
		List<OrderDetail> list = null;
		List<Food> foodList = null;
		if (id != null && !id.isEmpty()) {
			list = orderDetailService.findByOrderid(id);
			foodList = foodService.query();
			request.setAttribute("orderDetail", list);
			request.setAttribute("foodList", foodList);
			return request.getRequestDispatcher("/sys/order/orderDetail.jsp");
		}
		return null;
	}
	
	public Object getOrderList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 1. 获取“当前页”参数； (第一次访问当前页为null)
		String currPage = request.getParameter("currentPage");
		// 判断
		if (currPage == null || "".equals(currPage.trim())) {
			currPage = "0"; // 第一次访问，设置当前页为0;
		}
		// 转换
		int currentPage = Integer.parseInt(currPage);

		// 2. 创建PageBean对象，设置当前页参数； 传入service方法参数
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setCurrentPage(currentPage);
		pageBean.setPageCount(6);

		// 3. 调用service
		orderService.getAll(pageBean); // 【pageBean已经被dao填充了数据】
		// 4. 保存pageBean对象，到request域中
		request.setAttribute("pageBean", pageBean);
		
		List<DinnerTable> tableList = tableService.query();
		request.setAttribute("tableList", tableList);
		

		// 5. 跳转
		return request.getRequestDispatcher("sys/order/orderList.jsp");
	}
	
	
	public Object pay(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		String tableId = request.getParameter("tableId");
		Order order = new Order();
		order.setOrderStatus(Order.PAYED);
		order.setId(orderId);
		orderService.update(order);
		if (tableId != null) {
			tableService.quitTable(Integer.parseInt(tableId));
		}
		return getOrderList(request, response);
	}
}
