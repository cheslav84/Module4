package module.model.robots;

import lombok.SneakyThrows;
import module.model.entity.Fuel;
import module.model.entity.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class FinalConstructor implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(FinalConstructor.class);
    private final Fuel fuel;
    private final Item item;
    private final CountDownLatch latch;

    public FinalConstructor(Fuel fuel, Item item, CountDownLatch latch) {
        item.setFuel(fuel);
        this.fuel = fuel;
        this.item = item;
        this.latch = latch;
    }

    @Override
    public void run() {
        while (item.getThirdStagePercentages() < 100) {
            makeFinalConstruction();
            reloadConstructor();
        }
        setProductionTime();
        latch.countDown();
    }

    private void makeFinalConstruction() {
        int amountOfFuelPerCycle = getActualAmountOfFuelPerCycle();
        waitIfNoFuel(amountOfFuelPerCycle);
        makeDetail(amountOfFuelPerCycle);
        LOG.info("FinalConstructor made up to {} percents of third stage work, and consumed {} {} of fuel.",
                item.getThirdStagePercentages(), amountOfFuelPerCycle, fuel.getMeasureUnits());
    }

    private int getActualAmountOfFuelPerCycle() {
        int minAmountOfFuelPerCycle = 350;
        int maxAmountOfFuelPerCycle = 700;
        return new Random().nextInt(minAmountOfFuelPerCycle, maxAmountOfFuelPerCycle);
    }

    private void waitIfNoFuel(int actualAmountOfFuelPerCycle) {
        while (fuel.getRemainder() < actualAmountOfFuelPerCycle) {
            Thread.yield();
        }
    }

    private void makeDetail(int amountOfFuelPerCycle) {
        int amountOfWorkPerCycle = 10;
        item.setThirdStagePercentages(item.getThirdStagePercentages() + amountOfWorkPerCycle);
        consumeFuel(amountOfFuelPerCycle);
    }

    private void consumeFuel(int amountOfFuelPerCycle) {
        int totalConsumedFuel = fuel.getSpentAmount() + amountOfFuelPerCycle;
        fuel.setSpentAmount(totalConsumedFuel);
    }

    @SneakyThrows
    private void reloadConstructor() {
        int reloadTime = 1;
        TimeUnit.SECONDS.sleep(reloadTime);
    }

    private void setProductionTime() {
        Date productionDate = item.getProductionDate();
        long startTime = productionDate.getTime();
        long endTime = System.currentTimeMillis();
        int productionTime = (int)((endTime - startTime) / 1000);
        item.setProductionDuration(productionTime);
    }

}
