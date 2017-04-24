package price.weston.amazeing;

import static org.hamcrest.MatcherAssert.assertThat;
        import static org.hamcrest.Matchers.equalTo;
        import static org.hamcrest.Matchers.instanceOf;
        import static org.hamcrest.Matchers.notNullValue;
import org.junit.Test;

/**
 * Created by wprice on 4/23/17.
 */
public class CellBlockTest {

    @Test
    public void testCellBlock() {

        CellBlock cellBlock = new CellBlock(2, 2);
        assertThat(cellBlock, notNullValue());
        assertThat(cellBlock.getRow(), equalTo(2));
        assertThat(cellBlock.getColumn(), equalTo(2));
        assertThat(cellBlock.getConnections().size(), equalTo(0));
        MazeTestHelper.typeComplies(cellBlock);
    }


    @Test
    public void testCelLBlockConnection() {
        CellBlock cellBlock = new CellBlock(0, 0);
        CellBlock cellBlock1 = new CellBlock(0, 1);
        cellBlock.addConnection(cellBlock1);
        assertThat(cellBlock.connectedTo(cellBlock1), equalTo(true));
        assertThat(cellBlock1.connectedTo(cellBlock), equalTo(true));
        assertThat(cellBlock.connectedTo(null), equalTo(false));
        assertThat(cellBlock.connectedTo(new CellBlock(2, 2)), equalTo(false));
        assertThat(cellBlock.connectionCount(), equalTo(1));
    }

}
