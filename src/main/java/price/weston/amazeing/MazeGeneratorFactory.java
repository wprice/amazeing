package price.weston.amazeing;

/**
 * Created by wprice on 4/23/17.
 */
public class MazeGeneratorFactory {

    public static MazeGenerator getMazeGenerator(String type) {
        return getMazeGenerator(MazeGenerationStrategy.valueOf(type));
    }


    public static MazeGenerator getMazeGenerator(MazeGenerationStrategy strategy) {

        if(strategy.equals(MazeGenerationStrategy.BINARY_TREE)) {
            return new BinaryTreeMazeGenerator();
        } else if (strategy.equals(MazeGenerationStrategy.SIDEWINDER)) {
            return new SidewinderMazeGenerator();
        }

        return new BinaryTreeMazeGenerator();
    }
}
