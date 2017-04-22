package price.weston.amazeing;


/**
 * Provide maze construction via factory.
 *
 * @see Maze
 * @see CellBlock
 */
public class MazeFactory {

    private static final int DEFAULT_ROWS = 3;
    private static final int DEFAULT_COLUMNS = 3;

    public static Maze createMaze() {
        return createMaze(DEFAULT_ROWS, DEFAULT_COLUMNS, MazeGenerationStrategy.BINARY_TREE);
    }
    public static Maze createMaze(MazeGenerationStrategy generationStrategy) {
       return createMaze(DEFAULT_ROWS, DEFAULT_COLUMNS, generationStrategy);
    }

    public static Maze createMaze(int rows, int columns, MazeGenerationStrategy generationStrategy) {
        Maze maze = new Maze(rows, columns);
        return maze;
    }
}
