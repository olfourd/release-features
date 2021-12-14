package com.my.state.states;

import com.my.state.StateService;

public interface State {

    State toNextState(StateService stateService);

    State toPreviousState(StateService stateService);

    void delegatingMethod();
}
