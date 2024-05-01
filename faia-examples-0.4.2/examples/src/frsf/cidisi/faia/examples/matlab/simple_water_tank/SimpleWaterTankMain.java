package frsf.cidisi.faia.examples.matlab.simple_water_tank;

public class SimpleWaterTankMain {

    public static void main(String[] args) {
        SimpleWaterTankAgent agent = new SimpleWaterTankAgent();

        SimpleWaterTankEnvironment environment =
                new SimpleWaterTankEnvironment();

        TimeBasedSimulator simulator =
                new TimeBasedSimulator(environment, agent);

        simulator.start();
    }
}
