# mazeTraverse
Devon Wallerson
10/6/2023 - Maze Recursion Java Project

# How It Works #

Reading the Maze: The program reads the maze data from a text file called "maze.dat." The maze is represented as a 2D grid of characters, where 'X' represents walls, '-' represents the exit, and '+' marks the starting point.

Finding the Start: The program searches for the starting point marked by '+' in the maze and records its coordinates.

Recursive Traversal: It uses a recursive function to explore the maze. The algorithm checks neighboring cells in the up, down, left, and right directions. It avoids obstacles ('X'), tracks its path with '+', and backtracks if necessary.

Solution or Dead End: If the algorithm finds a path to the exit ('-'), it prints the solution. If it reaches a dead end or goes out of bounds, it backtracks and explores other paths.
    
# Dependencies #

  This project uses standard Java libraries for file handling and IO operations. There are no external      dependencies. You can also swap your maze.dat file as long as it follows the same specifications.
