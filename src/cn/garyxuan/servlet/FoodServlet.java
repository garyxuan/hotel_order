package cn.garyxuan.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import cn.garyxuan.entity.Food;
import cn.garyxuan.entity.FoodType;
import cn.garyxuan.service.IFoodService;
import cn.garyxuan.service.IFoodTypeService;
import cn.garyxuan.service.impl.FoodService;
import cn.garyxuan.service.impl.FoodTypeService;
import cn.garyxuan.util.Condition;
import cn.garyxuan.util.PageBean;

/**
 * 菜系相关接口
 */
@WebServlet("/Food")
public class FoodServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private IFoodService foodService = new FoodService();
    private IFoodTypeService foodTypeService = new FoodTypeService();
	
//	@Override
//	public void init(ServletConfig config) throws ServletException {
//		super.init();
//		PageBean<Food> pageBean = new PageBean<>();
//		pageBean.setPageCount(6);
//		foodService.getAll(pageBean);
//		List<Food> list = foodService.query();
//		config.getServletContext().setAttribute("foodList", list);
//		config.getServletContext().setAttribute("foodPb", pageBean);
//	}
	
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(10 * 1024 * 1024); // 单个文件大小限制10M 
		upload.setSizeMax(50 * 1024 * 1024); // 总文件大小限制 10M   表单中文件大小
		upload.setHeaderEncoding("UTF-8"); // 对中文文件编码处理
		//判断上传表单是否为multipart/form-data类型
		if (ServletFileUpload.isMultipartContent(request)) {
			Food food = new Food();
			//解析上传数据列表
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {//普通文本
					String name = fileItem.getFieldName();//数据key
					System.out.println("isFormField name is " + name);
					String value = new String(fileItem.getString().getBytes("ISO8859-1"), "UTF-8");//数据value
					BeanUtils.setProperty(food, name, value);
				} else { //文件内容
					String fieldName = fileItem.getFieldName();
					System.out.println("isFileField name is " + fieldName);
					String path = getServletContext().getRealPath("/upload");
					File f = new File(path);
					if (!f.exists()) {
						f.mkdir();
					}
					String name = UUID.randomUUID().toString() + "#" + fileItem.getName();//名称重新随机拼接UUID，防止重复
					BeanUtils.setProperty(food, fieldName, "upload/" + name);
					//生成上传文件
					File file = new File(path, name);
					if (!file.isDirectory()) {
						fileItem.write(file);//写入
					}
					fileItem.delete();// 删除组件运行时产生的临时文件
				}
			}
			foodService.add(food);
		}
		list(request, response);
	}
	
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			// 1. 获取“当前页”参数； (第一次访问当前页为null)
			String currPage = request.getParameter("currentPage");
			//搜索参数
			String keyword = request.getParameter("keyword");
			// 判断
			if (currPage == null || "".equals(currPage.trim())) {
				currPage = "1"; // 第一次访问，设置当前页为1;
			}
			// 转换
			int currentPage = Integer.parseInt(currPage);

			// 2. 创建PageBean对象，设置当前页参数； 传入service方法参数
			PageBean<Food> pageBean = new PageBean<Food>();
			pageBean.setCurrentPage(currentPage);
			if (keyword != null && !"".equals(keyword.trim())) {//是搜索动作
				System.out.println("Action list keyword is" + keyword);
				Condition condition = new Condition();
				condition.setFoodName(keyword);
				pageBean.setCondition(condition);
			}

			// 3. 调用service
			foodService.getAll(pageBean); // 【pageBean已经被dao填充了数据】
			// 4. 保存pageBean对象，到request域中

			List<Food> list = pageBean.getPageData();
			System.out.println("++++++++++++" + list);
			// 获得食物类别的方法
			List<FoodType> types = new ArrayList<FoodType>();
			
			if (list != null) {
				for (Food food : list) {
					FoodType foodtype = foodTypeService.findById(food.getFoodTypeId());
					types.add(foodtype);
				}
			}
			request.setAttribute("types", types);

			request.setAttribute("pageBean", pageBean);
			request.setAttribute("list", list);
//			request.setAttribute("keyword", keyword);
			request.getRequestDispatcher("/sys/food/foodList.jsp").forward(request, response);
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		foodService.delete(Integer.parseInt(id));
		list(request, response);
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	
	public Object show(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<FoodType> foodTypes = foodTypeService.query();
		request.setAttribute("foodTypes", foodTypes);
		String id = request.getParameter("id");
		Food food = foodService.findById(Integer.parseInt(id));
		request.setAttribute("food", food);
		int foodTypeId = food.getFoodTypeId();
		FoodType foodType = foodTypeService.findById(foodTypeId);
		request.setAttribute("foodType", foodType);
		return request.getRequestDispatcher("/sys/food/updateFood.jsp");
	}
	
	public void search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		System.out.println("Food action search keyword is " + keyword);
//		if (keyword != null) {
//			List<Food> list = foodService.query(keyword);
//			List<FoodType> types = new ArrayList<>();
//			if (list != null) {
//				for (Food food : list) {
//					FoodType foodType = foodTypeService.findById(food.getFoodTypeId());
//					types.add(foodType);
//				}
//			}
//			System.out.println(types);
//			System.out.println(list);
//			request.setAttribute("types", types);
//			request.setAttribute("list", list);
//		}
//		request.getRequestDispatcher("/sys/food/foodList.jsp").forward(request, response);
		list(request, response);
	}
	
	public Object findFoodType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Food Action is findFoodType");
		List<FoodType> foodTypes = foodTypeService.query();
		request.setAttribute("foodTypes", foodTypes);
		return request.getRequestDispatcher("/sys/food/addFood.jsp");
	}


}
