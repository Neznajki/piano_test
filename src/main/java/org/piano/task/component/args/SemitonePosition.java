package org.piano.task.component.args;

import lombok.Getter;
import lombok.Setter;
import org.piano.task.validator.SemitoneValidatorContextInterface;

@Setter
@Getter
public class SemitonePosition implements ArgumentInterface, SemitoneValidatorContextInterface {
    String value;
    @Override
    public Integer getPosition() {
        return 1;
    }
}
