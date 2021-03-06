<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/views/common/common.jsp"  %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JPS</title>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">技术演示系统</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <%-- 
    <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item"><a href="javascript:alert('开发中');">工单管理</a></li>
      <li class="layui-nav-item"><a href="javascript:alert('开发中');">报表管理</a></li>
      <li class="layui-nav-item"><a href="javascript:alert('开发中');">系统管理</a></li>
      <li class="layui-nav-item">
        <a href="javascript:void(0);">其它功能</a>
        <dl class="layui-nav-child">
          <dd><a href="javascript:alert('开发中');">邮件管理</a></dd>
          <dd><a href="javascript:alert('开发中');">消息管理</a></dd>
          <dd><a href="javascript:alert('开发中');">授权管理</a></dd>
        </dl>
      </li>
    </ul>
    --%>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
          <shiro:principal property="user_name"/>
        </a>
        <dl class="layui-nav-child">
          <dd><a href="javascript:alert('开发中');">个人中心</a></dd>
          <dd><a href="${path }/user/logout">退出</a></dd>
        </dl>
      </li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">Activiti</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:menuClick('${path }/activiti/processMgt');">流程管理</a></dd>
            <dd><a href="javascript:menuClick('${path }/activiti/todo');">待办事项</a></dd>
            <dd><a href="javascript:;">已办事项</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;">WebSocket</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:menuClick('${path }/webSocket/todo');">Spring WebSocket</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item"><a href="">云市场</a></li>
        <li class="layui-nav-item"><a href="">发布商品</a></li>
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <div style="padding: 15px;width:1140px;height:530px;">
    	<iframe id="mainBody" src="" style="width:100%;height:100%;" border="0" frameborder="0"  ></iframe>
    </div>
  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © layui.com - 底部固定区域
  </div>
</div>
<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
  
});

//菜单点击，跳转url
function menuClick(url){
	$("#mainBody").attr("src",url);
}
</script>
</body>
</html>