/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nastya Winehouse
 */
public class ServletChart2 extends HttpServlet {

public void doPost(HttpServletRequest request, 
                   HttpServletResponse response)
                             throws ServletException, IOException
{
    PrintWriter out = new PrintWriter(response.getWriter());
    try {
        String param = request.getParameter("chart");
        response.setContentType("text/html");

        out.println("<!DOCTYPE html PUBLIC " +
                    "\"-//W3C//DTD HTML 4.01 Transitional//EN\" " +
                    "\"http://www.w3.org/TR/html4/loose.dtd\"> ");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>JFreeChart : servlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<H2>JFreeChart Servlet</H2>");
        out.println("<P>");
        out.println("Please choose a chart type :");
        out.println("<FORM ACTION=\"ServletChart2\" METHOD=POST>");
        String barChecked  = (param.equals( "bar") ? " CHECKED" : "");
        String lineChecked = (param.equals("line") ? " CHECKED" : "");
//        out.println("<INPUT TYPE=\"radio\" NAME=\"chart\"
//                      VALUE=\"bar\"" + barChecked + "> Bar Chart");
//        out.println("<INPUT TYPE=\"radio\" NAME=\"chart\" 
//                      VALUE=\"line\"" + lineChecked + "> Line Chart");
        out.println("<P>");
        out.println("<INPUT TYPE=\"submit\" VALUE=\"Generate chart\">");
        out.println("</FORM>");
        out.println("<P>");
        out.println("<IMG SRC=\"ServletChart2Generator?type=" + param
                             + "\" BORDER=1 WIDTH=600 HEIGHT=400/>");
        out.println("</body>");
        out.println("</html>");

        out.flush();
    } catch (Exception e) {
        System.err.println(e.toString());
    } finally {
        out.close();
    }
}
}
