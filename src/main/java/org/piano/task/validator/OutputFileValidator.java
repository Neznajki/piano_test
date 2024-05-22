package org.piano.task.validator;

import java.io.File;

public class OutputFileValidator implements ValidatorInterface<OutputFileValidatorContextInterface> {
    public void validate(OutputFileValidatorContextInterface context) {
        String value = context.getValue();

        if (value == null) {
            throw new RuntimeException("Input file Value is missing");
        }

        File file = new File(value);
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            throw new RuntimeException("Output File exists and is directory");
        }
        if (! file.canWrite()) {
            throw new RuntimeException("Output File is not writable");
        }
    }

    @Override
    public boolean supports(ValidatorContextInterface context) {
        return context instanceof OutputFileValidatorContextInterface;
    }
}
