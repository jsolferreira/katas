package kyu3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class TheLift {
    public static int[] theLift(final int[][] queues, final int capacity) {

        final Integer[] lift = new Integer[capacity];
        final Integer[][] queueBoxed = Arrays.stream(queues)
                .map(queue -> Arrays.stream(queue)
                        .boxed()
                        .toArray(Integer[]::new))
                .toArray(Integer[][]::new);

        int currentFloor = 0;
        int direction = 1;
        ArrayList<Integer> stops = new ArrayList<>();

        stops.add(0);

        load(queueBoxed[currentFloor], lift, currentFloor, direction);
        currentFloor++;

        while (areThereMorePersons(queueBoxed) || !isTheLiftEmpty(lift)) {
            boolean stoppedUnloading = unload(lift, currentFloor);
            direction = nextDirection(queueBoxed, lift, currentFloor, direction);
            boolean stoppedLoading = load(queueBoxed[currentFloor], lift, currentFloor, direction);

            if (stoppedUnloading || stoppedLoading) {
                stops.add(currentFloor);
            }

            currentFloor += direction;
        }

        if (stops.get(stops.size() - 1) != 0) {
            stops.add(0);
        }

        return stops.stream().mapToInt(z -> z).toArray();
    }

    private static boolean isTheLiftEmpty(final Integer[] lift) {
        return Arrays.stream(lift).allMatch(Objects::isNull);
    }

    private static boolean areThereMorePersons(final Integer[][] queues) {
        return Arrays.stream(queues).anyMatch(queue -> Arrays.stream(queue).anyMatch(Objects::nonNull));
    }

    private static int nextDirection(final Integer[][] queues, final Integer[] lift, int currentFloor, int direction) {

        if (direction == 1) {

            boolean keepGoingUp = IntStream.range(currentFloor, queues.length)
                    .anyMatch(i -> Arrays.stream(queues[i])
                            .anyMatch(destFloor -> destFloor != null && (i != currentFloor || destFloor > currentFloor)));

            if (!keepGoingUp) {
                keepGoingUp = Arrays.stream(lift).anyMatch(destFloor -> destFloor != null && destFloor > currentFloor);
            }

            return keepGoingUp ? 1 : -1;
        } else {

            boolean keepGoingDown = IntStream.rangeClosed(0, currentFloor)
                    .anyMatch(i -> Arrays.stream(queues[i])
                            .anyMatch(destFloor -> destFloor != null && (i != currentFloor || destFloor < currentFloor)));

            if (!keepGoingDown) {
                keepGoingDown = Arrays.stream(lift).anyMatch(destFloor -> destFloor != null && destFloor < currentFloor);
            }

            return keepGoingDown ? -1 : 1;
        }
    }

    private static boolean unload(final Integer[] lift, int currentFloor) {

        boolean unloaded = false;

        for (int j = 0; j < lift.length; j++) {
            if (lift[j] != null && lift[j] == currentFloor) {
                lift[j] = null;
                unloaded = true;
            }
        }

        return unloaded;
    }

    private static boolean load(final Integer[] queue, final Integer[] lift, int currentFloor, int direction) {

        boolean loaded = false;

        for (int i = 0; i < queue.length; i++) {
            if (queue[i] != null && ((direction == -1 && queue[i] < currentFloor) || (direction == 1 && queue[i] > currentFloor))) {
                loaded = true;
                for (int j = 0; j < lift.length; j++) {
                    if (lift[j] == null) {
                        lift[j] = queue[i];
                        queue[i] = null;
                        break;
                    }
                }
            }
        }

        return loaded;
    }
}
