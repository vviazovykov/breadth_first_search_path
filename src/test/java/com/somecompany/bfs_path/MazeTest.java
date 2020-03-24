package com.somecompany.bfs_path;

import com.somecompany.bfs_path.model.Coordinate;
import com.somecompany.bfs_path.solution.BFSMazeSolution;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

public class MazeTest {

    private Maze CUT;

    private String inputMaze = String.format(
            "######################################\n" +
            "#....................................#\n" +
            "#..S...#......................#......#\n" +
            "#.....1#......................#......#\n" +
            "#.............................#......#\n" +
            "#....................................#\n" +
            "#....................................#\n" +
            "#..............#.....................#\n" +
            "#............#.......................#\n" +
            "#..........#.........................#\n" +
            "#....................................#\n" +
            "#.....................#..........#...#\n" +
            "#.....................#....E.....#...#\n" +
            "#.....................#..........#...#\n" +
            "#....................................#\n" +
            "######################################\n"
    );

    private String expectedMaze = String.format(
            "######################################\n" +
            "#....................................#\n" +
            "#..S111#......................#......#\n" +
            "#.....1#......................#......#\n" +
            "#.....1111111111111111111111..#......#\n" +
            "#..........................1.........#\n" +
            "#..........................1.........#\n" +
            "#..............#...........1.........#\n" +
            "#............#.............1.........#\n" +
            "#..........#...............1.........#\n" +
            "#..........................1.........#\n" +
            "#.....................#....1.....#...#\n" +
            "#.....................#....E.....#...#\n" +
            "#.....................#..........#...#\n" +
            "#....................................#\n" +
            "######################################\n"
    );

    @Before
    public void setUp() throws FileNotFoundException {
        CUT = new Maze(inputMaze);
    }

    @Test
    public void printPath_ShouldPrintMazeWithShortestPath() {
        // Given
        BFSMazeSolution bfs = new BFSMazeSolution();
        List<Coordinate> path = bfs.solve(CUT);
        // When
        String actualMazeWithShortMaze = CUT.printPath(path);
        // Then
        Assert.assertEquals(expectedMaze, actualMazeWithShortMaze);
    }
}
