package org.piano.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.piano.task.component.ArgExtractorComponent;
import org.piano.task.component.JsonFileComponent;
import org.piano.task.service.PianoService;
import org.piano.task.validator.KeyValidator;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        PianoService pianoService = new PianoService(
                ArgExtractorComponent.getInstance(),
                new JsonFileComponent(new ObjectMapper()),
                new KeyValidator()
        );

        pianoService.execute(args);
    }
}