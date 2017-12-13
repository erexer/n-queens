import java.util.Stack;

public class NQueens {
 
  private static boolean validPosition(Stack<Integer> stack, int col) {
      if (stack.contains(col)) { // test if on same column
          return false;
      }
      else {
         int row = stack.size();
         int qRow = 0;
         boolean notValidPosition = false; //start with a valid square
         for (Integer qCol : stack) { // test if on same diagonal
             notValidPosition = ((col - qCol) == (row - qRow)) || ((col - qCol) == (qRow - row)) || notValidPosition; // if any non-valid squares are found, then notValidPosition will always be true
             qRow++;
         }
         return !notValidPosition; // return not-not-valid position
      }
  }
 
  //The solve method finds and prints out all solutions to the n-queens problem
  public static int solve(int n) {
      int numSolutions = 0;
      Stack<Integer> coordStack = new Stack<Integer>();
      int col = 0;
      while( true ) {
          while (col < n){ // increments through the rows
              if (validPosition(coordStack, col)) { // checks if valid position, and pushes
                  coordStack.push(col);
                  col = 0;
                  break;
              }
              else {
                  col++;
              }
          }
          if (col == n) { // checks if not valid position
              if (coordStack.isEmpty()){ // if the stack is empty, we're done
                  break;
              }
              else { // otherwise there was no valid position, and we backtrack to look for a solution.
                  col = coordStack.pop() + 1;
              }
          }
          if (coordStack.size() == n) { // if our stack is the size of the chess board, we've found a solution!
                  printSolution(coordStack);
                  col = coordStack.pop() + 1;
                  numSolutions++;
          }
      }
    return numSolutions;
  }
  
  // prints out a solution from the current stack.
  private static void printSolution(Stack<Integer> s) {
    for (int i = 0; i < s.size(); i ++) {
      for (int j = 0; j < s.size(); j ++) {
        if (j == s.get(i))
          System.out.print("Q ");
        else
          System.out.print("* ");
      }
      System.out.println();
    }
    System.out.println();  
  }
  
  public static void main(String[] args) {
  
     int n = 4;
  
     if (args.length == 1) {
       n = Integer.parseInt(args[0].trim());
       if (n < 1) {
         System.out.println("Incorrect parameter");
         System.exit(-1);
       }
     }
     else {
       System.out.println("Wrong number of parameters supplied");
       System.exit(-1);
     }
  
     int number = solve(n);
     System.out.println("There are " + number + " solutions to the " + n + "-queens problem.");
  }
  
}