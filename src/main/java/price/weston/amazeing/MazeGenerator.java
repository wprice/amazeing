package price.weston.amazeing;

/**
 * Created by wprice on 4/23/17.
 */
public interface MazeGenerator {

    Maze generate(int rows, int columns);
    Maze generate(CellBlock[][] grid);
}
