package cn.garyxuan.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.garyxuan.entity.DinnerTable;
import cn.garyxuan.service.IDinnerTableService;
import cn.garyxuan.service.impl.DinnerTableService;

/**
 * 餐桌相关接口
 */
@WebServlet("/DinnerTable")
public class DinnerTableServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private IDinnerTableService service = new DinnerTableService();
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		List<DinnerTable> list = service.query();
		config.getServletContext().setAttribute("table", list);
	}
	
	public void search(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			String keyword = request.getParameter("keyword");
			if(keyword != null){
				List<DinnerTable> list = service.query(keyword);
				request.setAttribute("list",list);
				System.out.println("action search");
				request.getRequestDispatcher("/sys/table/tableList.jsp").forward(request, response);
			}
		}

	public void delete(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			String id = request.getParameter("id");
			if (id != null && !"".equals(id.trim())) {
				service.delete(Integer.parseInt(id));
			}
			list(request, response);
		}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void update(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
		String tableName = request.getParameter("tableName");
		String id = request.getParameter("id");
		System.out.println("action update");
		if(tableName != null && id != null && !"".equals(tableName.trim())){
			System.out.println("update tableName is " + tableName);
			DinnerTable dt = new DinnerTable();
			dt.setTableName(tableName);
			dt.setId(Integer.parseInt(id));
			service.update(dt);
		}
		list(request, response);
	}

	/**
	 * 获取所有餐桌 完成之后转发至所有餐桌列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void list(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
	
		List<DinnerTable> list = service.query();
		request.setAttribute("list",list);
		
		//将餐桌列表存到context里传到前台显示
		request.getServletContext().setAttribute("table", list);
		
		request.getRequestDispatcher("/sys/table/tableList.jsp").forward(request, response);
	}
	
	/**
	 * 添加餐桌 完成之后转发至所有餐桌列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
		String tableName = request.getParameter("tableName");
		if(tableName != null && !"".equals(tableName.trim())){
			DinnerTable dt = new DinnerTable();
			dt.setTableName(tableName);
			service.add(dt);
		}
		list(request, response);
	}
	
	
	/**
	 * 退订餐桌 完成之后转发至所有餐桌列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void quit(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
			String id = request.getParameter("id");
			service.quitTable(Integer.parseInt(id));
			list(request, response);
	}
	
	/**
	 * 预定餐桌 完成之后转发至所有餐桌列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void book(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
			String id = request.getParameter("id");
			service.bookTable(Integer.parseInt(id));
			list(request, response);
	}
	
	/**
	 * 显示某一餐桌信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOExceptio
	 */
	public Object show(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String id = request.getParameter("id");
		if (id != null && !"".equals(id.trim())) {
			DinnerTable table =  service.findById(Integer.parseInt(id));
			request.setAttribute("table", table);
			return request.getRequestDispatcher("/sys/table/updateTable.jsp");
		}
		return null;
	}

}
