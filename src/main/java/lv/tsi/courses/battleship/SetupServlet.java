package lv.tsi.courses.battleship;

import lv.tsi.courses.battleship.console.messages.ConsoleMessage;
import lv.tsi.courses.battleship.model.CellState;
import lv.tsi.courses.battleship.model.Game;
import lv.tsi.courses.battleship.model.Player;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


@WebServlet(name = "SetupServlet", urlPatterns = "/setup")
public class SetupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] selected = req.getParameterValues("cells");

        var player = (Player) req.getSession().getAttribute("player");
        var messenger = (ConsoleMessage) req.getServletContext().getAttribute("consoleMessage");
        var game = (Game) req.getSession().getAttribute("game");
        player.getOwnField().clear();

        if (selected != null) {
            messenger.write(player.getName(), "ships positions are: " + Arrays.toString(selected));

            for (String addr : selected) {
                player.getOwnField().setState(addr, CellState.SHIP);
            }
        }

        if (player.getOwnField().isValid()) {
            player.setShipsSet(true);
            if (game.isShipsReady()) {
                resp.sendRedirect("/game");
            }else {
                req.getRequestDispatcher("/WEB-INF/waitSetup.jsp").include(req, resp);;
                messenger.write(player.getName(), "has finished setup");
                return;
            }
        } else {
            if (selected != null) {
                if (player.getOwnField().getListOfMisplacedCells().size() != 0) {
                    ArrayList<String> misplacedCells = player.getOwnField().getListOfMisplacedCells();

                    StringBuilder misplacedCellName = new StringBuilder();

                    for (String cells : misplacedCells) {
                        misplacedCellName.append("[").append(cells).append("] ");
                    }

                    req.setAttribute("wrongPlacedCells", "wrong placed cells: " + misplacedCellName);
                    req.setAttribute("warningMessage", "wrong Ship placement");

                    messenger.write(player.getName(), "can't start - cells " + misplacedCells + " has diagonal connection");
                } else {
                    req.setAttribute("warningMessage", "Wrong amount of placed ships. Need 20 - you have placed: " + selected.length);
                    messenger.write(player.getName(), "Wrong amount of placed ships");
                }
            } else {
                req.setAttribute("warningMessage", "Not enough ships placed. Need 20 - you have placed: 0");
                messenger.write(player.getName(), "can't start - Not enough ships placed");
            }
        }
        req.getRequestDispatcher("/WEB-INF/setupShips.jsp").include(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var player = (Player) req.getSession().getAttribute("player");
        var game = (Game) req.getSession().getAttribute("game");
        var messenger = (ConsoleMessage) req.getServletContext().getAttribute("consoleMessage");

        if (player == null) {
            resp.sendRedirect("/index.html");
            messenger.write("Unregistered", "Unregistered player");
            return;
        } else if (!game.isReady()) {
            req.getRequestDispatcher("WEB-INF/waitOpponentLogin.jsp").include(req, resp);
            return;
        } else if (player.getOwnField().isValid()) {
            if (game.isShipsReady()) {
                resp.sendRedirect("/game");
            }else {
                req.getRequestDispatcher("/WEB-INF/waitSetup.jsp").include(req, resp);;
                messenger.write(player.getName(), "has finished setup");
                return;
            }
        }
        req.getRequestDispatcher("/WEB-INF/setupShips.jsp").include(req, resp);
    }
}
