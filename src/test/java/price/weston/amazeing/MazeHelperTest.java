package price.weston.amazeing;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by wprice on 4/23/17.
 */
public class MazeHelperTest {


    @Test
    public void testBoundary() {

        CellBlock cellBlock = new CellBlock(0, 0);
        Assert.assertTrue(MazeHelper.atBoundary(0, 0, cellBlock, Compass.NORTH));

        cellBlock = new CellBlock(3, 2);
        Assert.assertFalse(MazeHelper.atBoundary(2, 2, cellBlock, Compass.NORTH));

    }
}
