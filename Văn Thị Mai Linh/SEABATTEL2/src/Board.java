import java.util.ArrayList;
import java.util.Scanner;

public class Board {
    private int row, col;
    private char[][] fogBoard = new char[100][100];
    private char[][] board = new char[100][100];
    ArrayList<Ship> ships = new ArrayList<>(); // luu cac tau

    public Board(int row, int col) {
        this.row = row;
        this.col = col;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                board[i][j] = '~';
                fogBoard[i][j] = '~';
            }
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public char[][] getFogBoard() {
        return fogBoard;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public void displayFogBoard() {
        System.out.print("|   |");
        for (int i = 1; i <= row; i++) {
            System.out.print(" " + i + " |");
        }
        System.out.println();

        for (int i = 1; i <= row; i++) {
            System.out.printf("| %c |", 'A' + i - 1);
            for (int j = 1; j <= col; j++) {
                if (fogBoard[i][j] == 'X')
                    System.out.print(" X ");
                else if (fogBoard[i][j] == '-')
                    System.out.print(" - ");
                else if (fogBoard[i][j] == '~')
                    System.out.print(" ~ ");
                if (j >= 10) {
                    System.out.print(" |");
                } else
                    System.out.print("|");
            }
            System.out.println();

        }
    }

    public void displayBoard() {
        System.out.print("|   |");
        for (int i = 1; i <= row; i++) {
            System.out.print(" " + i + " |");
        }
        System.out.println();

        for (int i = 1; i <= row; i++) {
            System.out.printf("| %c |", 'A' + i - 1);
            for (int j = 1; j <= col; j++) {
                if (board[i][j] == 'X')
                    System.out.print(" X ");
                else if (board[i][j] == '-')
                    System.out.print(" - ");
                else if (board[i][j] == '~')
                    System.out.print(" ~ ");
                else
                    System.out.print(" " + board[i][j] + " ");
                if (j >= 10) {
                    System.out.print(" |");
                } else
                    System.out.print("|");
            }
            System.out.println();

        }
    }

    Scanner scanf = new Scanner(System.in);

    // place boat

    public boolean isPlaced(Ship ship, Player player){
        for(Coordinate coord: ship.getCoord()){
            int xCor = coord.getxCor() - 'A' + 1;
            int yCor = coord.getyCor();
            if(player.getPlayerBoard().getBoard()[xCor][yCor] != '~')
                return true;// da co thuyen
        }
        return false;
    }


    public void setBoat(String shipName, Player player) {
        System.out.println("You need enter the coordinates (x,y) of the bow of the boat.");
        int xCor, yCor;
        Ship ship;
        char direction;
        while(true) {
            player.getPlayerBoard().displayBoard();
            while (true) {
                System.out.print("Enter x (A, B, C...): ");
                xCor = scanf.next().charAt(0) - 'A' + 1;
                System.out.print("Enter y (1, 2, 3...): ");
                yCor = scanf.nextInt();
                scanf.nextLine();
                if ((xCor <= row && xCor > 0) && (yCor <= col && yCor > 0)) {
                    break;
                } else {
                    System.out.println("The coordinates is not valid. Please try again!");
                }
            }
            Coordinate crd = new Coordinate((char) (xCor + 'A' - 1), yCor);

            while (true) {
                System.out.println("Vertical - V, Horizontal - H");
                System.out.print("Enter the direction (V or H): ");
                direction = scanf.next().charAt(0);

                if (direction == 'H' || direction == 'V')
                    break;
                else {
                    System.out.println("The direction is not valid. Please try again!");
                }
            }
            ship = new Ship(shipName, direction, crd);
            if(isPlaced(ship, player) == false)
                break;
            else
                System.out.println("This position had boat. Please choose other position!");

        }
        ships.add(ship);
        if (direction == 'V') {
            for (int i = xCor; i <= xCor + ship.getShipSize() - 1; i++) {
                player.getPlayerBoard().getBoard()[i][yCor] = shipName.charAt(0);
            }
        } else {
            for (int i = yCor; i <= yCor + ship.getShipSize() - 1; i++) {
                player.getPlayerBoard().getBoard()[xCor][i] = shipName.charAt(0);
            }
        }
        System.out.println("The " + shipName + " set successfully!");
    }

    public boolean isShipSunk(Ship ship, Player enemy) {
        ArrayList<Coordinate> coord = ship.getCoord();
        for (Coordinate crd : coord) {
            int xCor = crd.getxCor() - 'A' + 1;
            int yCor = crd.getyCor();
            if (enemy.getPlayerBoard().getBoard()[xCor][yCor] != 'X')
                return false;
        }
        return true;
    }

    public boolean isHitAttack(Player player, Player enemy, Coordinate crd) {
        int xCor = crd.getxCor() - 'A' + 1;
        int yCor = crd.getyCor();
        if (enemy.getPlayerBoard().getBoard()[xCor][yCor] != 'X' && enemy.getPlayerBoard().getBoard()[xCor][yCor] != '-' && enemy.getPlayerBoard().getBoard()[xCor][yCor] != '~')
            return true;
        return false;
    }

    // attack
    public Coordinate chooseAttackCoord(Player player, Player enemy) {
        int xCor, yCor;
        while (true) {
            enemy.getPlayerBoard().displayFogBoard();
            System.out.println("Enter the coordinate (x, y) to attack!");
            System.out.print("Enter x (A, B, C...): ");
            xCor = scanf.next().charAt(0) - 'A' + 1;
            System.out.print("Enter y (1, 2, 3...): ");
            yCor = scanf.nextInt();
            if ((xCor <= row && xCor > 0) && (yCor <= col && yCor > 0)) {
                if (enemy.getPlayerBoard().getFogBoard()[xCor][yCor] != '~')
                    System.out.println("This coordinate was attacked. Please choose other coordinate!");
                else
                    return new Coordinate((char) (xCor + 'A' - 1), yCor);
            } else
                System.out.println("The coordinates is not valid. Please try again!");
        }
    }

    public void attackBoat(Player player, Player enemy, Coordinate coord) {
        int xCor = coord.getxCor() - 'A' + 1;
        int yCor = coord.getyCor();
        if (isHitAttack(player, enemy, coord)) {
            enemy.getPlayerBoard().getBoard()[xCor][yCor] = 'X';
            enemy.getPlayerBoard().getFogBoard()[xCor][yCor] = 'X';
            System.out.println("Hitted!");
            player.setNumberOfEnermyCellHited(player.getNumberOfEnermyCellHited() + 1);
            for (int i = enemy.getPlayerBoard().getShips().size() - 1; i >= 0; i--) {
                Ship ship = enemy.getPlayerBoard().getShips().get(i);
                if (isShipSunk(ship, enemy)) {
                    System.out.println("The " + ship.getShipName() + " sunk!");
                    enemy.getPlayerBoard().getShips().remove(i);
                    player.setNumberOfEnermyShipsDestroyed(player.getNumberOfEnermyShipsDestroyed() + 1);
                    enemy.setNumberOfShipsRemaining(enemy.getNumberOfShipsRemaining() - 1);
                }
            }
        } else{
            enemy.getPlayerBoard().getBoard()[xCor][yCor] = '-';
            enemy.getPlayerBoard().getFogBoard()[xCor][yCor] = '-';
            player.setNumberOfEnermyCellHited(player.getNumberOfEnermyCellHited() + 1);
            System.out.println("Missed!");

        }
    }



}
