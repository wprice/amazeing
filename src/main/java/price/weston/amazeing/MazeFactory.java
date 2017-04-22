package price.weston.amazeing;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provide maze construction via factory.
 *
 * @see Maze
 * @see CellBlock
 */
public class MazeFactory {

    private static final Logger logger = LoggerFactory.getLogger(MazeFactory.class);

    private static final int DEFAULT_ROWS = 3;
    private static final int DEFAULT_COLUMNS = 3;

    public static Maze createMaze() {
        return createMaze(DEFAULT_ROWS, DEFAULT_COLUMNS, MazeGenerationStrategy.BINARY_TREE);
    }
    public static Maze createMaze(MazeGenerationStrategy generationStrategy) {
       return createMaze(DEFAULT_ROWS, DEFAULT_COLUMNS, generationStrategy);
    }

    public static Maze createMaze(int rows, int columns) {
        return createMaze(rows, columns, MazeGenerationStrategy.BINARY_TREE);
    }
    public static Maze createMaze(int rows, int columns, MazeGenerationStrategy generationStrategy) {

        if(rows < 0 || columns < 0) {
            logger.warn("Invalid value for rows {} or columns {}, setting to default {}, {}", rows, columns, DEFAULT_ROWS, DEFAULT_COLUMNS);
            rows = DEFAULT_ROWS;
            columns = DEFAULT_COLUMNS;
        }

        Maze maze = new Maze(rows, columns);
        return maze;
    }
}
