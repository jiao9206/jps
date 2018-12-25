<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/views/common/common.jsp"  %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Process Create</title>
</head>
<body>
<div class="layui-form" style="width:85%;margin-top: 30px;">
	<input type="text" class="layui-input" value="${id}" name="processInstanceId" style="display: none"/>
	<div class="layui-form-item">
		<label class="layui-form-label">开始时间</label>
		<div class="layui-input-block">
			<input lay-verify="required" type="text" class="layui-input" id="startTime" name="startTime">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">结束时间</label>
		<div class="layui-input-block">
			<input lay-verify="required" type="text" class="layui-input" id="endTime" name="endTime">
		</div>
	</div>
	<div class="layui-form-item layui-form-text">
    	<label class="layui-form-label">请假原因</label>
    	<div class="layui-input-block">
      		<textarea lay-verify="required" name="reason" placeholder="" class="layui-textarea"></textarea>
    	</div>
  	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="submitButton">提交</button>
		</div>
	</div>
</div>
<script type="text/javascript">
var layer;
var form;
var laydate;
layui.use(["layer","form","laydate"],function(){
	layer=layui.layer;
	form=layui.form;
	laydate=layui.laydate;

	laydate.render({
		elem:"#startTime",
		type:"datetime"
	});
	laydate.render({
		elem:"#endTime",
		type:"datetime"
	});
	
	form.on("submit(submitButton)",function(data){
		<%--设置submit不可重复点击--%>
		$(".layui-btn").attr("class","layui-btn layui-btn-disabled");
		<%--提交数据--%>
		$.ajax({
			url:"${path}/activiti/createAndSend",
			data:data.field,
			dataType:"json",
			success:function(e){
				if(e.flag){
					layer.alert("SUCCESS!",function(){
						var index = parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					});
				}else{
					layer.msg(flag.message);
				}
			},
			error:function(e){
				layer.msg("System Exception!");
			}
		})
	})
})

</script>
</body>
</html>