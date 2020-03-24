package com.somecompany.bfs_path.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FindPathInputReaderFile extends AbstractFindPathInputReader {

    File file;

    public FindPathInputReaderFile(File file) {
        this.file = file;
    }

    @Override
    public String readTheInput() throws FileNotFoundException {
        String fileText = "";
        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {
                fileText += input.nextLine() + "\n";
            }
        }
        return fileText;
    }
}
