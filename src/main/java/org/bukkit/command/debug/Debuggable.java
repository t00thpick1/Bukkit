package org.bukkit.command.debug;

public interface Debuggable {

    /**
     * Returns a string debug report, complete with line breaks.
     *
     * @return a debug report
     */
    public String debug();

    /**
     * Returns the name of the debuggable.
     *
     * @return name of the debuggable
     */
    public String getName();
}
