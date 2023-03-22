package module.model.util;

import lombok.SneakyThrows;
import module.model.entity.Fuel;
import module.model.entity.Item;
import module.model.robots.BaseConstructor;
import module.model.robots.FinalConstructor;
import module.model.robots.FuelSupplier;
import module.model.robots.Programmer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ItemFactory {

    public Item makeItem() throws InterruptedException {
        String fuelMeasureUnits = "gallons";
        Fuel fuel = new Fuel(fuelMeasureUnits);
        Item item = new Item();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        FuelSupplier fuelSupplier = new FuelSupplier(fuel);

        makeFuel(fuelSupplier, executor);
        makeBaseConstruction(item, executor);
        programItem(item, executor);
        makeFinalConstruction(fuel, item, executor);

        fuelSupplier.terminate();
        executor.shutdown();
        return item;
    }

    private void makeFuel(FuelSupplier supplier, ExecutorService executor) {
        try {
            executor.execute(supplier);
        } catch (Exception e) {
            supplier.terminate();
        }

    }

    private void makeBaseConstruction(Item item, ExecutorService executor) throws InterruptedException {
        CountDownLatch latch =  new CountDownLatch(2);
        BaseConstructor firstConstructor = new BaseConstructor(item, latch);
        BaseConstructor secondConstructor = new BaseConstructor(item, latch);
        executor.execute(firstConstructor);
        executor.execute(secondConstructor);
        latch.await();
    }

    @SneakyThrows
    private void programItem(Item item, ExecutorService executor) {
        CountDownLatch latch =  new CountDownLatch(1);
        executor.execute(new Programmer(item, latch));
        latch.await();
    }

    @SneakyThrows
    private void makeFinalConstruction(Fuel fuel, Item item, ExecutorService executor) {
        CountDownLatch latch =  new CountDownLatch(1);
        executor.execute(new FinalConstructor(fuel, item, latch));
        latch.await();
    }

}
