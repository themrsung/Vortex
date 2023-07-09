package oasis.vortex;

import oasis.vortex.state.State;
import oasis.vortex.state.VortexState;

/**
 * <h2>Vortex</h2>
 * <p>The main class of Vortex.</p>
 */
public final class Vortex {
    private static final State state = new VortexState();

    public static State getState() {
        return state;
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

}