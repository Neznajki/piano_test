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

            int totalSemitone = octave * NOTES_PER_OCTAVE + noteNum;
            int newTotal = totalSemitone + semitone;

            int newOctave = Math.floorDiv(newTotal, NOTES_PER_OCTAVE);
            int newNoteNum = newTotal % NOTES_PER_OCTAVE;

            if (newNoteNum <= 0) {
                newNoteNum += NOTES_PER_OCTAVE;
                newOctave -= 1;
            }

            if (!isWithinRange(newOctave, newNoteNum)) {
                throw new OutOfRangeException(
                        "Note [" + newOctave + "," + newNoteNum + "] is out of keyboard range.");
            }

            result.add(List.of(newOctave, newNoteNum));
        }

        return result;
    }

    private static boolean isWithinRange(int octave, int note) {
        if (octave < MIN_OCTAVE || octave > MAX_OCTAVE) return false;
        if (octave == MIN_OCTAVE && note < 10) return false; // starts from [-3,10]
        if (octave == MAX_OCTAVE && note > 1) return false;  // ends at [5,1]
        return true;
    }

    public static class OutOfRangeException extends Exception {
        public OutOfRangeException(String message) {
            super(message);
        }
    }
}
