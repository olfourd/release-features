package com.my.state.states;

import com.my.state.StateService;

public class NewState implements State {

    @Override
    public State toNextState(StateService stateService) {
        ProcessState processState = new ProcessState();
        stateService.setState(processState);
        return processState;
    }

    @Override
    public State toPreviousState(StateService stateService) {
        stateService.setState(this);
        return this;
    }

    @Override
    public void delegatingMethod() {
        System.out.println("It's fresh state. What would you like to do?");
    }
}
