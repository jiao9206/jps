<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Please Login</title>
<%@include file="/views/common/common.jsp"  %>
<style type="text/css">
	.headDiv{
		height:70px;
	}
	.myBtnRight{
		margin-top: 20px;
	}
	.myBtnLeft{
		margin-top: 11px;
		font-size: xx-large;
	}
	.layui-input-block{
		width:200px;
	}
	.myMiddle{
		margin-top: 150px;
	}
</style>
</head>
<body>
	<%--头部 --%>
	<div class="layui-fluid layui-bg-green">  
		<div class="layui-row">
			<div class="layui-col-md9 headDiv">
				<a href="${path }/" class="layui-btn layui-btn-lg myBtnLeft">JPS Management</a>
			</div>
			<div class="layui-col-md3 headDiv">
				<a href="" class="layui-btn layui-btn-sm myBtnRight">English</a>
				<button class="layui-btn layui-btn-sm myBtnRight">|</button>
				<a href="" class="layui-btn layui-btn-sm myBtnRight">简体中文</a>
				<button class="layui-btn layui-btn-sm myBtnRight">|</button>
				<a href="" class="layui-btn layui-btn-sm myBtnRight">联系我们</a>
			</div>
		</div>
	</div>
	<%--中间 --%>
	<div class="layui-fluid myMiddle">
		<div class="layui-row">
			<div class="layui-col-md7">
				<img alt="JiaoPeng" src="${path }/views/common/login/login01.png" style="margin-left: 100px;margin-top: -7px;">
			</div>
			<div class="layui-col-md5">
				<form action="" class="layui-form">
					<div style="border: 1px solid #DCDCDC;width:370px;">
						<br/>
						<div class="layui-form-item">
							<label class="layui-form-label">用户名</label>
							<div class="layui-input-block">
								<input type="text" name="username" placeholder="请输入" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">密码</label>
							<div class="layui-input-block">
								<input type="password" name="password" placeholder="请输入" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<div class="layui-input-block">
								<input type="checkbox" name="remember" title="下次自动登陆">
							</div>
						</div>
						<div class="layui-form-item">
							<div class="layui-input-block">
								<button class="layui-btn" lay-submit lay-filter="submit">登陆</button>
								<button type="reset" class="layui-btn layui-btn-primary">重置</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%--底部 --%>
	<div class="layui-fluid layui-bg-green" style="margin-top: 140px;height:65px;">
		<div class="layui-row">
			<div class="layui-col-md12">
				<div style="text-align: center;">
					<a href="" class="layui-btn layui-btn-sm myBtnRight">About JPS</a>
					<button class="layui-btn layui-btn-sm myBtnRight">|</button>
					<button class="layui-btn layui-btn-sm myBtnRight">©1998 - 2018 JPS Inc. All Rights Reserved.</button>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	
	<script type="text/javascript">
	layui.use(['layer','form'],function(){
		var layer=layui.layer;
		layer.msg("JiaoPeng");
	})
	</script>
</body>
</html>