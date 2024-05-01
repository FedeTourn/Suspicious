package frsf.cidisi.faia.examples.matlab.simple_water_tank.actions;

import frsf.cidisi.faia.agent.reactive.ReactiveAction;
import frsf.cidisi.faia.examples.matlab.simple_water_tank.SimpleWaterTankEnvironmentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class ChangeVolumeFlow extends ReactiveAction {

    private double volumeFlow;

    public ChangeVolumeFlow(double volumeFlow) {
        this.volumeFlow = volumeFlow;
    }

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {

        SimpleWaterTankEnvironmentState environmentState =
                (SimpleWaterTankEnvironmentState) est;

        environmentState.setVolumeFlow(this.volumeFlow);

        return environmentState;
    }

    @Override
    public String toString() {
        return "Change volume flow to " + this.volumeFlow;
    }
}
