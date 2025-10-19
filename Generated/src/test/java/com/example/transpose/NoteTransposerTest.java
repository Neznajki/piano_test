package com.example.transpose;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Type;
import java.util.List;

public class NoteTransposerTest {

    @Test
    void testTransposeUp() throws Exception {
        List<List<Integer>> notes = List.of(List.of(1, 1), List.of(1, 5));
        List<List<Integer>> result = NoteTransposer.transposeNotes(notes, 3);
        assertEquals(List.of(List.of(1, 4), List.of(1, 8)), result);
    }

    @Test
    void testTransposeDown() throws Exception {
        List<List<Integer>> notes = List.of(List.of(2, 5));
        List<List<Integer>> result = NoteTransposer.transposeNotes(notes, -3);
        assertEquals(List.of(List.of(2, 2)), result);
    }

    @Test
    void testTransposeOctaveBoundary() throws Exception {
        List<List<Integer>> notes = List.of(List.of(1, 11));
        List<List<Integer>> result = NoteTransposer.transposeNotes(notes, 3);
        assertEquals(List.of(List.of(2, 2)), result);
    }

    @Test
    void testTransposeOutOfRangeLow() {
        List<List<Integer>> notes = List.of(List.of(-3, 10)); // first note
        Exception e = assertThrows(NoteTransposer.OutOfRangeException.class,
                () -> NoteTransposer.transposeNotes(notes, -2));
        assertTrue(e.getMessage().contains("out of keyboard range"));
    }

    @Test
    void testTransposeOutOfRangeHigh() {
        List<List<Integer>> notes = List.of(List.of(5, 1)); // last note
        Exception e = assertThrows(NoteTransposer.OutOfRangeException.class,
                () -> NoteTransposer.transposeNotes(notes, 5));
        assertTrue(e.getMessage().contains("out of keyboard range"));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "[[5,1]]; [[-3, 10]]; -87",
            "[[-3, 10]]; [[5,1]]; 87",
            "[[2,1],[2,6],[2,1],[2,8],[2,1],[2,9],[2,1],[2,6],[2,1],[2,8],[2,1],[2,9],[2,1],[2,11],[2,1],[2,8],[2,1],[2,9],[2,1],[2,11],[2,1],[3,1],[2,1],[2,9],[2,1],[2,11],[2,1],[3,1],[2,1],[3,2],[2,1],[2,11],[2,1],[3,1],[2,1],[2,9],[2,1],[2,11],[2,1],[2,8],[2,1],[2,9],[2,1],[2,6],[2,1],[2,8],[2,1],[2,5],[2,1],[2,6],[2,1],[2,1],[2,1],[2,2],[2,1],[1,11],[2,1],[2,1],[2,1],[1,9],[2,1],[1,11],[2,1],[1,8],[2,1],[1,9],[2,1],[1,6],[2,1],[1,11],[2,1],[1,8],[2,1],[1,9],[2,1],[1,6],[2,1],[1, 8],[2,1],[1,5],[2,1],[1,6]];" +
                    "[[1,10],[2,3],[1,10],[2,5],[1,10],[2,6],[1,10],[2,3],[1,10],[2,5],[1,10],[2,6],[1,10],[2,8],[1,10],[2,5],[1,10],[2,6],[1,10],[2,8],[1,10],[2,10],[1,10],[2,6],[1,10],[2,8],[1,10],[2,10],[1,10],[2,11],[1,10],[2,8],[1,10],[2,10],[1,10],[2,6],[1,10],[2,8],[1,10],[2,5],[1,10],[2,6],[1,10],[2,3],[1,10],[2,5],[1,10],[2,2],[1,10],[2,3],[1,10],[1,10],[1,10],[1,11],[1,10],[1,8],[1,10],[1,10],[1,10],[1,6],[1,10],[1,8],[1,10],[1,5],[1,10],[1,6],[1,10],[1,3],[1,10],[1,8],[1,10],[1,5],[1,10],[1,6],[1,10],[1,3],[1,10],[1,5],[1,10],[1,2],[1,10],[1,3]];" +
                    "-3",
    }, delimiter = ';')
    public void testService(String inputContent, String outputContent, Integer semitones) throws NoteTransposer.OutOfRangeException {
        Gson gson = new Gson();
        Type type = new TypeToken<List<List<Integer>>>(){}.getType();
        List<List<Integer>> notes = gson.fromJson(inputContent, type);
        List<List<Integer>> expectedOutcome = gson.fromJson(outputContent, type);
        List<List<Integer>> result = NoteTransposer.transposeNotes(notes, semitones);
        assertEquals(expectedOutcome, result);
    }
}