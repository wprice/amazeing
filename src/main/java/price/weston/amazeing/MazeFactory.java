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
    private static final CellBlock DEFAULT_ENTRANCE = new CellBlock(0, 0);
    private static final CellBlock DEFAULT_EXIT = new CellBlock(2, 2);

    public static Maze createMaze() {
        return createMaze(DEFAULT_ROWS, DEFAULT_COLUMNS, DEFAULT_ENTRANCE, DEFAULT_EXIT, MazeGenerationStrategy.BINARY_TREE);
    }
    public static Maze createMaze(MazeGenerationStrategy generationStrategy) {
       return createMaze(DEFAULT_ROWS, DEFAULT_COLUMNS, DEFAULT_ENTRANCE, DEFAULT_EXIT, generationStrategy);
    }

    public static Maze createMaze(int rows, int columns, final CellBlock entrance, final CellBlock exit, MazeGenerationStrategy generationStrategy) {
        Maze maze = new Maze(rows, columns);
        return maze;
    }
}
