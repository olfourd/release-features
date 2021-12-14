package com.my.state.states;

import com.my.state.StateService;

public class FinishedState implements State {

    @Override
    public State toNextState(StateService stateService) {
        return this;
    }

    @Override
    public State toPreviousState(StateService stateService) {
        ProcessState processState = new ProcessState();
        stateService.setState(processState);
        return processState;
    }

    @Override
    public void delegatingMethod() {
        System.out.println("can't do anything. my state is done");
    }
}
