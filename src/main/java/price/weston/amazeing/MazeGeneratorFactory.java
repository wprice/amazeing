package price.weston.amazeing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wprice on 4/23/17.
 */
public class MazeGeneratorFactory {

    private static final Logger logger = LoggerFactory.getLogger(MazeGeneratorFactory.class);

    public static MazeGenerator getMazeGenerator(String type) {

        try {
            return getMazeGenerator(MazeGenerationStrategy.valueOf(type.toUpperCase()));
        }catch(Exception e) {
            logger.error(e.getMessage());
        }
        return getDefaultGenerator();

    }


    public static MazeGenerator getMazeGenerator(MazeGenerationStrategy strategy) {

        if(strategy != null) {
            if(strategy.equals(MazeGenerationStrategy.BINARY_TREE)) {
                return new BinaryTreeMazeGenerator();
            } else if (strategy.equals(MazeGenerationStrategy.SIDEWINDER)) {
                return new SidewinderMazeGenerator();
            } else {
                //We should really throw here but for now return the default
                return getDefaultGenerator();
            }

        }

        return new BinaryTreeMazeGenerator();
    }

    public static MazeGenerator getDefaultGenerator() {
        return new BinaryTreeMazeGenerator();
    }
}
