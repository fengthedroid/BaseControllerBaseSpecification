package io.pandaria;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Feng on 30-Nov-14.
 */

public class HelloServlet extends HttpServlet {
    private static final String DEFAULT_USER = "Guest";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user");
        if (user == null)
            user = DEFAULT_USER;
        ServletContext c = this.getServletContext();
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.append("Hello, ").append(user).append("!<br/><br/>\r\n")
                .append("context: "+c.getInitParameter("settingOne")+"<br/>\r\n")
        .append("servlet: "+this.getInitParameter("database"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
