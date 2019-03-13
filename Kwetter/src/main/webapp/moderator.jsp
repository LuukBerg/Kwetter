<%@ page import="org.jboss.resteasy.client.jaxrs.ResteasyClient" %>
<%@ page import="org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder" %>
<%@ page import="javax.ws.rs.client.WebTarget" %>
<%@ page import="javax.ws.rs.client.Invocation" %>
<%@ page import="kwetter.model.models.User" %>
<%@ page import="kwetter.model.models.Profile" %>
<%@ page import="javax.ws.rs.core.Response" %>
<%@ page import="javax.ws.rs.client.Entity" %><%--
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
<%!
    private ResteasyClient resteasyClient;
    private static String baseUrl = "http://localhost:8080/Kwetter/api";
%>
<div id ="allKweets">
    <h1> the date: </h1>
    <% resteasyClient = new ResteasyClientBuilder().build();


    %>

</div>
</body>
</html>
