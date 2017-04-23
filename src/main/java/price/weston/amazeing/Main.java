package price.weston.amazeing;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by wprice on 4/22/17.
 */
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private static Options createOptions() {
        Options options = new Options();

        Option rows = Option.builder("r").hasArg(true).longOpt("rows").type(Integer.class).desc("the number of rows to create").build();
        Option columns = Option.builder("c").hasArg(true).longOpt("columns").desc("the number of columns to create").type(Integer.class).build();
        Option start = Option.builder("start").hasArg(true).desc("the entrance for maze traversal").type(String.class).build();
        Option end = Option.builder("end").hasArg(true).desc("the exit for maze traversal").type(String.class).build();
        Option format = Option.builder("format").desc("the format for traversal output valid values are [grid, line, stack]").hasArg(true).type(String.class).build();
        Option travdir = Option.builder("travdir").desc("direction of traversal format [entrance, exit]").hasArg(true).type(String.class).build();
        Option help = Option.builder("help").hasArg(false).longOpt("help").desc("print options and help description").build();
        Option mazeType = Option.builder("ma").hasArg(true).desc("the type of algorithm used to generate the maze [binary_tree, sidewinder]").type(String.class).build();

        options.addOption(rows);
        options.addOption(columns);
        options.addOption(start);
        options.addOption(end);
        options.addOption(format);
        options.addOption(travdir);
        options.addOption(mazeType);
        options.addOption(help);

        return options;
    }
    public static void main(String[] args) {

        Options options = null;
        CommandLineParser parser = null;
        CommandLine commandLine = null;
        int rows = 3;
        int columns = 3;
        Maze maze = null;
        CellBlock start = null;
        CellBlock end = null;

        try {
            options = createOptions();
            parser = new DefaultParser();
            commandLine = parser.parse(options, args);

            if(commandLine.hasOption("help")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("amazeing", options);
                return;
            }

            if(commandLine.hasOption("r")) {
                rows = Integer.valueOf(commandLine.getOptionValue("r"));
            }

            if(commandLine.hasOption("c")) {
                columns = Integer.valueOf(commandLine.getOptionValue("c"));
            }

            CellBlock[][] grid = MazeHelper.populateGrid(rows, columns);
            System.out.println();
            System.out.println("Grid created: \n");
            System.out.println(MazeHelper.formatMaze(grid, null, false));

            MazeGenerationStrategy generationStrategy = MazeGenerationStrategy.BINARY_TREE;

            if(commandLine.hasOption("ma")) {
                generationStrategy = MazeGenerationStrategy.valueOf(commandLine.getOptionValue("ma").toUpperCase());
            }

            logger.info("Generating maze with {} algorithm", generationStrategy);
            MazeGenerator mazeGenerator = MazeGeneratorFactory.getMazeGenerator(generationStrategy);
            maze = mazeGenerator.generate(grid);
            System.out.println();
            System.out.println("Maze generated: \n");
            System.out.println(MazeHelper.formatMaze(maze));

            if(commandLine.hasOption("start")) {
                start = MazeHelper.fromValues(commandLine.getOptionValue("start"));
            }

            if(commandLine.hasOption("end")) {
                end = MazeHelper.fromValues(commandLine.getOptionValue("end"));
            }

            maze.traverse(start, end);
            MazeTraversalFormat format = MazeTraversalFormat.UNKNOWN;

            if(commandLine.hasOption("format")) {
                format = MazeTraversalFormat.valueOf(commandLine.getOptionValue("format").toUpperCase());
            }

            System.out.println();
            System.out.println("Maze traversed: \n");

            MazeTraversalFormatDirection direction = MazeTraversalFormatDirection.DESCENDING;

            if(commandLine.hasOption("travdir")) {
                direction = (commandLine.getOptionValue("travdir").equalsIgnoreCase("entrance"))
                        ? MazeTraversalFormatDirection.DESCENDING : MazeTraversalFormatDirection.ASCENDING;
            }
            System.out.println(MazeHelper.formatTraversalPath(maze, format, direction));


        }catch(Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}
