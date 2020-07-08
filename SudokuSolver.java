public class SudokuSolver {
    private int[][] board;
    public static final int boxIndexEmpty = 0;
    public static final int sizeOfGrid = 9;

    public SudokuSolver(int[][] board) {
        this.board = new int[sizeOfGrid][sizeOfGrid];

        for (int i = 0; i < sizeOfGrid; i++) {
            for (int j = 0; j < sizeOfGrid; j++) {
                this.board[i][j] = board[i][j];
            }
        }
    }

    private boolean rowChecker(int row, int number) {
        for (int i = 0; i < sizeOfGrid; i++)
            if (board[row][i] == number)
                return true;

        return false;
    }

    // we check if a possible number is already in a column
    private boolean colChecker(int col, int number) {
        for (int i = 0; i < sizeOfGrid; i++)
            if (board[i][col] == number)
                return true;

        return false;
    }

    // we check if a possible number is in its 3x3 box
    private boolean inBox(int row, int col, int number) {
        int r = row - row % 3;
        int c = col - col % 3;

        for (int i = r; i < r + 3; i++)
            for (int j = c; j < c + 3; j++)
                if (board[i][j] == number)
                    return true;

        return false;
    }

    // combined method to check if a number possible to a row,col position is ok
    private boolean positionIsValid(int row, int col, int number) {
        return !rowChecker(row, number) && !colChecker(col, number) && !inBox(row, col, number);
    }

    public boolean solver() {
        for (int row = 0; row < sizeOfGrid; row++) {
            for (int col = 0; col < sizeOfGrid; col++) {
                /** Base Checker */
                if (board[row][col] == boxIndexEmpty) {
                    /** Tries for number */
                    for (int number = 1; number <= sizeOfGrid; number++) {
                        /** Checks if the row is valid */
                        if (positionIsValid(row, col, number)) {
                            board[row][col] = number;

                            if (solver()) {
                                return true;
                            } else {
                                board[row][col] = boxIndexEmpty;
                            }
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

    public void display() {
        for (int i = 0; i < sizeOfGrid; i++) {
            for (int j = 0; j < sizeOfGrid; j++) {
                System.out.print(" " + board[i][j]);
            }

            System.out.println();
        }

        System.out.println();
    }

    public static void main(String[] args) {
        SudokuBoards sudokuBoards = new SudokuBoards();
        SudokuSolver sudoku = new SudokuSolver(sudokuBoards.pickBoard());
        System.out.println("Sudoku grid to solve");
        sudoku.display();

        // we try resolution
        if (sudoku.solver()) {
            System.out.println("Sudoku Grid solved with simple BT");
            sudoku.display();
        } else {
            System.out.println("Unsolvable");
        }
    }

}