package price.weston.amazeing;

import org.junit.Test;

/**
 * Created by wprice on 4/23/17.
 */
public class MazeGeneratorTest {

    @Test
    public void testBinaryTree() {
        BinaryTreeMazeGenerator bt = new BinaryTreeMazeGenerator();
        Maze maze = bt.generate(8, 8);
        MazeHelper.dumpMaze(maze);
        maze.traverse(new CellBlock(0, 0), new CellBlock(7, 7));
        System.out.println(MazeHelper.formatTraversalPath(maze));

    }

    @Test
    public void testSidewinder() {
        SidewinderMazeGenerator sw = new SidewinderMazeGenerator();
        Maze maze = sw.generate(8, 8);
        MazeHelper.dumpMaze(maze);
        maze.traverse(new CellBlock(0, 0), new CellBlock(7, 7));
        System.out.println(MazeHelper.formatTraversalPath(maze));
    }
}
