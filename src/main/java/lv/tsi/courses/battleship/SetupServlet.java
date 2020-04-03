package lv.tsi.courses.battleship;

import lv.tsi.courses.battleship.model.CellState;
import lv.tsi.courses.battleship.model.Game;
import lv.tsi.courses.battleship.model.Player;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

@WebServlet(name = "SetupServlet", urlPatterns = "/setup")
public class SetupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] selected = req.getParameterValues("cells");

        var player = (Player) req.getSession().getAttribute("player");

        player.getOwnField().clear();

        if (selected != null) {
            new InfoMsg(player.getName(),"ships positions are: " + Arrays.toString(selected));
            for (String addr : selected) {
                player.getOwnField().setState(addr, CellState.SHIP);
            }
        }

        if (player.getOwnField().isValid()) {
            resp.sendRedirect("/waitSetup");
            new InfoMsg(player.getName(),"has finished setup");
        }else{
            if(player.getOwnField().getListOfMisplacedCells().size() != 0){
                StringBuilder misplacedCells = new StringBuilder();
                for (String cells :player.getOwnField().getListOfMisplacedCells()){
                    misplacedCells.append("\"").append(cells).append("\" ");
                }
                req.setAttribute("wrongPlacedCells","wrongPlacedCells: "+ misplacedCells);
                req.setAttribute("warningMessage", "wrong Ship placement");
            }else {
                req.setAttribute("warningMessage", "Not enough ships are placed, need 20");
                new InfoMsg(player.getName(), "can't start - wrong ship placement");
            }
            req.getRequestDispatcher("/WEB-INF/setupShips.jsp").include(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var player = (Player) req.getSession().getAttribute("player");
        var game = (Game) req.getSession().getAttribute("game");

        if(player == null){
            resp.sendRedirect("/index.html");
            new InfoMsg("Unregistered","Unregistered player");
        }else if(!game.isReady()){
            req.getRequestDispatcher("WEB-INF/waitOpponentLogin.jsp").include(req, resp);
        }else if(player.getOwnField().isValid()){
            resp.sendRedirect("/waitSetup");
            new InfoMsg(player.getName(),"has finished setup");
        }
        req.getRequestDispatcher("/WEB-INF/setupShips.jsp").include(req, resp);
    }
}
