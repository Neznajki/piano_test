package com.example.transpose;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java -jar task.jar <inputFile> <semitone> <outputFile>");
            System.exit(1);
        }

        String inputFile = args[0];
        int semitone;
        String outputFile = args[2];

        try {
            semitone = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("Error: semitone must be an integer.");
            System.exit(1);
            return;
        }

        try {
            String inputJson = Files.readString(Paths.get(inputFile));
            Gson gson = new Gson();
            Type type = new TypeToken<List<List<Integer>>>(){}.getType();
            List<List<Integer>> notes = gson.fromJson(inputJson, type);

            List<List<Integer>> transposed = NoteTransposer.transposeNotes(notes, semitone);

            String outputJson = gson.toJson(transposed);
            Files.createDirectories(Paths.get(outputFile).getParent());
            Files.writeString(Paths.get(outputFile), outputJson);
            System.out.println("Successfully transposed notes to " + outputFile);

        } catch (NoteTransposer.OutOfRangeException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O Error: " + e.getMessage());
        }
    }
}
