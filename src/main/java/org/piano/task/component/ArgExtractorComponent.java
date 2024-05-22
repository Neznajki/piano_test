package org.piano.task.component;

import org.piano.task.component.args.ArgumentInterface;
import org.piano.task.component.args.InputFilePath;
import org.piano.task.component.args.OutputFile;
import org.piano.task.component.args.SemitonePosition;
import org.piano.task.validator.InputFileValidator;
import org.piano.task.validator.OutputFileValidator;
import org.piano.task.validator.SemitoneValidator;
import org.piano.task.validator.ValidatorInterface;

import java.io.File;
import java.util.List;
import java.util.Map;

public class ArgExtractorComponent {

    public static final String INPUT_FILE = "inputFile";
    public static final String SEMITONE = "semitone";
    public static final String OUTPUT_FILE = "OutputFile";

    public static ArgExtractorComponent getInstance() {
        return new ArgExtractorComponent(Map.of(
                INPUT_FILE, new InputFilePath(),
                SEMITONE, new SemitonePosition(),
                OUTPUT_FILE, new OutputFile()
        ), List.of(new InputFileValidator(), new SemitoneValidator(), new OutputFileValidator()));
    }

    Map<String, ArgumentInterface> arguments;
    List<ValidatorInterface> validators;

    public ArgExtractorComponent(Map<String, ArgumentInterface> arguments, List<ValidatorInterface> validators) {
        this.arguments = arguments;
        this.validators = validators;
    }

    public void extractArgs(String[] args) {
        if (args.length < 3) {
            throw new RuntimeException("requires 3 args “inputFile”, “semitone”, “outputFile”");
        }

        arguments.forEach((name, arg) -> arg.setValue(args[arg.getPosition()]));
        validate();
    }

    public void validate() {
        validators.forEach((validator) -> arguments.forEach((name, arg) -> {
            if (validator.supports(arg)) {
                validator.validate(arg);
            }
        }));
    }

    public File getInputFile() {
        return new File(arguments.get(INPUT_FILE).getValue());
    }

    public Integer getSemitone() {
        return Integer.valueOf(arguments.get(SEMITONE).getValue());
    }

    public File getOutputFile() {
        return new File(arguments.get(OUTPUT_FILE).getValue());
    }
}
