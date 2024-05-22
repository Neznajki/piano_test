package org.piano.task.component.args;

import lombok.Getter;
import lombok.Setter;
import org.piano.task.validator.InputFileValidatorContextInterface;


@Setter
@Getter
public class InputFilePath implements ArgumentInterface, InputFileValidatorContextInterface {
    String value;
    @Override
    public Integer getPosition() {
        return 0;
    }
}
