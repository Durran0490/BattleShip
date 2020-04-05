package lv.tsi.courses.battleship.model;

public class Player {
private String name;
private Field ownField = new Field();
private Field enemyField = new Field();
private boolean shipsSet;

    public void setShipsSet(boolean shipsSet) {
        this.shipsSet = shipsSet;
    }

    public boolean isShipsSet() {
        return shipsSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Field getOwnField() {
        return ownField;
    }

    public Field getEnemyField() {
        return enemyField;
    }

    public boolean isReadyToPlay(){
        return ownField.isValid();
    }
}
