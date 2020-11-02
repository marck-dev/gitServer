package com.marck_devs.GitServer.CommandExec;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Set;

public final class CommandDB {
    private HashMap<String, Command> commands = new HashMap<>();

//    ---------------------------------| SINGLETON |------------------------------
    private static CommandDB instace;

    public static CommandDB instace(){
        if(instace == null)
            instace = new CommandDB();
        return instace;
    }
//    ---------------------------------| /SINGLETON |------------------------------


    private CommandDB(){}

    public  void put(@NotNull String key,@NotNull Command value){
        commands.put(key, value);
    }

    public  Command getCommand(@NotNull String key){
        return commands.get(key);
    }

    public  HashMap<String, Command> getAsMap(){
        return commands;
    }

    @Contract(pure = true)
    public Set<String> getKeys(){
        return commands.keySet();
    }

    public  boolean existsKey(@NotNull String key){
        return commands.containsKey(key);
    }
}
