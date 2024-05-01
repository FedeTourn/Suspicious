package frsf.cidisi.faia.examples.matlab.simple_water_tank;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.reactive.ReactiveBasedAgentState;

public class SimpleWaterTankAgentState extends ReactiveBasedAgentState {

    private double tankHeight;
    private double volumeFlow;
    private double previousVolumeFlow;
    private double time;

    public SimpleWaterTankAgentState() {
        this.initState();
    }

    @Override
    public void updateState(Perception p) {
        WaterTankPerception perception =
                (WaterTankPerception) p;

        this.time = perception.getTime();
        this.tankHeight = perception.getTankHeight();
        this.previousVolumeFlow = this.volumeFlow;
        this.volumeFlow = perception.getVolumeFlow();
    }

    @Override
    public void initState() {
        this.tankHeight = 5.0;
        this.volumeFlow = 0.0;
        this.time = 0.0;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("\nTime: " + this.time + "\n");
        sb.append("Tank height: " + this.tankHeight + "\n");
        sb.append("Volume flow: " + this.volumeFlow + "\n");

        return sb.toString();
    }

    public double getTankHeight() {
        return tankHeight;
    }

    public double getVolumeFlow() {
        return volumeFlow;
    }

    public double getTime() {
        return time;
    }

    public double getPreviousVolumeFlow() {
        return previousVolumeFlow;
    }
}
