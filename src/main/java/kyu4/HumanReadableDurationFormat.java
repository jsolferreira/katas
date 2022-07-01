package kyu4;

import java.util.ArrayList;
import java.util.List;

public class HumanReadableDurationFormat {

    private static final List<Integer> mods = List.of(60, 60, 24, 365, 1);
    private static final List<String> timeUnits = List.of("second", "minute", "hour", "day", "year");

    public static String formatDuration(int seconds) {

        if (seconds == 0) {

            return "now";
        }

        final ArrayList<String> components = new ArrayList<>();

        int step = 0;

        while (seconds > 0) {

            final int mod = mods.get(step);

            int value = mod == 1 ? seconds : seconds % mod;

            if (value != 0) {

                String timeUnit = timeUnits.get(step) + (value > 1 ? "s" : "");

                components.add(0, value + " " + timeUnit);
            }

            seconds = (seconds - value) / mod;
            step++;
        }

        if (components.size() == 1) {

            return components.get(0);
        }

        final int lastIndex = components.size() - 1;

        return String.join(" and ",
                String.join(", ", components.subList(0, lastIndex)),
                components.get(lastIndex));
    }
}
