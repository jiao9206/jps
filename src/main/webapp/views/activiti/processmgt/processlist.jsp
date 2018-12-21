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
	<button class="layui-btn layui-btn-sm">部署新流程</button>
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
		height:480,
		url:"${path}/activiti/queryProcessList",
		page:true,
		limit:5,
		limits:[5,10,20,50],
		cols:[[
			{field: 'id', title: '流程定义ID'},
			{field: 'deploymentId', title: '部署ID'},
			{field: 'name', title: '流程定义名称'},
			{field: 'key', title: '流程定义KEY'},
			{field: 'version', title: '版本号'},
			<%--
			{field: 'resourceName', title: 'XML资源'},
			{field: 'diagramResourceName', title: '图片资源'},
			--%>
			{field: 'operate', title: '操作',templet:function(e){
				var resourceName=e.resourceName.replace(/\\/g,',');
				var diagramResourceName=e.diagramResourceName.replace(/\\/g,',');
				var id=e.id;
				return "<a class='layui-btn layui-btn-sm' onclick=\"viewXml('"+id+"','"+resourceName+"')\">XML</a>"+
					   "<a class='layui-btn layui-btn-sm' onclick=\"viewPic('"+id+"','"+diagramResourceName+"')\">PIC</a>"+
					   "<a class='layui-btn layui-btn-sm' onclick=\"delProcess('"+e.deploymentId+"')\">DEL</a>";
			}}
		]]
	});
})
/**
 * 查看xml资源
 */
function viewXml(id,resourceName){
	layer.open({
		title:"View XML",
		type:2,
		area:["800px","400px"],
		content:"${path}/activiti/viewXml?id="+id+"&resourceName="+resourceName
	})
}
/**
 * 查看图片资源
 */
function viewPic(id,diagramResourceName){
	layer.open({
		title:"View XML",
		type:2,
		area:["800px","400px"],
		content:"${path}/activiti/viewXml?id="+id+"&diagramResourceName="+diagramResourceName
	})
}
/**
 * 删除部署
 */
function delProcess(deploymentId){
	layer.confirm("Are youe sure?",function(e){
		$.ajax({
			url:"${path}/activiti/delProcess",
			data:{"deploymentId":deploymentId},
			dataType:"json",
			success:function(e){
				if(e.flag){
					layer.msg("SUCCESS!");
				}else{
					layer.msg(flag.message);
				}
			},
			error:function(e){
				layer.msg("System Exception!");
			}
		})
	})
}
</script>
</body>
</html>