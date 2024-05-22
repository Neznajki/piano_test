package org.piano.task.validator;

public interface ValidatorInterface<T extends ValidatorContextInterface> {
    boolean supports(ValidatorContextInterface context);
    void validate(T context);
}
