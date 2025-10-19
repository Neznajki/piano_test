package com.example.transpose;

import java.util.ArrayList;
import java.util.List;

public class NoteTransposer {

    private static final int NOTES_PER_OCTAVE = 12;
    private static final int MIN_OCTAVE = -3;
    private static final int MAX_OCTAVE = 5;

    public static List<List<Integer>> transposeNotes(List<List<Integer>> notes, int semitone)
            throws OutOfRangeException {

        List<List<Integer>> result = new ArrayList<>();

        for (List<Integer> note : notes) {
            int octave = note.get(0);
            int noteNum = note.get(1);

            int totalSemitone = octave * NOTES_PER_OCTAVE + (noteNum - 1);
            int newTotal = totalSemitone + semitone;

            int newOctave = Math.floorDiv(newTotal, NOTES_PER_OCTAVE);
            int withinOctave = newTotal - newOctave * NOTES_PER_OCTAVE;
            int newNoteNum = withinOctave + 1;

            if (isOutOfRange(newOctave, newNoteNum)) {
                throw new OutOfRangeException(
                        "Note [" + newOctave + "," + newNoteNum + "] is out of keyboard range.");
            }

            result.add(List.of(newOctave, newNoteNum));
        }

        return result;
    }

    private static boolean isOutOfRange(int octave, int note) {
        return
            octave < MIN_OCTAVE ||
            octave > MAX_OCTAVE ||
            (octave == MIN_OCTAVE && note < 10) ||
            (octave == MAX_OCTAVE && note > 1);
    }

    public static class OutOfRangeException extends Exception {
        public OutOfRangeException(String message) {
            super(message);
        }
    }
}
