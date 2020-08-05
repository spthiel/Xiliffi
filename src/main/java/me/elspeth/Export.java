package me.elspeth;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Export {

    public static List<Xliff> prepare(XliffObject object) {

        List<Xliff> out = new LinkedList<>();

        out.add(new Xliff(object.getSource(), object));
        out.addAll(object.getTargets().stream().filter(target -> !target.equals("note")).map(target -> new Xliff(object.getSource(), target, object)).collect(Collectors.toSet()));

        return out;
    }

    public static void write(File folder, String filename, List<Xliff> files) {

        filename = filename.replaceAll("\\..+?$","");
        if(filename.equals("db")) {
            filename = "locallang_db";
        } else if(!filename.equals("locallang")) {
            filename = "locallang_csh_" + filename;
        }

        filename = filename + ".xlf";

        for (Xliff file : files) {
            String content = file.asFile();
            String prefix = file.hasTarget() ? file.getTarget() + "." : "";
            String path = prefix + filename;

            File f = new File(folder, path);

            try(BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
                writer.write(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
