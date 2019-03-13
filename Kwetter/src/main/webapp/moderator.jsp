<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="org.jboss.resteasy.client.jaxrs.ResteasyClient" %>
<%@ page import="org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder" %>
<%@ page import="javax.ws.rs.client.WebTarget" %>
<%@ page import="javax.ws.rs.client.Invocation" %>
<%@ page import="kwetter.model.models.User" %>
<%@ page import="kwetter.model.models.Profile" %>
<%@ page import="javax.ws.rs.core.Response" %>
<%@ page import="javax.ws.rs.client.Entity" %>
<%@ page import="kwetter.model.models.Kweet" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.ws.rs.core.GenericEntity" %>
<%@ page import="kwetter.bean.KweetBean" %>
<%@ page import="javax.ws.rs.core.GenericType" %>

<%--
  Created by IntelliJ IDEA.
  User: luukj
  Date: 27-2-2019
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Moderator's</title>
</head>
<body>
<div id ="allKweets">
    <jsp:useBean id="kweetBean" class="kwetter.bean.KweetBean" scope="session">
        <%-- intialize bean properties --%>
    </jsp:useBean>
        <h1> Kweets: </h1>
    <table>
            <c:forEach items="${kweetBean.kweets}" var="kweet">
                <tr>
                    <td>${kweet.content}</td>
                    <td>date: ${kweet.date}</td>
                    <td>${kweet.owner.owner.username}</td>
                </tr>
            </c:forEach>
    </table>
    <tr>
        <td>content: ${kweet.content}</td>
        <td>id: ${kweet.id}</td>
    </tr>
<h1>end</h1>
</div>
</body>
</html>
