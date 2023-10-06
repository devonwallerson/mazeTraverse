// Devon Wallerson
// CS 114
// Maze Recursion Java Project


// Importing IOExecption to ensure that file input does not result in error.
// Importing BufferedReader and FilerReader to scan the file.
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;


public class Main {

// Instantiailzing a firstRun variable to determine whether this is the user's first
// run through the maze to prevent calling recursively repeatedly on the 
// starting icon.
private static boolean firstRun = true;

// Main method.
public static void main(String[]args) throws IOException{
   // Setting a filePath to the maze.dat file in the folder.
   String filePath = "maze.dat";
   try {
      // Instantializing the 2D character Array that will contain the maze.
      char[][] charArray = textToArray(filePath);

      // Creating an array that will hold the coordinates of whether the user starts in the maze.
      int[]start = findStart(charArray);

      // Print the initial maze array before it is traversed.
      for (char[] row : charArray) {
          for (char value : row) {
              System.out.print(value);
          }
          System.out.println();
      }
      
      // Calling recursive method with the maze array and the starting coordinates of the maze.
      traverse(charArray,start[0],start[1]);
   
  } catch (IOException e) {
      // Catches any IO Exceptions and prints information about the error to the user.
      e.printStackTrace();
  }

}

// findStart method takes in the 2D Maze Aray and returns an integer array returning the coordinates.
public static int[] findStart(char[][] arr) {
   int[] start = {1, 1}; // Instantializing start array with auto coordinates of (1,1)

   for (int i = 0; i < arr.length; i++) {
       for (int j = 0; j < arr[i].length; j++) { // Iterating through 2D array with for loops.
           if (arr[i][j] == '+') { // ( + ) Represents the start of the Maze.
               start[0] = i; // Set the row index
               start[1] = j; // Set the column index
               System.out.println(start[0]+","+start[1]); // Printing coordinates for user.
               return start; // Return int array with coordinates.
           }
       }
   }

   return start; // Return the starting position.
}

// Recursive traverse maze method.
public static boolean traverse(char[][] arr, int x, int y){

   // numRows = number of rows in array
   // numCols = number of columns in array.
   int numRows = arr.length;
   int numCols = arr[0].length;

   if (x < 0 || x > numRows || y<0 || y > numCols) { // Checking if within bounds of
      for (char[] row : arr) {
          for (char value : row) {
              System.out.print((value));
          }
          System.out.println(); // Print out of bounds + Array for testing.
      }
      System.out.println("Out of bounds."); 
      return false; // Returns false : We were not able to find a solution and a way to the exit of the maze.
   }

   if (arr[x][y] == '-'){ //Checking if we found a solution!
      for (char[] row : arr) {
          for (char value : row) {
              System.out.print((value));
          }
          System.out.println(); // Prints array and that we found a solution.
      }
      System.out.println("Found solution.");
      return true; // Returns true : The program was able to succesfully find the exit.
   }  

   if (arr[x][y] == 'X') { // Hitting X means we have hit a wall and can not travel in that direction.
      return false;
   }
   
   if (arr[x][y] == '+' && !firstRun){ // Preventing us from recursively moving between starting point and first place.
         return false;
   }
   arr[x][y]='+'; // If all these conditions are not met, then we assign the space traveled and on the path to the end.
   firstRun=false;

   if (traverse(arr,x+1,y) || traverse(arr,x-1,y) || traverse(arr,x,y+1) || traverse(arr,x,y-1)){
      // If we can recursively move left, right, up, or down, we return true and keep moving.
      return true;
   } else {
      // If we can not, we return false and name the place not along the track to the destination.
      arr[x][y]='.';
      return false;
   }

}


// File to Array Method.
public static char[][] textToArray(String fileName) throws IOException {
      // Once again, looking to handle a possible IO Error.

      try (BufferedReader reader1 = new BufferedReader(new FileReader(fileName))) {
         // Instantiating a string that holds the row, rowCounter, and maxRowLength Variables.
          String row;
          int rowCount = 0;
          int maxRowLength = 0;

          // Count the number of rows and determine the maximum row length
          while ((row = reader1.readLine()) != null) { // When there are values to the row, add to the row counter
              rowCount++; // Add to the rowCounter variable
              if (row.length() > maxRowLength) {
                  maxRowLength = row.length(); // If the row length is longer than the previous max, assign it the value of the length.
              }
          }

          // Create the result character array.
          char[][] result = new char[rowCount][maxRowLength];

          reader1.close(); // Close and reopen the file to reset the stream

          BufferedReader reader2 = new BufferedReader(new FileReader(fileName));

          // Instantializing a currentROw variable to help the reader understand where it is in the file.
          int currentRow = 0;

         // While there is words on each row, create a new row with all the values of that row.
          while ((row = reader2.readLine()) != null) {
              char[] rowValues = new char[maxRowLength];
              for (int i = 0; i < row.length(); i++){
                  rowValues[i] = row.charAt(i);
              }

              result[currentRow] = rowValues; // Assign the values of that row to the resultArray.
              currentRow++; // Keep moving down the file.
          }
          reader2.close(); // Close second reader.

          return result; // Return the result array.

         // This project should recursively solve any solvable maze problem using backtracking. Thank you for reading! 
   }
}

}