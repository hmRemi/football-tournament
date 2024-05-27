package rip.revere.tournament.launch;

import rip.revere.tournament.Tournament;

public class Launch {

    /**
     * Main method to launch the tournament.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        System.setProperty("encoding", "UTF-8");
        new Tournament(args).launch();
    }
}
