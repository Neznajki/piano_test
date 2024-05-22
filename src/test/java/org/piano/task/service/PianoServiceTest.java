package org.piano.task.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.piano.task.component.ArgExtractorComponent;
import org.piano.task.component.JsonFileComponent;
import org.piano.task.component.args.InputFilePath;
import org.piano.task.component.args.OutputFile;
import org.piano.task.component.args.SemitonePosition;
import org.piano.task.validator.InputFileValidator;
import org.piano.task.validator.KeyValidator;
import org.piano.task.validator.OutputFileValidator;
import org.piano.task.validator.SemitoneValidator;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.piano.task.component.ArgExtractorComponent.*;

public class PianoServiceTest {
    @ParameterizedTest
    @CsvSource(value = {
            "[[5,1]]; [[-3, 10]]; -87",
            "[[-3, 10]]; [[5,1]]; 87",
            "[[2,1],[2,6],[2,1],[2,8],[2,1],[2,9],[2,1],[2,6],[2,1],[2,8],[2,1],[2,9],[2,1],[2,11],[2,1],[2,8],[2,1],[2,9],[2,1],[2,11],[2,1],[3,1],[2,1],[2,9],[2,1],[2,11],[2,1],[3,1],[2,1],[3,2],[2,1],[2,11],[2,1],[3,1],[2,1],[2,9],[2,1],[2,11],[2,1],[2,8],[2,1],[2,9],[2,1],[2,6],[2,1],[2,8],[2,1],[2,5],[2,1],[2,6],[2,1],[2,1],[2,1],[2,2],[2,1],[1,11],[2,1],[2,1],[2,1],[1,9],[2,1],[1,11],[2,1],[1,8],[2,1],[1,9],[2,1],[1,6],[2,1],[1,11],[2,1],[1,8],[2,1],[1,9],[2,1],[1,6],[2,1],[1, 8],[2,1],[1,5],[2,1],[1,6]];" +
            "[[1,10],[2,3],[1,10],[2,5],[1,10],[2,6],[1,10],[2,3],[1,10],[2,5],[1,10],[2,6],[1,10],[2,8],[1,10],[2,5],[1,10],[2,6],[1,10],[2,8],[1,10],[2,10],[1,10],[2,6],[1,10],[2,8],[1,10],[2,10],[1,10],[2,11],[1,10],[2,8],[1,10],[2,10],[1,10],[2,6],[1,10],[2,8],[1,10],[2,5],[1,10],[2,6],[1,10],[2,3],[1,10],[2,5],[1,10],[2,2],[1,10],[2,3],[1,10],[1,10],[1,10],[1,11],[1,10],[1,8],[1,10],[1,10],[1,10],[1,6],[1,10],[1,8],[1,10],[1,5],[1,10],[1,6],[1,10],[1,3],[1,10],[1,8],[1,10],[1,5],[1,10],[1,6],[1,10],[1,3],[1,10],[1,5],[1,10],[1,2],[1,10],[1,3]];" +
            "-3",
    }, delimiter = ';')
    public void testService(String inputContent, String outputContent, Integer semitones) throws IOException {
        String testInputFile = "testInput.json";
        String testOutputFile = "testOutput.json";
        ObjectMapper mapperMock = Mockito.mock(ObjectMapper.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonFileComponent fileComponent = new JsonFileComponent(mapperMock);
        when(mapperMock.readValue(eq(new File(testInputFile)), eq(Integer[][].class)))
                .thenReturn(mapper.readValue(inputContent, Integer[][].class));

        PianoService pianoService = new PianoService(
                new ArgExtractorComponent(
                    Map.of(
                        INPUT_FILE, new InputFilePath(),
                        SEMITONE, new SemitonePosition(),
                        OUTPUT_FILE, new OutputFile()
                    ),
                    List.of(
                        Mockito.mock(InputFileValidator.class),
                        Mockito.mock(SemitoneValidator.class),
                        Mockito.mock(OutputFileValidator.class)
                    )
                ),
                fileComponent,
                new KeyValidator()
        );

        pianoService.execute(new String[]{testInputFile, String.valueOf(semitones), testOutputFile});

        verify(mapperMock)
                .writeValue(eq(new File(testOutputFile)), eq(mapper.readValue(outputContent, Integer[][].class)));
    }
}
