package module.model.robots;

import lombok.SneakyThrows;
import module.model.entity.Fuel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class FuelSupplier implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(FuelSupplier.class);
    private boolean running = true;
    private final Fuel fuel;

    public FuelSupplier(Fuel fuel) {
        this.fuel = fuel;
    }

    @Override
    public void run() {
        while (running) {
            makeFuel();
            transportFuel();
        }
    }

    private synchronized void makeFuel() {
        int fuelAmount = fuel.getProducedAmount();
        int actualFuelAmount = getActualFuelAmount();
        fuelAmount += actualFuelAmount;
        fuel.setProducedAmount(fuelAmount);
        LOG.info("FuelSupplier produced {} {} of fuel.", actualFuelAmount, fuel.getMeasureUnits());
    }

    private int getActualFuelAmount() {
        int maxFuelProductionAmount = 1000;
        int minFuelProductionAmount = 500;
        return new Random().nextInt(minFuelProductionAmount, maxFuelProductionAmount);
    }

    @SneakyThrows
    private void transportFuel() {
        int transportationTime = 3;
        TimeUnit.SECONDS.sleep(transportationTime);
    }

    public void terminate() {
        running = false;
    }
}
