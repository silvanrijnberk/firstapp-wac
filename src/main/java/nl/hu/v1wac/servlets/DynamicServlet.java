package nl.hu.v1wac.servlets;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DynamicServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String first = request.getParameter("first");
        String second = request.getParameter("second");
        String operation = request.getParameter("bt");
        PrintWriter out = response.getWriter();
        float answer = 0;
        if(operation.equals("+")) {
            answer = Float.parseFloat(first) + Float.parseFloat(second);
        }
        if(operation.equals( "-")) {
            answer = Float.parseFloat(first) - Float.parseFloat(second);
        }
        if(operation.equals("/")) {
            answer = Float.parseFloat(first) / Float.parseFloat(second);
        }

        response.setContentType("text/html");
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<body>");

        out.println("<h2> "+ Float.parseFloat(first)  + operation + Integer.parseInt(second) + "="+ answer +"!</h2>");
        out.println("</body>");
        out.println("</html>");


    }
}


