package module.model.robots;

import lombok.SneakyThrows;
import module.model.entity.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Programmer implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(Programmer.class);
    private final Item item;
    private final CountDownLatch latch;
    private boolean completed = false;
    private int brokenMicrocircuits;

    public Programmer(Item item, CountDownLatch latch) {
        this.item = item;
        this.latch = latch;
    }

    @Override
    public void run() {
        while (!completed) {
            doProgramming();
            if (isProgrammingSuccessful()) {
                completed = true;
                LOG.info("Programming completed successful.");
            } else {
                brokenMicrocircuits++;
                item.setSecondStagePercentages(0);
                LOG.info("Programming failed. Starting again.");
            }
        }
        item.setBrokenMicrocircuits(brokenMicrocircuits);
        latch.countDown();
    }

    private boolean isProgrammingSuccessful() {
        int possibilityToBrakeMicrocircuit = 30;
        return new Random().nextInt(0, 100) > possibilityToBrakeMicrocircuit;
    }

    private void doProgramming() {
        while (item.getSecondStagePercentages() < 100) {
            programMicrocircuit();
            reloadProgrammer();
        }
    }

    private void programMicrocircuit() {
        int secondStagePercentages = item.getSecondStagePercentages();
        int amountOfWorkPerCycle = getActualAmountOfWorkPerCycle();
        secondStagePercentages += amountOfWorkPerCycle;
        item.setSecondStagePercentages(secondStagePercentages);
        LOG.info("Programmer made up to {} percents of second stage work.", secondStagePercentages);
    }

    private int getActualAmountOfWorkPerCycle() {
        int minAmountOfWorkPerCycle = 25;
        int maxAmountOfWorkPerCycle = 35;
        return new Random().nextInt(minAmountOfWorkPerCycle, maxAmountOfWorkPerCycle);
    }

    @SneakyThrows
    private void reloadProgrammer() {
        int reloadTime = 1;
        TimeUnit.SECONDS.sleep(reloadTime);
    }

}
