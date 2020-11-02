package com.marck_devs.GitServer.CommandExec;

import java.util.HashMap;
import java.util.Set;

public final class CommandDB {
    private HashMap<String, Command> commands = new HashMap<String, Command>();

//    ---------------------------------| SINGLETON |------------------------------
    private static CommandDB instace;

    public static CommandDB instace(){
        if(instace == null)
            instace = new CommandDB();
        return instace;
    }
//    ---------------------------------| /SINGLETON |------------------------------


    private CommandDB(){}

    public  void put(String key, Command value){
        commands.put(key, value);
    }

    public  Command getCommand(String key){
        return commands.get(key);
    }

    public  HashMap<String, Command> getAsMap(){
        return commands;
    }

    public  Set<String> getKeys(){
        return commands.keySet();
    }

    public  boolean existsKey(String key){
        return commands.containsKey(key);
    }
}
