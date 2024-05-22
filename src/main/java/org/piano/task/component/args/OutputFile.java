package org.piano.task.component.args;

import lombok.Getter;
import lombok.Setter;
import org.piano.task.validator.OutputFileValidatorContextInterface;

@Setter
@Getter
public class OutputFile implements ArgumentInterface, OutputFileValidatorContextInterface {
    String value;
    @Override
    public Integer getPosition() {
        return 2;
    }

}
