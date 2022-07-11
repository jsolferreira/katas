package kyu4;

import java.util.Map;

public class ASimplisticTCPFiniteStateMachine {

    private static final Map<String, Map<String, String>> map = Map.ofEntries(
            Map.entry("CLOSED", Map.ofEntries(Map.entry("APP_PASSIVE_OPEN", "LISTEN"), Map.entry("APP_ACTIVE_OPEN", "SYN_SENT"))),
            Map.entry("LISTEN", Map.ofEntries(Map.entry("RCV_SYN", "SYN_RCVD"), Map.entry("APP_SEND", "SYN_SENT"), Map.entry("APP_CLOSE", "CLOSED"))),
            Map.entry("SYN_RCVD", Map.ofEntries(Map.entry("APP_CLOSE", "FIN_WAIT_1"), Map.entry("RCV_ACK", "ESTABLISHED"))),
            Map.entry("SYN_SENT", Map.ofEntries(Map.entry("RCV_SYN", "SYN_RCVD"), Map.entry("RCV_SYN_ACK", "ESTABLISHED"), Map.entry("APP_CLOSE", "CLOSED"))),
            Map.entry("ESTABLISHED", Map.ofEntries(Map.entry("APP_CLOSE", "FIN_WAIT_1"), Map.entry("RCV_FIN", "CLOSE_WAIT"))),
            Map.entry("FIN_WAIT_1", Map.ofEntries(Map.entry("RCV_FIN", "CLOSING"), Map.entry("RCV_FIN_ACK", "TIME_WAIT"), Map.entry("RCV_ACK", "FIN_WAIT_2"))),
            Map.entry("CLOSING", Map.ofEntries(Map.entry("RCV_ACK", "TIME_WAIT"))),
            Map.entry("FIN_WAIT_2", Map.ofEntries(Map.entry("RCV_FIN", "TIME_WAIT"))),
            Map.entry("TIME_WAIT", Map.ofEntries(Map.entry("APP_TIMEOUT", "CLOSED"))),
            Map.entry("CLOSE_WAIT", Map.ofEntries(Map.entry("APP_CLOSE", "LAST_ACK"))),
            Map.entry("LAST_ACK", Map.ofEntries(Map.entry("RCV_ACK", "CLOSED")))
    );

    public static String traverseStates(String[] events) {
        String state = "CLOSED";                          // initial state, always

        for (String event : events) {
            state = map.get(state).get(event);

            if (state == null) return "ERROR";
        }

        return state;
    }
}

