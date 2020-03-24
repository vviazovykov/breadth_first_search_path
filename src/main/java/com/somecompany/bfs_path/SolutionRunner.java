package com.somecompany.bfs_path;

import com.somecompany.bfs_path.model.Coordinate;
import com.somecompany.bfs_path.solution.BFSMazeSolution;
import com.somecompany.bfs_path.util.AbstractFindPathInputReader;
import com.somecompany.bfs_path.util.FindPathInputReaderFile;
import com.somecompany.bfs_path.util.FindPathInputReaderStdIn;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;

public class SolutionRunner {

    private static final Logger LOG = Logger.getLogger(SolutionRunner.class);

    public static void main( String[] args ) {

        String maze = startApp();

        try {
            execute(maze);
        } catch (FileNotFoundException e) {
            LOG.error("Cannot find maze file location", e);
        }
    }

    private static String startApp() {
        String maze = "";
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("#----------------------------------------------#");
            System.out.println("Choose the option to input the Maze inputs from file as well as from standard input");
            System.out.println("1) Press \"1\" to get Maze from file");
            System.out.println("2) Press \"2\" to get Maze input from console");
            System.out.println("0) Press \"0\" to exit");
            System.out.println("#----------------------------------------------#");
            System.out.print(">");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String inputType = "";
            try {
                inputType = br.readLine();
            } catch (IOException e) {
                LOG.error("Unaccepted value is pressed");
            }

            if (inputType == null || inputType.isEmpty()) {
                System.out.println("Please, choose \"1\", \"2\" or \"0\" option \n");
                continue;
            }

            int result = 0;
            try {
                result = Integer.valueOf(inputType);
            } catch (NumberFormatException nfe) {
                LOG.error("Unaccepted value is pressed");
                continue;
            }

            switch (result) {
                case 1:
                    System.out.println("1 pressed.");
                    String mazeFromLocation = readInputFile("Please, specify file location (ex. \"maze.txt\"):");
                    if (mazeFromLocation == null || mazeFromLocation.isEmpty()) {
                        continue;
                    } else {
                        maze = mazeFromLocation;
                    }
                    break;
                case 2:
                    System.out.println("2 pressed.");
                    String mazeFromConsole = readInputConsole("Please, input the maze and press ENTER two times:");
                    if (mazeFromConsole == null || mazeFromConsole.isEmpty()) {
                        continue;
                    } else {
                        maze = mazeFromConsole;
                    }
                    break;
                case 0:
                    System.out.println("See you next time!");
                    break;
                default:
                    System.out.println("Please, choose \"1\" or \"2\" option. In order to exit press \"0\" \n");
                    continue;
            }
            isRunning = false;
        }
        return maze;
    }

    private static String readInputFile(String text) {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println(text);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                String fileLocation = br.readLine();
                AbstractFindPathInputReader findPathInputReader =
                        new FindPathInputReaderFile(
                                new File(SolutionRunner.class.getClassLoader().getResource(fileLocation).getFile())
                        );
                return findPathInputReader.readTheInput();
            } catch (Exception e) {
                LOG.error("Something wrong with your file Location. Try again.");
                continue;
            }
        }
        return null;
    }

    private static String readInputConsole(String text) {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println(text);
            AbstractFindPathInputReader consoleInputReader = new FindPathInputReaderStdIn(System.in);
            try {
                return consoleInputReader.readTheInput();
            } catch (Exception e) {
                LOG.error("Something wrong with your input. Try again.");
                continue;
            }
        }
        return null;
    }

    private static void execute(String mazeText) throws FileNotFoundException {
        Maze maze = new Maze(mazeText);
        executeBFSMazeSolution(maze);
    }

    private static void executeBFSMazeSolution(Maze maze) {
        BFSMazeSolution bfs = new BFSMazeSolution();
        List<Coordinate> path = bfs.solve(maze);
        maze.printPath(path);
        maze.reset();
    }
}