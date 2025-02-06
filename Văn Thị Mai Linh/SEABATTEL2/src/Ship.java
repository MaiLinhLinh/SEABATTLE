import java.util.ArrayList;

public class Ship {
    private String shipName;
    private int shipSize;
    private char direction;
    private ArrayList<Coordinate>coord = new ArrayList<>();

    public Ship(String shipName, char direction, Coordinate crd){
        this.shipName = shipName;
        if(shipName.charAt(0) == 'P')
            shipSize = 2;
        else if(shipName.charAt(0) == 'D')
            shipSize = 3;
        else if(shipName.charAt(0) == 'S')
            shipSize = 4;
        else
            shipSize = 5;
        this.direction = direction;

        int xCor = crd.getxCor() - 'A' + 1;
        int yCor = crd.getyCor();
        if(direction == 'V'){
            for(int i = xCor; i <= xCor + shipSize - 1; i++){
                coord.add(new Coordinate((char)(i + 'A' - 1), yCor));
            }
        }
        else{
            for(int i = yCor; i <= yCor + shipSize - 1; i++){
                coord.add(new Coordinate((char)(xCor + 'A' - 1), i));
            }

        }
    }
    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipSize(int shipSize) {
        this.shipSize = shipSize;
    }

    public int getShipSize() {
        return shipSize;
    }

    public ArrayList<Coordinate> getCoord() {
        return coord;
    }

    public void setCoord(ArrayList<Coordinate> coord) {
        this.coord = coord;
    }


    public char getDirection() {
        return direction;
    }


}
