package rip.revere.tournament.util;

public class TournamentLogger {

    private final String prefix;

    /**
     * Constructs a TournamentLogger with the specified prefix.
     *
     * @param prefix The prefix to add to log messages.
     */
    public TournamentLogger(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Logs an informational message.
     *
     * @param message The message to log.
     */
    public void info(String message) {
        System.out.println("[" + prefix + "]" + " | [INFO] " + message);
    }

    /**
     * Logs an informational message with a new line.
     *
     * @param message The message to log.
     */
    public void infoNewLine(String message) {
        System.out.println("\n[" + prefix + "]" + " | [INFO] " + message);
    }

    /**
     * Logs a warning message.
     *
     * @param message The message to log.
     */
    public void warn(String message) {
        System.out.println("[" + prefix + "]" + " | [WARN] " + message);
    }

    /**
     * Logs an error message.
     *
     * @param message The message to log.
     */
    public void error(String message) {
        System.out.println("[" + prefix + "]" + " | [ERROR] " + message);
    }

    /**
     * Logs a debug message.
     *
     * @param message The message to log.
     */
    public void debug(String message) {
        System.out.println("[" + prefix + "]" + " | [DEBUG] " + message);
    }
}
