package frsf.cidisi.faia.examples.matlab.simple_water_tank;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class WaterTankPerception extends Perception {

    private double tankHeight;
    private double time;
    private double volumeFlow;

    @Override
    public void initPerception(Agent agent, Environment environment) {
        // TODO Auto-generated method stub
    }

    public double getVolumeFlow() {
        return volumeFlow;
    }

    public void setVolumeFlow(double volumeFlow) {
        this.volumeFlow = volumeFlow;
    }

    public double getTankHeight() {
        return tankHeight;
    }

    public void setTankHeight(double tankHeight) {
        this.tankHeight = tankHeight;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Time: " + this.time + "; Tank height: " + this.tankHeight +
                "; Volume flow: " + this.volumeFlow;
    }
}
