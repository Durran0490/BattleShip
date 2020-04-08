package lv.tsi.courses.battleship;

import lv.tsi.courses.battleship.model.Game;
import lv.tsi.courses.battleship.model.Player;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "finishServlet", urlPatterns = "/finish")
public class finishServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var player = (Player) request.getSession().getAttribute("player");
        var game = (Game) request.getSession().getAttribute("game");
        if (game != null) {
            if (game.isFinished()) {
                if (game.getWinner().equals(player)) {
//                    request.getSession().setAttribute("winner", game.getWinner());
                    request.getRequestDispatcher("/WEB-INF/winner.jsp").include(request, response);
                } else {
                    request.getRequestDispatcher("/WEB-INF/getLuckyNextTime.jsp").include(request, response);
                }
            }else{
                response.sendRedirect("/setup");
            }
        }else{
            response.sendRedirect("/index.html");
            return;
        }
    }
}
