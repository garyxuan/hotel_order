<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无线点餐后台管理系统</title>
</head>
    <frameset rows="100px,*,19px" framespacing="0" border="0" frameborder="no">
       <frame name="top" noresize="noresize" src="${pageContext.request.contextPath }/sys/public/top.jsp" scrolling="no"/>
       <frameset cols="163px,*">
            <frame noresize="noresize" name="left" src="${pageContext.request.contextPath }/sys/public/left.jsp" scrolling="yes" /> 
            <frame noresize="noresize" name="right" src="${pageContext.request.contextPath }/sys/public/right.jsp" scrolling="yes" /> 
        </frameset>
        <frame name="status_bar" noresize="noresize" scrolling="no" src="${pageContext.request.contextPath }/sys/public/bottom.jsp" />
    </frameset>
    <noframes>
        <body>
            你的浏览器不支持框架布局，推荐你使用<a href="http://www.firefox.com.cn/download/" style="text-decoration: none;">火狐浏览器</a>,
            <a href="http://www.google.cn/intl/zh-CN/chrome/" style="text-decoration: none;">谷歌浏览器</a>
        </body>
    </noframes>
</html>