package cn.garyxuan.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.garyxuan.util.WebUtil;

/**
 * 项目中通用的servlet 所有的servlet都继承这个类
 */
@WebServlet("/BaseServlet")
public abstract class BaseServlet extends HttpServlet {
      
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Object returnValue = null;
		String methodName = request.getParameter("method");
		System.out.println(getServletContext().getContextPath());
		try {
			//1.获取实例对象类型，为其子类
			Class clazz = this.getClass();
			//2.获取对象方法 方法名，参数类型
			Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			//3.执行方法
			returnValue = method.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
			returnValue = "/error/error.jsp";
		}
		// 跳转
		WebUtil.goTo(request, response, returnValue);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
