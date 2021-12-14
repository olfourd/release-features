package com.my.state;

public class Client {

    public static void main(String[] args) {
        StateService stateService = new StateService();
        stateService.dependOnStageResult();

        stateService.toNextState();
        stateService.dependOnStageResult();

        stateService.toNextState();
        stateService.dependOnStageResult();

        stateService.toPrevState();
        stateService.dependOnStageResult();
    }
}
