package com.somecompany.bfs_path.util;

import java.io.InputStream;
import java.util.Scanner;

public class FindPathInputReaderStdIn extends AbstractFindPathInputReader {

    InputStream in;

    public FindPathInputReaderStdIn(InputStream in) {
        this.in = in;
    }

    // TODO: Rework to get rid of pressing ENTER two times to process the Maze further
    @Override
    public String readTheInput() {
        String fileText = "";
        Scanner input = new Scanner(in);

        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.isEmpty()) {
                input.close();
                break;
            }
            fileText += line + "\n";
        }
        return fileText;
    }
}
