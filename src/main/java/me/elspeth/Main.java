package me.elspeth;

import java.io.File;
import java.time.Instant;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        if(args.length < 1) {
            System.err.println("Missing required path argument.");
            System.exit(2);
        }
        File file = new File(args[0]);
        if(!file.exists()) {
            System.err.println("Invalid file: File does not exist.");
            System.exit(3);
        }
        File outputFolder;
        if(args.length > 1) {
            outputFolder = new File(args[1]);
        } else {
            outputFolder = file.getParentFile();
        }
        XliffObject out = Parser.readXLIFFYFile(file);
        List<Xliff> files = Export.prepare(out);
        Export.write(outputFolder, file.getName(), files);
    }
}
