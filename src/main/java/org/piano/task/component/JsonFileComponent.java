package org.piano.task.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.piano.task.object.PianoKeys;

import java.io.File;
import java.io.IOException;

public class JsonFileComponent {
    ObjectMapper mapper;

    public JsonFileComponent(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public Integer[][] read(File file) throws IOException {
        return mapper.readValue(file, Integer[][].class);
    }

    public void write(File file, PianoKeys pianoKeys) throws IOException {
        mapper.writeValue(file, pianoKeys.prepareForSave());
    }
}
