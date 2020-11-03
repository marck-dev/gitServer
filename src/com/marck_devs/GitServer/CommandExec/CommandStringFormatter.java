package com.marck_devs.GitServer.CommandExec;

public class CommandStringFormatter {

    public static String Format(AbstractCommand cmd){
        return cmd.getCommand() +
                "\nCommand: " +
                cmd.getName() +
                "\n    " +
                cmd.getDesc() +
                "\nUsage: " +
                cmd.getUsage() +
                "\n" +
                cmd.getHelp();
    }
}
