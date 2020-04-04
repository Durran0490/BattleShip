package lv.tsi.courses.battleship.infoOutput;

import java.util.Date;

public class InfoMsg {
    public InfoMsg (String player,String message) {
        Date date = new Date();
        System.out.println(String.format("[%tH-%tM-%tS %tD] PLAYER: %-10s [%s]", date, date, date, date, player,message));
    }
}
