package lv.tsi.courses.battleship;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(urlPatterns = "/login")
public class BattleShip extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<html>");
            out.println("<body>");
            out.println("<h1>Hello " + name + "!</h1>");
            out.println("</html>");
            out.println("</body>");
        }
        System.out.println(String.format("[%s] User's name input (\"%s\")",new Date(),name));
    }
}
