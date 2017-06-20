<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
 	<!-- 包含公共的JSP代码片段 -->
	
<title>无线点餐平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath }/sys/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/sys/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath }/sys/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/sys/style/css/index_1.css" />
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			<img src="${pageContext.request.contextPath }/sys/style/images/title_arrow.gif"/> 餐桌列表
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


<!-- 过滤条件 -->
<div id="QueryArea">
	<form action="${pageContext.request.contextPath }/DinnerTable?method=search" method="post">
		<input type="hidden" name="method" value="search">
		<input type="text" name="keyword" title="请输入餐桌名称">
		<input type="submit" value="搜索">
	</form>
</div>


<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
            <tr id="TableTitle">
				<td>编号</td>
				<td>桌名</td>
				<td>状态</td>
				<td>预定时间</td>
				<td>操作</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody id="TableData">
		<c:forEach items="${requestScope.list }" var="table" varStatus="vs">
			<tr class="TableDetail1">
				<td>${vs.count}</td>
				<td>${table.tableName}</td>
				<c:choose>
					<c:when test="${table.tableStatus==1}">
					<td>预定</td>
					</c:when>
					<c:otherwise>
					<td>空闲</td>
					</c:otherwise>
				</c:choose>
				<td width=25%>${table.orderDate}</td>
				<td>
					<c:choose>
					<c:when test="${table.tableStatus==1}">
					<a href="${pageContext.request.contextPath }/DinnerTable?method=quit&id=${table.id}" class="FunctionButton">退桌</a>	
					</c:when>
					<c:when test="${table.tableStatus==0}">
					<a href="${pageContext.request.contextPath }/DinnerTable?method=book&id=${table.id}" class="FunctionButton">预定</a>
					</c:when>
					</c:choose>			
					<a href="${pageContext.request.contextPath }/DinnerTable?method=delete&id=${table.id}" onClick="return delConfirm();" class="FunctionButton">删除</a>
					<a href="${pageContext.request.contextPath }/DinnerTable?method=update&tableName=${table.tableName}" class="FunctionButton">修改</a>				
				</td>
			</tr>
        </c:forEach>
        
        </tbody>
    </table>
	
   <!-- 其他功能超链接 -->
	<div id="TableTail">
		<a class="FunctionButton" href="${pageContext.request.contextPath }/sys/table/addTable.jsp">添加</a>
    </div> 
</div>
</body>
</html>
