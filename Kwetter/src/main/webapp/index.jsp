
  Created by IntelliJ IDEA.
  User: luukj
  Date: 27-2-2019
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form method="post" action="j_security_check">
    username: <input type="text" name="j_username" /> <br />
    password: <input type="password" name="j_password" />
    <br />
    <input type="submit" value="login" />
    </form>
<div>
    <label>new user? Register here</label>
    <button action="/register.jsp" name="Register">Register</button>
</div>
</body>

</html>
