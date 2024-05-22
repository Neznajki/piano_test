package org.piano.task.validator;

import org.piano.task.object.Key;

public class KeyValidator {
    public void validate(Key key, String messageAdd) {
        if (key.getOctaveNumber() < -3 || key.getOctaveNumber() > 5) {
            throw new RuntimeException("Octave number must be between -3 and 5 " + messageAdd);
        }

        if (key.getNoteNumber() <= 0) {
            throw new RuntimeException("Note number must be greater than 0 " + messageAdd);
        }

        if (key.getNoteNumber() > 12) {
            throw new RuntimeException("Note number must be less than 13 " + messageAdd);
        }

        if (key.getOctaveNumber() == -3 && key.getNoteNumber() < 10) {
            throw new RuntimeException("Note number must be 10 or more in -3 Oktave " + messageAdd);
        }

        if (key.getOctaveNumber() == 5 && key.getNoteNumber() > 1) {
            throw new RuntimeException("Note number must be 1 or less in 5 Oktave " + messageAdd);
        }
    }
}
