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

        Option rows = Option.builder("r").hasArg(true).longOpt("rows").type(Integer.class).build();
        Option columns = Option.builder("c").hasArg(true).longOpt("columns").type(Integer.class).build();
        Option start = Option.builder("start").hasArg(true).type(String.class).build();
        Option end = Option.builder("end").hasArg(true).type(String.class).build();
        Option format = Option.builder("format").hasArg(true).type(String.class).build();
        Option help = Option.builder("help").hasArg(false).longOpt("help").build();

        options.addOption(rows);
        options.addOption(columns);
        options.addOption(start);
        options.addOption(end);
        options.addOption(format);
        options.addOption(help);

        return options;
    }
    public static void main(String[] args) {

        Options options = null;
        CommandLineParser parser = null;
        CommandLine commandLine = null;
        int rows = -1;
        int columns = - 1;
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

            maze = MazeFactory.createMaze(rows, columns);
            System.out.println();
            System.out.println("Grid created: \n");
            System.out.println(MazeHelper.formatMaze(maze));

            maze.generateMaze();

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
            System.out.println(MazeHelper.formatTraversalPath(maze, format));


        }catch(Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}
