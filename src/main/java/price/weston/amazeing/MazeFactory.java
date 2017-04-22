package price.weston.amazeing;


/**
 * Created by wprice on 4/21/17.
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

    public static Maze createMaze(int rows, int columns, CellBlock entrance, CellBlock exit, MazeGenerationStrategy generationStrategy) {
        Maze maze = new Maze(rows, columns, entrance, exit);
        return maze;
    }
}
