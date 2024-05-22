package org.piano.task.object;

import lombok.Getter;

@Getter
public class Key {
    Integer octaveNumber;
    Integer noteNumber;

    public Key(Integer octaveNumber, Integer noteNumber) {
        this.octaveNumber = octaveNumber;
        this.noteNumber = noteNumber;
    }

    public void calculate(Integer semitones) {
        Integer semitonesLeft = semitones;
        while (semitonesLeft > 0) {
            semitonesLeft--;

            if (noteNumber == 12) {
                octaveNumber++;
//                noteNumber = octaveNumber < 0 ? 12 : 1;
                noteNumber = 1;
            } else {
                noteNumber++;
            }
        }

        while (semitonesLeft < 0) {
            semitonesLeft++;

            if (noteNumber == 1) {
                octaveNumber--;
                noteNumber = 12;
            } else {
                noteNumber--;
            }
        }
    }

    public Integer[] toArray() {
        return new Integer[]{octaveNumber, noteNumber};
    }
}
