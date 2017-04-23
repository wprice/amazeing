package price.weston.amazeing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by wprice on 4/23/17.
 */
public class SidewinderMazeGenerator implements MazeGenerator {

    private CellBlock[][] grid;

    @Override
    public Maze generate(CellBlock[][] grid) {

        boolean east;
        boolean north;

        List<CellBlock> runList = new ArrayList<>();

        for(int i = 0; i < grid.length; i++) {

            runList = new ArrayList<>();

            for(int j = 0; j < grid[i].length; j++) {

                east = MazeHelper.atBoundary(grid.length - 1, grid[0].length - 1, grid[i][j], Compass.EAST);
                north = MazeHelper.atBoundary(grid.length - 1, grid[0].length - 1, grid[i][j], Compass.NORTH);

                runList.add(grid[i][j]);

                int rand = new Random().nextInt(2);

                //This seems wrong, we could very well end up with a non perfect maze due to never turning North
                if(east || (!north && rand == 0)) {

                    CellBlock joint = runList.get(new Random().nextInt(runList.size()));

                    if(!MazeHelper.atBoundary(grid[0].length - 1, grid[0].length, grid[i][j], Compass.NORTH)) {
                        joint.addConnection(grid[i - 1][joint.getColumn()]);
                        runList.clear();
                    }



                } else {
                    grid[i][j].addConnection(grid[i][j + 1]);
                }

            }
        }

        return new Maze(grid);
    }

    @Override
    public Maze generate(int rows, int columns) {

        return generate(MazeHelper.populateGrid(rows, columns));
    }
}
