package org.piano.task.validator;

import java.io.File;

public class InputFileValidator implements ValidatorInterface<InputFileValidatorContextInterface> {
    public void validate(InputFileValidatorContextInterface context) {
        String value = context.getValue();

        if (value == null) {
            throw new RuntimeException("Input file Value is missing");
        }

        File file = new File(value);
        if (!file.exists()) {
            throw new RuntimeException("Input file does not exist");
        }

        if (!file.isFile()) {
            throw new RuntimeException("Input file is not a file");
        }
        if (!file.canRead()) {
            throw new RuntimeException("Input file is not readable");
        }
    }

    @Override
    public boolean supports(ValidatorContextInterface context) {
        return context instanceof InputFileValidatorContextInterface;
    }
}
