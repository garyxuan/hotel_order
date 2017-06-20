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
import javax.servlet.http.HttpServletResponse;

/**
 * 对于不同的浏览器访问做处理
 */
@WebFilter(filterName = "BrowserFilter", urlPatterns = { "/*" })
public class BrowserFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		String userAgent = request.getHeader("user-agent");
		if (userAgent.contains("Firefox")) {
			System.out.println("is Firefox web browser");
			chain.doFilter(request, response);
		} else if (userAgent.contains("Chrome")) {
			System.out.println("is Chrome web browser");
			chain.doFilter(request, response);
		} else if (userAgent.contains(".NET")) {
			System.out.println("is IE web browser");
			chain.doFilter(request, response);
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
