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
<div>
	<table id="tabled" lay-filter="test"></table>
</div>
<script type="text/javascript">
var table;
var layer;
layui.use(["table","layer"],function(){
	table=layui.table;
	layer=layui.layer;
	table.render({
		elem:"#tabled",
		id:"list",
		height:520,
		url:"${path}/activiti/queryTodoList",
		page:true,
		limit:10,
		limits:[10,20,50],
		cols:[[
			{field: 'userId', title: '申请人'},
			<%--
			{field: 'leaveType', title: '类型'},
			--%>
			{field: 'startTime', title: '开始时间'},
			{field: 'endTime', title: '结束时间'},
			{field: 'reason', title: '请假原因'},
			<%--
			{field: '', title: '任务ID'},
			{field: '', title: '任务名称'},
			{field: '', title: '流程实例ID'},
			{field: '', title: '流程定义ID'},
			--%>
			{field: 'applyTime', title: '申请时间'},
			{field: 'operate', title: '操作',templet:function(e){
				return "<a class='layui-btn layui-btn-sm' onclick=\"dealDaiBan()\">办理</a>";
			}}
		]]
	});
})
/**
 * 刷新列表
 */
function reload(){
	table.reload("list",{
		page:{
			curr:1
		},
		where:{
			key:{
				test:"test"
			}
		}
	})
}

/*
 * 办理任务
 */
function dealDaiBan(){
	layer.msg("开发中");
}
</script>
</body>
</html>