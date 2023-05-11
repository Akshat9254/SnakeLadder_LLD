import java.util.concurrent.ThreadLocalRandom;

public class Board {
    private Cell[][] cells;
    private final int boardSize;
    private final int numberOfSnakes;
    private final int numberOfLadders;

    public Board(int boardSize, int numberOfSnakes, int numberOfLadders) {
        this.boardSize = boardSize;
        this.numberOfSnakes = numberOfSnakes;
        this.numberOfLadders = numberOfLadders;
        initializeCells();
        addSnakes();
        addLadders();
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getNumberOfSnakes() {
        return numberOfSnakes;
    }

    public int getNumberOfLadders() {
        return numberOfLadders;
    }

    private void initializeCells() {
        cells = new Cell[boardSize][boardSize];

        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    private void addSnakes() {
        int numberOfSnakesAdded = 0;

        while (numberOfSnakesAdded < numberOfSnakes) {
            int snakeHead = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length - 1);
            int snakeTail = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length - 1);

            if(snakeHead <= snakeTail) continue;

            Jumper snake = new Jumper(snakeHead, snakeTail);

            Cell cell = getCell(snakeHead);
            cell.jump = snake;

            System.out.println("Adding snake on " + snakeHead + " --> " + snakeTail);

            numberOfSnakesAdded++;
        }

        System.out.println("------------------------------------------------------");
    }

    public Cell getCell(int position) {
        position--;
        int row = position / cells.length;
        int col;

        if(row % 2 == 0) col = (position % cells.length);
        else col = -(position % cells.length) + cells.length - 1;

        return cells[row][col];
    }

    private void addLadders() {
        int numberOfLaddersAdded = 0;

        while (numberOfLaddersAdded < numberOfLadders) {
            int ladderStart = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length - 1);
            int ladderEnd = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length - 1);

            if(ladderStart >= ladderEnd) continue;

            Jumper ladder = new Jumper(ladderStart, ladderEnd);

            Cell cell = getCell(ladderStart);
            cell.jump = ladder;

            System.out.println("Adding ladder on " + ladderStart + " --> " + ladderEnd);

            numberOfLaddersAdded++;
        }

        System.out.println("------------------------------------------------------");
    }
}
