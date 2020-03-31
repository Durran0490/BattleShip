package lv.tsi.courses.battleship;

import lv.tsi.courses.battleship.model.Game;
import lv.tsi.courses.battleship.model.GameManager;
import lv.tsi.courses.battleship.model.Player;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

//        System.out.println(String.format("[%s] User's name input (\"%s\")",new Date(),name.trim()));

@WebServlet(urlPatterns = "/start")
public class BattleShipServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var name = req.getParameter("name");
        var player = new Player();
        player.setName(name);

        var game = GameManager.getIncompleteGameAndJoin(player);

        req.getSession().setAttribute("game", game);
        req.getSession().setAttribute("player", player);

        req.getRequestDispatcher("WEB-INF/waitOpponentLogin.jsp").include(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var player = (Player) req.getSession().getAttribute("player");
        var game = (Game) req.getSession().getAttribute("game");

        if (player == null) {
            resp.sendRedirect("/index.html");
            return;
        }

        if (game.isReady()) {
            resp.sendRedirect("/setup");
        } else {
            req.getRequestDispatcher("WEB-INF/waitOpponentLogin.jsp").include(req, resp);
        }
    }
}
