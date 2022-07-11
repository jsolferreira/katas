package kyu4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ASimplisticTCPFiniteStateMachineTest {

    @Test
    public void SampleTests() {
        assertEquals("CLOSE_WAIT", ASimplisticTCPFiniteStateMachine.traverseStates(new String[]{"APP_ACTIVE_OPEN", "RCV_SYN_ACK", "RCV_FIN"}));
        assertEquals("ESTABLISHED", ASimplisticTCPFiniteStateMachine.traverseStates(new String[]{"APP_PASSIVE_OPEN", "RCV_SYN", "RCV_ACK"}));
        assertEquals("LAST_ACK", ASimplisticTCPFiniteStateMachine.traverseStates(new String[]{"APP_ACTIVE_OPEN", "RCV_SYN_ACK", "RCV_FIN", "APP_CLOSE"}));
        assertEquals("SYN_SENT", ASimplisticTCPFiniteStateMachine.traverseStates(new String[]{"APP_ACTIVE_OPEN"}));
        assertEquals("ERROR", ASimplisticTCPFiniteStateMachine.traverseStates(new String[]{"APP_PASSIVE_OPEN", "RCV_SYN", "RCV_ACK", "APP_CLOSE", "APP_SEND"}));
    }
}
