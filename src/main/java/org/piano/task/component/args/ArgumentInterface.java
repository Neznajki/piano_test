package org.piano.task.component.args;

import org.piano.task.validator.ValidatorContextInterface;

public interface ArgumentInterface extends ValidatorContextInterface {
    Integer getPosition();
    void setValue(String args);
}
