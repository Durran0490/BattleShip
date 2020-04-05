package lv.tsi.courses.battleship.console.messages;

import java.util.Date;

public class ConsoleMessage {
    public void write(String player, String message){
        Date date = new Date();
        System.out.println(String.format("[%tH-%tM-%tS %tD] PLAYER: %-10s [%s]", date, date, date, date, player,message));
    }
}
