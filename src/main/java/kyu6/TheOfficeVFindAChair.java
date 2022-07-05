package kyu6;

import java.util.ArrayList;

public class TheOfficeVFindAChair {

    public static class Room {
        String occupants;
        int chairs;

        public Room(String occupants, int chairs) {
            this.occupants = occupants;
            this.chairs = chairs;
        }
    }

    public static Object meeting(Room[] x, int need) {

        if (need == 0) {
            return "Game On";
        }

        ArrayList<Integer> chairs = new ArrayList<>();
        int remaining = need;

        for (Room room : x) {
            if (remaining == 0) {
                break;
            }
            if (room.chairs > room.occupants.length()) {
                int diff = room.chairs - room.occupants.length();
                if (diff > remaining) {
                    chairs.add(remaining);
                    remaining = 0;
                } else {
                    remaining -= diff;
                    chairs.add(diff);
                }
            } else {
                chairs.add(0);
            }
        }

        if (remaining != 0) {
            return "Not enough!";
        }


        return chairs.stream().mapToInt(z -> z).toArray();
    }
}
