package com.my.state.states;

import com.my.state.StateService;

public class ProcessState implements State {

    @Override
    public State toNextState(StateService stateService) {
        FinishedState finishedState = new FinishedState();
        stateService.setState(finishedState);
        return finishedState;
    }

    @Override
    public State toPreviousState(StateService stateService) {
        NewState newState = new NewState();
        stateService.setState(newState);
        return newState;
    }

    @Override
    public void delegatingMethod() {
        System.out.println("Sorry, I'm busy. My state is processed");
    }
}
