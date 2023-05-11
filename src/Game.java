import java.util.ArrayDeque;
import java.util.Queue;

public class Game {
    private Board board;
    private Dice dice;
    private Queue<Player> playerList;
    private Player winner;

    public Game() {
        initializeGame();
    }

    public void initializeGame() {
        board = new Board(10, 5, 4);
        dice = new Dice(2);
        winner = null;
        playerList = new ArrayDeque<>();
        addPlayers(2);

        System.out.println("BoardSize = " + board.getBoardSize());
        System.out.println("Number of Snakes = " + board.getNumberOfSnakes());
        System.out.println("Number of Ladders = " + board.getNumberOfLadders());
        System.out.println("Number of Dice = " + dice.getDiceCount());
        System.out.println("Number of Players = " + playerList.size());
        System.out.println("------------------------------------------------------");
    }

    private void addPlayers(int noOfPlayers) {
        for(int i = 1; i <= noOfPlayers;  i++) {
            Player player = new Player(i, "Player" + i);
            playerList.add(player);
        }
    }

    public void start() {
        while (winner == null) {
            Player player = nextPlayer();
            System.out.println("Player turn: " + player.getName() + " is on " + player.getCurrentPosition());

            int diceNumbers = dice.rollDice();
            System.out.println("Dice Number: " + diceNumbers);
            int playerNewPosition = player.getCurrentPosition() + diceNumbers;
            System.out.println(player.getName() + "'s new position: " + playerNewPosition);


            if(playerNewPosition >= board.getBoardSize() * board.getBoardSize()) {
                winner = player;
                System.out.println("Hurray!!! " + player.getName() + " won the game");
                break;
            }

            Cell cell = board.getCell(playerNewPosition);

            if(cell.jump != null) {
                if(cell.jump.start > cell.jump.end) System.out.println("Found a Snake");
                else System.out.println("Found a Ladder");

                playerNewPosition = cell.jump.end;
                System.out.println(player.getName() + "'s new position: " + playerNewPosition);
            }

            player.setCurrentPosition(playerNewPosition);

            if(playerNewPosition >= board.getBoardSize() * board.getBoardSize()) {
                winner = player;
                System.out.println("Hurray!!! " + player.getName() + " won the game");
            }

            System.out.println("------------------------------------------------------");
        }
    }

    private Player nextPlayer() {
        Player player = playerList.remove();
        playerList.add(player);
        return player;
    }
}
