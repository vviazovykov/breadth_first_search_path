package com.somecompany.bfs_path;

import com.somecompany.bfs_path.model.Coordinate;
import com.somecompany.bfs_path.solution.BFSMazeSolution;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class SolutionRunner {

    private static final Logger LOG = Logger.getLogger(SolutionRunner.class);

    public static void main( String[] args ) {

        File mazeFile = new File(SolutionRunner.class.getClassLoader().getResource("maze.txt").getFile());

        try {
            execute(mazeFile);
        } catch (FileNotFoundException e) {
            LOG.error("Cannot find maze file location", e);
        }
    }

    private static void execute(File mazeFile) throws FileNotFoundException {
        Maze maze = new Maze(mazeFile);
        executeBFSMazeSolution(maze);
    }

    private static void executeBFSMazeSolution(Maze maze) {
        BFSMazeSolution bfs = new BFSMazeSolution();
        List<Coordinate> path = bfs.solve(maze);
        maze.printPath(path);
        maze.reset();
    }
}