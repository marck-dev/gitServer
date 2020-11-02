package com.marck_devs.GitServer.CommandExec;

import java.util.ArrayList;
import java.util.Objects;

public abstract class CommandAbstract implements  Command{
    protected String name;
    protected String version;
    protected String desc;
    protected String command;
    protected String usage;
    protected String help;
    protected String out;
    protected static final String REGEX = "^(?<comand>\\w*)(\\s*\"?(?<repo>\\w*))?\"?(\\s*\"?(?<run>.*[^\"]))?\"?$";
    protected ArrayList<String> args = new ArrayList<>();

    protected CommandAbstract(String name, String version, String desc, String command) {
        this.name = name;
        this.version = version;
        this.desc = desc;
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getOut() {
        return out;
    }

    public String getHelp() {
        return help;
    }

    public String getUsage() {
        return usage;
    }

    protected void setHelp(String help) {
        this.help = help;
    }

    protected void setUsage(String usage) {
        this.usage = usage;
    }

    protected void setOut(String out) {
        this.out = out;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandAbstract that = (CommandAbstract) o;
        return Objects.equals(name, that.name) &&
                version.equals(that.version) &&
                command.equals(that.command);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, version, command);
    }

    @Override
    public String toString() {
        return CommandStringFormatter.Format(this);
    }
}
