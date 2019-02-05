package com;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private WebLogDao context = new WebLogDaoImp();

    public ControllerServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        context.addPosting(new Posting(title,content, new Date()));
        RequestDispatcher rd = null;
        rd = getServletContext().getRequestDispatcher("/ControllerServlet");
        rd.forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("postings", context.getPostings());
       // request.getRequestDispatcher("/index.jsp").forward(request, response);
        RequestDispatcher rd = null;
        rd = getServletContext().getRequestDispatcher("/index.jsp");
        rd.forward(request,response);

    }
}
