package lv.tsi.courses.battleship;

import lv.tsi.courses.battleship.infoOutput.InfoMsg;
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
import java.util.Map;

@WebServlet(name = "SetupServlet", urlPatterns = "/setup")
public class SetupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] selected = req.getParameterValues("cells");

        var player = (Player) req.getSession().getAttribute("player");

        player.getOwnField().clear();

        if (selected != null) {

            new InfoMsg(player.getName(), "ships positions are: " + Arrays.toString(selected));

            for (String addr : selected) {
                player.getOwnField().setState(addr, CellState.SHIP);
            }
        }

        if (player.getOwnField().isValid()) {

            resp.sendRedirect("/waitSetup");
            new InfoMsg(player.getName(), "has finished setup");

        } else {

            if (player.getOwnField().getListOfMisplacedCells().size() != 0 && selected != null) {
                ArrayList<String> misplacedCells = player.getOwnField().getListOfMisplacedCells();

/** attempt to add CellState.WRONG **/
//                player.getOwnField().clear();
//
//                for (String addr : selected) {
//                    for (String missedCells : misplacedCells) {
//                        if(addr.equals(missedCells)) {
//                            player.getOwnField().setState(addr, CellState.WRONG);
//                        }else{
//                            player.getOwnField().setState(addr, CellState.SHIP);
//                        }
//                    }
//                }

                StringBuilder misplacedCellName = new StringBuilder();

                for (String cells : misplacedCells) {
                    misplacedCellName.append("[").append(cells).append("] ");
                }

                req.setAttribute("wrongPlacedCells", "wrong placed cells: " + misplacedCellName);
                req.setAttribute("warningMessage", "wrong Ship placement");

                new InfoMsg(player.getName(), "can't start - cells " + misplacedCells + " has diagonal connection");

/** attempt to debug CellState.WRONG **/
//                var player2 = (Player) req.getSession().getAttribute("player");
//                Map<String, CellState> cellsMap  = player2.getOwnField().getCells();
//                StringBuilder cellsWrong = new StringBuilder();
//                StringBuilder cellsShip = new StringBuilder();
//                for(Map.Entry<String, CellState> c: cellsMap.entrySet()){
//                    if(c.getValue()== CellState.SHIP){
//                        cellsShip.append("[").append(c.getValue()).append(" ").append(c.getKey()).append("] ");
//                    }else if((c.getValue()== CellState.WRONG)){
//                        cellsWrong.append("[").append(c.getValue()).append(" ").append(c.getKey()).append("] ");
//                    }
//                }
//                System.out.println("=W="+cellsWrong+ "=S="+cellsShip);

            } else {
                req.setAttribute("warningMessage", "Not enough ships placed, need 20");
                new InfoMsg(player.getName(), "can't start - Not enough ships placed");
            }

            req.getRequestDispatcher("/WEB-INF/setupShips.jsp").include(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var player = (Player) req.getSession().getAttribute("player");
        var game = (Game) req.getSession().getAttribute("game");

        if (player == null) {
            resp.sendRedirect("/index.html");
            new InfoMsg("Unregistered", "Unregistered player");

        } else if (!game.isReady()) {
            req.getRequestDispatcher("WEB-INF/waitOpponentLogin.jsp").include(req, resp);

        } else if (player.getOwnField().isValid()) {
            resp.sendRedirect("/waitSetup");

            new InfoMsg(player.getName(), "has finished setup");

        }
        req.getRequestDispatcher("/WEB-INF/setupShips.jsp").include(req, resp);
    }
}
