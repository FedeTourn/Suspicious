package frsf.cidisi.faia.examples.matlab.simple_water_tank;

import java.util.Hashtable;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.MatlabEnvironment;

public class SimpleWaterTankEnvironment extends MatlabEnvironment {

    private static final double TANK_AREA = 40.0;
    private static final double OUTLET_FLOW_RESTRICTION = 0.5;

    public SimpleWaterTankEnvironment() {
        super();

        this.environmentState = new SimpleWaterTankEnvironmentState();
    }

    @Override
    public SimpleWaterTankEnvironmentState getEnvironmentState() {
        return (SimpleWaterTankEnvironmentState) super.getEnvironmentState();
    }

    @Override
    public Perception getPercept() {
        WaterTankPerception perception = new WaterTankPerception();

        Hashtable<String, double[][]> simulationReturn;

        // Start simulation
        simulationReturn = this.startSimulation();

        double[][] h = simulationReturn.get("h");

        SimpleWaterTankEnvironmentState tankEnvironmentState =
                this.getEnvironmentState();

        tankEnvironmentState.setTankHeight(h[h.length - 1][0]);
        // Change start and end time for the next simulation
        tankEnvironmentState.nextTime();

        perception.setTankHeight(this.getEnvironmentState().getTankHeight());
        perception.setTime(this.getEnvironmentState().getStartTime());
        perception.setVolumeFlow(this.getEnvironmentState().getVolumeFlow());

        return perception;
    }

    public double getTankHeight() {
        return this.getEnvironmentState().getTankHeight();
    }

    @Override
    public String getMatlabProjectPath() {
        return "matlab_modelo_tanques";
    }

    @Override
    public String toString() {
        return this.getEnvironmentState().toString();
    }

    @Override
    public Object[] getMatlabFunctionParameters() {
        SimpleWaterTankEnvironmentState tankEnvironmentState =
                this.getEnvironmentState();

        return new Object[]{
                    TANK_AREA,
                    OUTLET_FLOW_RESTRICTION,
                    tankEnvironmentState.getTankHeight(),
                    tankEnvironmentState.getVolumeFlow(),
                    tankEnvironmentState.getStartTime(),
                    tankEnvironmentState.getEndTime()
                };
    }

    @Override
    protected String getMatlabFunctionName() {
        return "TK_1_L";
    }

    @Override
    protected Object[] getMatlabFunctionReturnVariables() {
        return new Object[]{
                    "t",
                    "h"
                };
    }
}
