<!-- 开启EL -->
<%@page isELIgnored="false"%>
<!-- 开启jstl -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 设置项目根路径 -->
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!-- 引入jquery -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<!-- 引入layUI -->
<script src="${path }/views/common/layui/layui.js"></script>
<link rel="stylesheet" href="${path }/views/common/layui/layui.css">