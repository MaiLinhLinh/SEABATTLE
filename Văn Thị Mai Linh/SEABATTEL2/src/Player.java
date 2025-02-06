public class Player {
    private String playerName;
    private Board playerBoard;
    private int numberOfEnermyShipsDestroyed;
    private int numberOfShipsRemaining;
    private int numberOfEnermyCellHited;

    public Player(String playerName, Board playerBoard){
        this.playerName = playerName;
        this.playerBoard = playerBoard;
        numberOfEnermyCellHited = 0;
        numberOfEnermyShipsDestroyed = 0;
        numberOfShipsRemaining = 0;
    }

    public Board getPlayerBoard() {
        return playerBoard;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getNumberOfEnermyCellHited() {
        return numberOfEnermyCellHited;
    }

    public int getNumberOfEnermyShipsDestroyed() {
        return numberOfEnermyShipsDestroyed;
    }

    public int getNumberOfShipsRemaining() {
        return numberOfShipsRemaining;
    }

    public void setNumberOfEnermyCellHited(int numberOfEnermyCellHited) {
        this.numberOfEnermyCellHited = numberOfEnermyCellHited;
    }

    public void setNumberOfShipsRemaining(int numberOfShipsRemaining) {
        this.numberOfShipsRemaining = numberOfShipsRemaining;
    }

    public void setNumberOfEnermyShipsDestroyed(int numberOfEnermyShipsDestroyed) {
        this.numberOfEnermyShipsDestroyed = numberOfEnermyShipsDestroyed;
    }
    public void displayStatus(){
        System.out.println("-------------------------------------------");
        System.out.print("| The number of enemy's ship destroyed: " + getNumberOfEnermyShipsDestroyed());
        if(getNumberOfShipsRemaining() >= 10)
            System.out.println(" |");
        else
            System.out.println("  |");
        System.out.print("| The number of cell you have attacked: " + getNumberOfEnermyCellHited());
        if(getNumberOfEnermyCellHited() >= 10)
            System.out.println(" |");
        else
            System.out.println("  |");
        System.out.print("| The number of your ship remaning: " + getNumberOfShipsRemaining());
        if(getNumberOfShipsRemaining() >= 10)
            System.out.println(" |");
        else
            System.out.println("  |");
        System.out.println("-------------------------------------------");
    }
}
