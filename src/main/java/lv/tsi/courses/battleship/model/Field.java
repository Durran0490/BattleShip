package lv.tsi.courses.battleship.model;

import lv.tsi.courses.battleship.InfoMsg;

import java.util.*;

public class Field {
    private Map<String, CellState> cells = new HashMap<>();
    private  ArrayList<String> listOfMisplacedCells = new ArrayList<>();

    public CellState getState(String addr) {
        return cells.getOrDefault(addr, CellState.EMPTY);
    }

    public ArrayList<String> getListOfMisplacedCells() {
        return listOfMisplacedCells;
    }

    public void setState(String addr, CellState state) {
        cells.put(addr, state);
    }

    public void clear() {
        cells.clear();
    }

    public boolean isValid() {
        if(cells != null){
            if(cells.size() != 0){
                listOfMisplacedCells = diagonalConnection();
            }
        }
        if(cells.values().stream().filter(s -> s == CellState.SHIP).count() == 20){
            return listOfMisplacedCells.size() == 0;
        }
        return false;
    }

    private ArrayList<String> diagonalConnection() {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> diagonalCells;

        var cellChecker = new CellChecker();

        for (Map.Entry<String, CellState> cell : cells.entrySet()) {

            cellChecker.setCell(cell.getKey());
            diagonalCells = cellChecker.diagonalCells();

             for (Map.Entry<String, CellState> otherCells : cells.entrySet())  {
                 for (String diagCell : diagonalCells) {
                    if (otherCells.getKey().equals(diagCell)) {
                        new InfoMsg("", "cell " + diagCell + " has diagonal connection");
                        if(!list.contains(otherCells.getKey())) {
                            list.add(otherCells.getKey());
                        }
                    }
                }
            }
        }
        return list;
    }

    class CellChecker {

        private String letter;
        private String number;

        private List<String> cellLetter = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J"));
        private List<String> cellNumber = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));

        public void setCell(String cell) {
            letter = cell.substring(0, 1);
            number = cell.substring(1, 2);
        }

        public ArrayList<String> diagonalCells() {
            ArrayList<String> list = new ArrayList<>();
            String lowerRight = lowerRight();
            String lowerLeft = lowerLeft();
            String upperLeft = upperLeft();
            String upperRight = upperRight();

            if (upperRight != null) list.add(upperRight);
            if (lowerRight != null) list.add(lowerRight);
            if (lowerLeft != null) list.add(lowerLeft);
            if (upperLeft != null) list.add(upperLeft);

            return list;
        }

        private String upperRight() {
            if (letter.equals("J") || number.equals("1")) {
                return null;
            } else {
                return cellLetter.get(cellLetter.indexOf(letter) + 1) + cellNumber.get(cellNumber.indexOf(number) - 1);
            }
        }

        private String lowerRight() {
            if (letter.equals("J") || number.equals("10")) {
                return null;
            } else {
                return cellLetter.get(cellLetter.indexOf(letter) + 1) + cellNumber.get(cellNumber.indexOf(number) + 1);
            }
        }

        private String lowerLeft() {
            if (letter.equals("A") || number.equals("10")) {
                return null;
            } else {
                return cellLetter.get(cellLetter.indexOf(letter) - 1) + cellNumber.get(cellNumber.indexOf(number) + 1);
            }
        }

        private String upperLeft() {
            if (letter.equals("A") || number.equals("1")) {
                return null;
            } else {
                return cellLetter.get(cellLetter.indexOf(letter) - 1) + cellNumber.get(cellNumber.indexOf(number) - 1);
            }
        }
    }

}
