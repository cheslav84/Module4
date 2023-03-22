package module.model.robots;

import lombok.SneakyThrows;
import module.model.entity.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class BaseConstructor implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(BaseConstructor.class);
    private final Item item;
    private final CountDownLatch latch;

    public BaseConstructor(Item item, CountDownLatch latch) {
        this.item = item;
        this.latch = latch;
    }

    @Override
    public void run() {
        setConstructionDate();
        while (!isWorkDone()) {
            makeBaseConstruction();
            reloadConstructor();
        }
        latch.countDown();
    }

    private void setConstructionDate() {
        synchronized (item) {
            if (item.getProductionDate() == null) {
                item.setProductionDate(new Date());
            }
        }
    }

    private boolean isWorkDone() {
        boolean isDone;
        synchronized (item) {
            isDone = item.getFirstStagePercentages() > 100;
        }
        return isDone;
    }

    private void makeBaseConstruction() {
        synchronized (item) {
            int firstStagePercentages = item.getFirstStagePercentages();
            int amountOfWorkPerCycle = getActualAmountOfWorkPerCycle();
            firstStagePercentages += amountOfWorkPerCycle;
            item.setFirstStagePercentages(firstStagePercentages);
            LOG.info("BaseConstructor {} made up to {} percents of first stage work.",
                    Thread.currentThread().getName(), firstStagePercentages);
        }
    }

    private int getActualAmountOfWorkPerCycle() {
        int minAmountOfWorkPerCycle = 10;
        int maxAmountOfWorkPerCycle = 20;
        return new Random().nextInt(minAmountOfWorkPerCycle, maxAmountOfWorkPerCycle);
    }

    @SneakyThrows
    private void reloadConstructor() {
        int reloadTime = 2;
        TimeUnit.SECONDS.sleep(reloadTime);
    }

}
