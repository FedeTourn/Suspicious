package frsf.cidisi.faia.examples.matlab.simple_water_tank;

import frsf.cidisi.faia.state.MatlabEnvironmentState;

public class SimpleWaterTankEnvironmentState extends MatlabEnvironmentState {

    private double tankHeight;
    private double volumeFlow;

    public SimpleWaterTankEnvironmentState() {
        // Start time = 0
        // Step = 3
        super(0, 3);

        this.initState();
    }

    @Override
    public void initState() {
        this.tankHeight = 5.0;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("\nTime: " + this.getStartTime() + "\n");
        sb.append("Tank height: " + this.tankHeight + "\n");
        sb.append("Volume flow: " + this.volumeFlow + "\n");

        return sb.toString();
    }

    public double getTankHeight() {
        return tankHeight;
    }

    public void setTankHeight(double altura) {
        this.tankHeight = altura;
    }

    public double getVolumeFlow() {
        return volumeFlow;
    }

    public void setVolumeFlow(double volumeFlow) {
        this.volumeFlow = volumeFlow;
    }
}
