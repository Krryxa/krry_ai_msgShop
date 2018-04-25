<%@ page language="java" import="java.util.*,com.krry.action.*" pageEncoding="utf-8"%>
<% 
	String path = request.getParameter("path");
	String filePath = request.getServletContext().getRealPath("/") + path;
	String result = ShopQuery.getIDInfo(filePath,request);
	out.print(result);
%>