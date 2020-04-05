package lv.tsi.courses.battleship;

import lv.tsi.courses.battleship.console.messages.ConsoleMessage;
import lv.tsi.courses.battleship.model.GameManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class StartAppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        GameManager gameMgr = new GameManager();
        sce.getServletContext().setAttribute("gameManager", gameMgr);
        System.out.println("GAME MANAGER INITIALIZED");
        ConsoleMessage consoleMessage = new ConsoleMessage();
        sce.getServletContext().setAttribute("consoleMessage", consoleMessage);
        System.out.println("GAME INFO MESSENGER INITIALIZED");
    }

}
