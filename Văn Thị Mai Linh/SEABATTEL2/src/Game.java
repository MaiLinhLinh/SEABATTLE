import java.util.Scanner;

public class Game {
    private Board boardA;
    private Board boardB;
    private Player playerA;
    private Player playerB;

    Scanner scanf = new Scanner(System.in);

    public Game() {
        System.out.print("Enter player 1 name: ");
        String player1Name = scanf.nextLine();
        System.out.print("Enter player 2 name: ");
        String player2Name = scanf.nextLine();
        System.out.print("Enter size of the board: ");
        int size = scanf.nextInt();
        scanf.nextLine();

        boardA = new Board(size, size);
        playerA = new Player(player1Name, boardA);

        boardB = new Board(size, size);
        playerB = new Player(player2Name, boardB);

    }

    public void Menu() {
        System.out.println("========================================");
        System.out.println("| Choice  |          Function           |");
        System.out.println("|    1    |     Display your status     |");
        System.out.println("|    2    |     Display your board      |");
        System.out.println("|    3    | Display your enemy's board  |");
        System.out.println("|    4    |          Attack             |");
        System.out.println("|    5    |     Finish your turn        |");
        System.out.println("========================================");
    }

    private String[] shipName = {
            "Patrol Boat 1",
            "Patrol Boat 2",
            "Destroyer Boat",
            "Submarine",
            "Battle Ship"
    };

    public void setUp() {
        Bonus.SETUP();
        System.out.println(playerA.getPlayerName() + "'s TURN!");
        for (String name : shipName) {
            System.out.println("Enter the position for " + name);
            playerA.getPlayerBoard().setBoat(name, playerA);
            playerA.setNumberOfShipsRemaining(playerA.getNumberOfShipsRemaining() + 1);
            playerA.getPlayerBoard().displayBoard();
        }
        System.out.println(playerB.getPlayerName() + "'s Turn");
        for (String name : shipName) {
            System.out.println("Enter the position for " + name);
            playerB.getPlayerBoard().setBoat(name, playerB);
            playerB.setNumberOfShipsRemaining(playerB.getNumberOfShipsRemaining() + 1);
            playerB.getPlayerBoard().displayBoard();
        }
        System.out.println("SETUP COMPLETE!");
    }

    public void playGame() {
        int currentPlayer = 1;
        boolean check = false;
        while (true) {
            if (currentPlayer == 1) {
                System.out.println(playerA.getPlayerName() + "'s Turn");
                while (true) {
                    System.out.println("MENU");
                    Menu();
                    System.out.print("Enter your choice: ");
                    int choice = scanf.nextInt();
                    scanf.nextLine();

                    switch (choice) {
                        case 1:
                            System.out.println("YOUR STATUS");
                            playerA.displayStatus();
                            break;
                        case 2:
                            System.out.println("YOUR BOARD");
                            playerA.getPlayerBoard().displayBoard();
                            break;
                        case 3:
                            System.out.println("YOUR ENEMY FOGGY BOARD");
                            playerB.getPlayerBoard().displayFogBoard();
                            break;
                        case 4:
                            System.out.println("ATTACK");
                            while (true) {
                                Coordinate attack = playerA.getPlayerBoard().chooseAttackCoord(playerA, playerB);
                                boolean isHit = playerA.getPlayerBoard().isHitAttack(playerA, playerB, attack);
                                playerA.getPlayerBoard().attackBoat(playerA, playerB, attack);
                                if (!isHit) {
                                    currentPlayer = 2;
                                    break;
                                }
                                if (playerB.getNumberOfShipsRemaining() == 0) {
                                    check = true;
                                    break;
                                }
                            }
                            break;
                        case 5:
                            currentPlayer = 2;
                            break;
                        default:
                            System.out.println("The choice is not valid. Please try again!");
                    }

                    if(currentPlayer == 2 || check == true)
                        break;
                }
            } else {
                System.out.println(playerB.getPlayerName() + "'s Turn");
                while (true) {
                    System.out.println("MENU");
                    Menu();
                    System.out.print("Enter your choice: ");
                    int choice = scanf.nextInt();
                    scanf.nextLine();

                    switch (choice) {
                        case 1:
                            System.out.println("YOUR STATUS");
                            playerB.displayStatus();
                            break;
                        case 2:
                            System.out.println("YOUR BOARD");
                            playerB.getPlayerBoard().displayBoard();
                            break;
                        case 3:
                            System.out.println("YOUR ENEMY FOGGY BOARD");
                            playerA.getPlayerBoard().displayFogBoard();
                            break;
                        case 4:
                            System.out.println("ATTACK");
                            while (true) {
                                Coordinate attack = playerB.getPlayerBoard().chooseAttackCoord(playerB, playerA);
                                boolean isHit = playerB.getPlayerBoard().isHitAttack(playerB, playerA, attack);
                                playerB.getPlayerBoard().attackBoat(playerB, playerA, attack);
                                if (!isHit) {
                                    currentPlayer = 1;
                                    break;
                                }
                                if (playerA.getNumberOfShipsRemaining() == 0) {
                                    check = true;
                                    break;
                                }
                            }
                            break;
                        case 5:
                            currentPlayer = 1;
                            break;
                        default:
                            System.out.println("The choice is not valid. Please try again!");
                            break;
                    }
                    if(currentPlayer == 1 || check == true)
                        break;
                }

            }
            if(check){
                Bonus.OVER();
                if(currentPlayer == 1){
                    System.out.println(playerA.getPlayerName() + " WIN!");
                    System.out.println(playerA.getPlayerName() + "'s Board");
                    playerA.getPlayerBoard().displayBoard();
                    System.out.println(playerB.getPlayerName() + "'s Board");
                    playerB.getPlayerBoard().displayBoard();
                }
                else{
                    System.out.println(playerB.getPlayerName() + " WIN!");
                    System.out.println(playerB.getPlayerName() + "'s Board");
                    playerB.getPlayerBoard().displayBoard();
                    System.out.println(playerA.getPlayerName() + "'s Board");
                    playerA.getPlayerBoard().displayBoard();
                }
                break;
            }
        }
    }

}
