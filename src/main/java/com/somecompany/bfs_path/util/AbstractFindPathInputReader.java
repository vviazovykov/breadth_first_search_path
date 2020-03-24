package com.somecompany.bfs_path.util;

import java.io.FileNotFoundException;

public abstract class AbstractFindPathInputReader {

    /**
     * Method to read the input (from console or from file)
     *
     * @return String representation of the input
     * @throws FileNotFoundException if file location of the input not found
     */
    public abstract String readTheInput() throws FileNotFoundException;
}
