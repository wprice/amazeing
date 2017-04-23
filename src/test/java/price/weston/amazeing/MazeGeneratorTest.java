package price.weston.amazeing;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;

/**
 * Created by wprice on 4/23/17.
 */
public class MazeGeneratorTest {

    @Test
    public void testMazeGeneratorFactory() {

        MazeGenerator generator = MazeGeneratorFactory.getMazeGenerator(MazeGenerationStrategy.BINARY_TREE);
        assertThat(generator, notNullValue());
        assertThat(generator, instanceOf(MazeGenerator.class));
        assertThat(generator, instanceOf(BinaryTreeMazeGenerator.class));

        generator = MazeGeneratorFactory.getMazeGenerator("");
        assertThat(generator, notNullValue());
        assertThat(generator, instanceOf(MazeGenerator.class));
        assertThat(generator, instanceOf(BinaryTreeMazeGenerator.class));

        generator = MazeGeneratorFactory.getMazeGenerator("binary_tree");
        assertThat(generator, notNullValue());
        assertThat(generator, instanceOf(MazeGenerator.class));
        assertThat(generator, instanceOf(BinaryTreeMazeGenerator.class));

        generator = MazeGeneratorFactory.getMazeGenerator(MazeGenerationStrategy.SIDEWINDER);
        assertThat(generator, notNullValue());
        assertThat(generator, instanceOf(MazeGenerator.class));
        assertThat(generator, instanceOf(SidewinderMazeGenerator.class));

    }
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
