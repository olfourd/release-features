package com.my.state;

import com.my.state.states.NewState;
import com.my.state.states.State;

public class StateService {

    private State state;

    {
        state = new NewState();
    }

    public void setState(State state) {
        this.state = state;
    }

    public State toNextState() {
        return this.state.toNextState(this);
    }

    public State toPrevState() {
        return this.state.toPreviousState(this);
    }

    public void dependOnStageResult() {
        this.state.delegatingMethod();
    }
}
