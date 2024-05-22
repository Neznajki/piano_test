package org.piano.task.service;

import org.piano.task.component.ArgExtractorComponent;
import org.piano.task.component.JsonFileComponent;
import org.piano.task.object.PianoKeys;
import org.piano.task.validator.KeyValidator;

import java.io.IOException;

public class PianoService {
    ArgExtractorComponent argExtractor;
    JsonFileComponent fileComponent;
    KeyValidator keyValidator;

    public PianoService(ArgExtractorComponent argExtractor, JsonFileComponent fileComponent, KeyValidator keyValidator) {
        this.argExtractor = argExtractor;
        this.fileComponent = fileComponent;
        this.keyValidator = keyValidator;
    }

    public void execute(String[] args) throws IOException {
        argExtractor.extractArgs(args);
        PianoKeys pianoKeys = new PianoKeys(keyValidator, fileComponent.read(argExtractor.getInputFile()));
        pianoKeys.validate().calculate(argExtractor.getSemitone());
        fileComponent.write(argExtractor.getOutputFile(), pianoKeys);
    }
}
