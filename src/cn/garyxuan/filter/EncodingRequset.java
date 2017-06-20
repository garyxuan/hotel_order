package cn.garyxuan.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 处理get请求 的编码转换
 * @author garyxuan
 *
 */
public class EncodingRequset extends HttpServletRequestWrapper{

	private HttpServletRequest request;
	public EncodingRequset(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	@Override
	public String getParameter(String name) {
		System.out.println("invoke getParameter" + name);
		String value = request.getParameter(name);
		try {
			if (value != null) {
				System.out.println("get value is before" + value);
				value = new String(value.getBytes("ISO-8859-1"), "GBK");
				System.out.println("get value is" + value);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}
}
