package org.piano.task.validator;

public class SemitoneValidator implements ValidatorInterface<SemitoneValidatorContextInterface> {
    public void validate(SemitoneValidatorContextInterface context) {
        String value = context.getValue();

        if (value == null) {
            throw new RuntimeException("Semitone Value is missing");
        }

        int semitone = Integer.parseInt(value);

        if (semitone < -87 || semitone > 87) {
            throw new RuntimeException("Semitone Value must be between -87 and 87");
        }
    }

    @Override
    public boolean supports(ValidatorContextInterface context) {
        return context instanceof SemitoneValidatorContextInterface;
    }
}
