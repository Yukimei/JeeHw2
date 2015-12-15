import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Calculator")
public class Calculator extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String buttonName = request.getParameter("button");
        String x = request.getParameter("x1");
        String y = request.getParameter("x2");



        out.println("<!doctype html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\">");
        out.println("<title>HW2- andrewID @ yingxuem</title>");
        out.println("<style>");
        out.println("table, th, td {");
        out.println("    border: 1px solid black;");
        out.println("    border-collapse: collapse;");
        out.println("}");
        out.println("th{");
        out.println("    padding: 5px;");
        out.println("    text-align: center;");
        out.println("}");
        out.println("td{");
        out.println("    padding: 2px;");
        out.println("    text-align: center;}");
        out.println("th {");
        out.println("   color:white;   background-color:black;}");
        out.println("td#td1, td#td2, td#td3, td#td4 {  background-color:#eee;}");
        out.println("</style></head><body>");
        out.println("  <form action=\"Calculator\" method=\"POST\">");
        out.println("<table><tr> <th colspan=\"2\"> Simple Caculator</th></tr>");
        out.println("  <tr>    <td id = \"td1\"> X: </td>   <td>     <input id = \"data1\" value = \"" + (x == null ? "": x) + "\"name = \"x1\" type=\"text\" size=30/> </td>");
        out.println("  <tr>    <td id = \"td2\"> Y: <td>    <input id = \"data2\" value = \"" + (y == null ? "" : y) + "\"name = \"x2\" type=\"text\" size=30 /> ");
        out.println("<tr>");
        out.println("<td id = \"td3\" colspan=\"2\">");
        out.println("<input type=\"submit\" name = \"button\" value = \"+\">");
        out.println("<input type=\"submit\" name = \"button\" value = \"-\">");
        out.println("<input type=\"submit\" name = \"button\" value = \"*\">");
        out.println("<input type=\"submit\" name = \"button\" value = \"/\">");
        out.println("</table>");
        out.println("  </form>");
        out.println("</body>");
        out.println("</html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
//        String url =  request.getHeader("Referer");
//        System.out.println(url);

        String buttonName = request.getParameter("button");
        String x = request.getParameter("x1");
        String y = request.getParameter("x2");
//        if (!isFirstTimeVisit) {
//        if (url.startsWith("http://localhost:8080/HW2/Calculator") || url.startsWith("localhost:8080/HW2/Calculator")) {
        if (x == null) {
        	out.println("<h3><span style=\"color:red\"> No parameter for X is sent to HTTP server!</span></h3>");
        }
        
        if (y == null) {
        	out.println("<h3><span style=\"color:red\"> No parameter for Y is sent to HTTP server!</span></h3>");
        }
        
        if (x != null && y != null) {

            if (x.equals("")) {
                out.println("<h3><span style=\"color:red\"> X is empty!</span></h3>");
            }

            if (y.equals("")) {
                out.println("<h3><span style=\"color:red\"> Y is empty!</span></h3>");
            }
        }

        out.println("<!doctype html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>HW2- andrewID @ yingxuem</title>");
        out.println("<style>");
        out.println("table, th, td {");
        out.println("    border: 1px solid black;");
        out.println("    border-collapse: collapse;");
        out.println("}");
        out.println("th{");
        out.println("    padding: 5px;");
        out.println("    text-align: center;");
        out.println("}");
        out.println("td{");
        out.println("    padding: 2px;");
        out.println("    text-align: center;}");
        out.println("th {");
        out.println("   color:white;   background-color:black;}");
        out.println("td#td1, td#td2, td#td3, td#td4 {  background-color:#eee;}");
        out.println("</style></head><body>");
        if (x != null && y != null) {
            boolean inputStatus = true;
            if (!x.equals("")) {
                try {
                	x = sanitize(x);
                    Double.parseDouble(x);
                } catch (NumberFormatException e) {
                    out.println("<h3><span style=\"color:red\"> X is not a number!</span></h3>");
                    inputStatus = false;
                }
            }
            if (!y.equals("")) {
                try {
                	y = sanitize(y);
                    Double.parseDouble(y);
                } catch (NumberFormatException e) {
                    out.println("<h3><span style=\"color:red\"> Y is not a number!</span></h3>");
                    inputStatus = false;
                }
            }
            if (!x.equals("") && !y.equals("")) {
                if (inputStatus == true) {
                    double n1 = Double.parseDouble(x);
                    double n2 = Double.parseDouble(y);

                	if (n2 == 0 && buttonName.equals("/")) {
                        out.println("<h3><span style=\"color:red\">Cannot divide by zero!</span></h3>");
                    } else {

                        double answer = 0;

                        if (buttonName.equals("+")) {
                            answer = n1 + n2;
                        }

                        if (buttonName.equals("-")) {
                            answer = n1 - n2;
                        }

                        if (buttonName.equals("*")) {
                            answer = n1 * n2;
                        }

                        if (buttonName.equals("/")) {

                            answer = n1 / n2;
                        }

                        String line = String.format("%,.2f %s %,.2f %s %,.2f ", n1, buttonName, n2, "=", answer);
                        out.println("<h3><span>" + line + " </span></h3>");
                    }
                }
            }
        }

        out.println("  <form action=\"Calculator\" method=\"POST\">");
        out.println("<table><tr> <th colspan=\"2\"> Simple Caculator</th></tr>");
        out.println("  <tr>    <td id = \"td1\"> X: </td>   <td>     <input id = \"data1\" value = \"" + (x == null ? "": x) + "\"name = \"x1\" type=\"text\" size=30/> </td>");
        out.println("  <tr>    <td id = \"td2\"> Y: <td>    <input id = \"data2\" value = \"" + (y == null ? "" : y) + "\"name = \"x2\" type=\"text\" size=30 /> ");
        out.println("<tr>");
        out.println("<td id = \"td3\" colspan=\"2\">");
        out.println("<input type=\"submit\" name = \"button\" value = \"+\">");
        out.println("<input type=\"submit\" name = \"button\" value = \"-\">");
        out.println("<input type=\"submit\" name = \"button\" value = \"*\">");
        out.println("<input type=\"submit\" name = \"button\" value = \"/\">");
        out.println("</table>");
        out.println("  </form>");
        out.println("</body>");
        out.println("</html>");
    }
    private String sanitize(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;");
    }
}