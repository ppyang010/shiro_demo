<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<body>
<shiro:guest>
    欢迎游客访问22222，<a href="${pageContext.request.contextPath}/user/login">点击登录</a><br/>
</shiro:guest>
<shiro:user>
    欢迎[<shiro:principal/>]登录，<a href="${pageContext.request.contextPath}/user/logout">点击退出</a><br/>
</shiro:user>
<h4>角色</h4>
<a href="${pageContext.request.contextPath}/adminrole">adminrole</a><br/>
<a href="${pageContext.request.contextPath}/admin">admin</a><br/>
<h4>权限</h4>
<a href="${pageContext.request.contextPath}/adminview">admin view</a><br/>
<a href="${pageContext.request.contextPath}/userview">user view</a><br/>
</body>
</html>
