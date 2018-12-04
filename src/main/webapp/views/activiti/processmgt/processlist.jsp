<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/views/common/common.jsp"  %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Process Mgt</title>
</head>
<body>
<table id="tabled" lay-filter="test"></table>

<script type="text/javascript">
layui.use("table",function(){
	var table=layui.table;
	table.render({
		elem:"#tabled",
		height:500,
		url:"${path}/activiti/queryProcessList",
		page:true,
		limit:10,
		limits:[1,10,20,50],
		cols:[[
			{field: 'id', title: '流程定义ID'},
			{field: 'deploymentId', title: '部署ID'},
			{field: 'name', title: '流程定义名称'},
			{field: 'key', title: '流程定义KEY'},
			{field: 'version', title: '版本号'},
			{field: 'resourceName', title: 'XML资源名称'},
			{field: 'diagramResourceName', title: '图片资源名称'},
			{field: 'operate', title: '操作'}
		]]
	})
})
</script>
</body>
</html>