package lv.tsi.courses.battleship;

import lv.tsi.courses.battleship.console.messages.ConsoleMessage;
import lv.tsi.courses.battleship.model.Game;
import lv.tsi.courses.battleship.model.GameManager;
import lv.tsi.courses.battleship.model.Player;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/start")
public class BattleShipServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var name = req.getParameter("name");
        var player = new Player();
        player.setName(name);

        var gameMgr = (GameManager) req.getServletContext().getAttribute("gameManager");
        var messenger = (ConsoleMessage) req.getServletContext().getAttribute("consoleMessage");

        var game = gameMgr.getIncompleteGameAndJoin(player);

        req.getSession().setAttribute("game", game);
        req.getSession().setAttribute("player", player);

        req.getRequestDispatcher("WEB-INF/waitOpponentLogin.jsp").include(req, resp);
        messenger.write(player.getName(),"Entered in queue");
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
