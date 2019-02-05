<%@ page import="com.Posting" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Blog</title>
    <link rel = "stylesheet"
          type = "text/css"
          href = "stylesheet.css" />
</head>
<body>
<div name="title" class="title">
    <p1>My Blog</p1>
</div>
<div class="left-sidebar">


<div name = "about" class="left-sidebar-items">
    <p2>about</p2>
</div>
<div name = "pictures" class="left-sidebar-items">
    <p2>pictures</p2>
</div>
<div name = "my friends" class="left-sidebar-items">
    <p2>my friends</p2>
</div>
</div>



<div name = "posts" class="posts">



    <%

        List<Posting> postings = null;

        if (request.getAttribute("postings") != null) {
            postings = (List<Posting>) request.getAttribute("postings");
            Collections.sort(postings, new Comparator<Posting>() {
                public int compare(Posting o1, Posting o2) {
                    return o2.getDate().compareTo(o1.getDate());
                }
            });
            for (Posting post: postings) {
                %>
<div class = post>
            Title: <%=post.getTitle()%> <br>
            Posting: <%=post.getContent()%> <br>
            Posted on <%=post.getDate()%> <br>
</div>
    <%

            }
        }
    %>


</div>
</body>
</html>