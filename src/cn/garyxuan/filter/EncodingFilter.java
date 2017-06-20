package cn.garyxuan.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * 编码过滤器 对get/post请求参数的编码进行处理  ！！！！ 该过滤器不启用
 * 解决方法：在tomcat_home\conf\server.xml 中的Connector元素中设置URIEncoding属性为合适的字符编码
 */
@WebFilter(filterName = "EncodingFilter", urlPatterns = { "/app/*" })
public class EncodingFilter implements Filter {

    public EncodingFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");//post请求有效
		System.out.println("setCharacterEncoding");
		HttpServletRequest request = (HttpServletRequest)req;
		if ("GET".equals(request.getMethod())) {
			EncodingRequset er = new EncodingRequset(request);
			chain.doFilter(er, res);
		} else if ("POST".equals(request.getMethod())) {
			chain.doFilter(request, res);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
