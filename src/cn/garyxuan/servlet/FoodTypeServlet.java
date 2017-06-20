package cn.garyxuan.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.garyxuan.entity.FoodType;
import cn.garyxuan.service.IFoodTypeService;
import cn.garyxuan.service.impl.FoodTypeService;

/**
 * 菜系相关接口
 */
@WebServlet("/FoodType")
public class FoodTypeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private IFoodTypeService service =  new FoodTypeService();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void list(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
			List<FoodType> list = service.query();
			request.setAttribute("list",list);
			request.getRequestDispatcher("/sys/foodtype/foodTypeList.jsp").forward(request, response);
		}
	
	public void delete(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			String id = request.getParameter("id");
			service.delete(Integer.parseInt(id));
			list(request, response);
		}
	
	public void add(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			String typeName = request.getParameter("typeName");
			if (typeName != null && !"".equals(typeName.trim())) {
				FoodType foodtype = new FoodType();
				foodtype.setTypeName(typeName);
				service.add(foodtype);
				list(request, response);
			}
		}
	
	public void update(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			String typeName = request.getParameter("typeName");
			String id = request.getParameter("id");
			if (typeName != null && id != null && !"".equals(typeName.trim())) {
				FoodType foodtype = new FoodType();
				foodtype.setTypeName(typeName);
				foodtype.setId(Integer.parseInt(id));
				service.update(foodtype);
				list(request, response);
			}
		}
	
	public void show(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { 
		String id = request.getParameter("id");
		if (id != null && !"".equals(id.trim())) {
			FoodType foodType = service.findById(Integer.parseInt(id));
			request.setAttribute("foodType", foodType);
			request.getRequestDispatcher("/sys/foodtype/updateFoodType.jsp").forward(request, response);
		}
	}
	
	public void search(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		if(keyword != null){
			List<FoodType> list = service.query(keyword);
			request.setAttribute("list",list);
			System.out.println("action search");
			request.getRequestDispatcher("/sys/foodtype/foodTypeList.jsp").forward(request, response);
		}
	}

}
