package org.piano.task.object;

import org.piano.task.validator.KeyValidator;

import java.util.Arrays;
import java.util.List;

public class PianoKeys {
    final KeyValidator keyValidator;
    final List<Key> keys;

    public PianoKeys(KeyValidator keyValidator, Integer[][] keys) {
        this.keyValidator = keyValidator;
        this.keys = Arrays.stream(keys).map((values) -> new Key(values[0], values[1])).toList();
    }

    public PianoKeys validate() {
        for(int i = 0; i < keys.size(); i++) {
            keyValidator.validate(keys.get(i), "in input file, at pos " + i);
        }

        return this;
    }

    public void calculate(Integer semitones) {
        for(int i = 0; i < keys.size(); i++) {
            final Key key = keys.get(i);
            key.calculate(semitones);
            keyValidator.validate(key, "after calculations, at pos " + i);
        }
    }

    public Integer[][] prepareForSave() {
        return keys.stream().map(Key::toArray).toArray(Integer[][]::new);
    }
}
