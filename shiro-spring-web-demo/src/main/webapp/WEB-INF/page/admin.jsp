<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<body>
<shiro:guest>
   这里是admin界面，<a href="${pageContext.request.contextPath}/user/login">点击登录</a><br/>
</shiro:guest>
<shiro:user>
    欢迎[<shiro:principal/>]admin界面，<a href="${pageContext.request.contextPath}/user/logout">点击退出</a><br/>
</shiro:user>
</body>
</html>
