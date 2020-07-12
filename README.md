# SudokuSolver
I created a Sudoku Solver using Java and the backtracking algorithm.
I created two files, one to store the boards and randomly pick one, and another file to compile the answer.
The board will be built on a 9x9 2d array, and it will contain Zeros, which are the "Empty" slots, and the numbers that come with it.
The board will have a layout like this:
            0 0 0 0 0 0 0 0 0 <br/>
            0 0 0 0 0 0 0 0 0<br/>
            0 0 0 0 0 0 0 0 0<br/>
            0 0 0 0 0 0 0 0 0<br/>
            0 0 0 0 0 0 0 0 0<br/>
            0 0 0 0 0 0 0 0 0<br/>
            0 0 0 0 0 0 0 0 0<br/>
            0 0 0 0 0 0 0 0 0<br/>
          
The framework for SudokuBoard.java:
This class is fairly easy to create. We just need to list several 2d arrays, then create a method that randomly chooses one to return. This can be done several ways, but my pickBoard() method uses a switch case that chooses the boards randomly with Math.random().
The framework for SudokuSolver.java:
First, I declared a Constructor that takes in a 2d array; that's the board, which has been called from our class, "SudokuBoard". It will set the new board into our private global variable, and copy everything over.

I needed to find out if positions are valid,so I created a method that checks the rows and columns from the position of the row/column and see if they are equal to the number that we are currently trying to find out if it's a valid spot to put the number there. Also, we want to check if the inner box (The 3x3 area) doesn't already contain this, with a simple checker:
          int r = row - row % 3;<br/>
          int c = col - col % 3;<br/>
          for (int i = r; i < r + 3; i++){<br/>
              for (int j = c; j < c + 3; j++){<br/>
                  if (board[i][j] == number) return true;<br/>
              }<br/>
          }<br/>
          return false;<br/>
            
And to put the methods I wrote into use, I compiled a getter method that wraps all of the methods up into one method, which I can call, positionIsValid, which will just return true/false if all the requirements mentioned above are set.

Now we can move onto the solution of the problem.

Using a backtracking algorithm, we can find the answer with a recursive call to your solver() method.
In order to make this recursive, we need a base case that checks if the position of the row/ column is empty, then return false, otherwise, continue. We can do this by adding in a nested for-loop, for rows and columns, and making a simple if statement that checks if the number there is zero. Next, We need to find a number that fits, between 1-9 with 1 and 9 being inclusive. This can be done with a simple for loop with the "i" value being the number to be checked and an if statement to call our positionIsValid method. If it is valid, then add the number, and we need to check if solver() is true then return true in the method. Our else case for the if solver() is true, is to just leave the box as a zero.
If we get through everything, and we haven't already returned an object, we can just return true at the end. Our method will look something like this:

              for (int row = 0; row < sizeOfGrid; row++) {<br/>
                for (int col = 0; col < sizeOfGrid; col++) {<br/>
                  /** Base Checker */<br/>
                  if (board[row][col] == boxIndexEmpty) {<br/>
                      /** Tries for number */<br/>
                      for (int number = 1; number <= sizeOfGrid; number++) {<br/>
                          /** Checks if the row is valid */<br/>
                          if (positionIsValid(row, col, number)) {<br/>
                              board[row][col] = number;<br/>
                              if (solver()) {<br/>
                                  return true;<br/>
                              } else {<br/>
                                  board[row][col] = boxIndexEmpty;                <br/>
                              }<br/>
                          }<br/>
                      }<br/>
<br/>
                      return false;<br/>
                  }<br/>
                }<br/>
              }<br/>
              return true;<br/>
            
Now all we really need is to create a main method and a print method. Printing the grid is fairly easy, just create a nested for-loop and loop through the array, printing out the individual rows and columns. For the main method, We need to create two new objects from SudokuSolver(), and SudokuBoards(). When we declare the SudokuSolver, our Constructor needs to take in a 2d array (a sudoku board), so we need to access our pickBoard() method from sudokuBoards, and pass it into that constructor. After that, we can do a simle if/else statement to see if the board can actually be solved, and print the board out if we can.

The output should looks like this:<br/>
Sudoku grid to solve<br/>
9 0 0 1 0 0 0 0 5<br/>
0 0 5 0 9 0 2 0 1<br/>
8 0 0 0 4 0 0 0 0<br/>
0 0 0 0 8 0 0 0 0<br/>
0 0 0 7 0 0 0 0 0<br/>
0 0 0 0 2 6 0 0 9<br/>
2 0 0 3 0 0 0 0 6<br/>
0 0 0 2 0 0 9 0 0<br/>
0 0 1 9 0 4 5 7 0<br/>
<br/>
Answer to Sudoku Grid:<br/>
9 3 4 1 7 2 6 8 5<br/>
7 6 5 8 9 3 2 4 1<br/>
8 1 2 6 4 5 3 9 7<br/>
4 2 9 5 8 1 7 6 3<br/>
6 5 8 7 3 9 1 2 4<br/>
1 7 3 4 2 6 8 5 9<br/>
2 9 7 3 5 8 4 1 6<br/>
5 4 6 2 1 7 9 3 8<br/>
3 8 1 9 6 4 5 7 2<br/>
