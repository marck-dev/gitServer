package com.marck_devs.GitServer.CommandExec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CommandLog extends AbstractCommand {

    private static CommandLog instance;

    public static CommandLog instance(){
        if(instance == null)
            instance = new CommandLog();
        return instance;
    }

    protected CommandLog() {
        super("Log", "1.0.0", "Get the log from the repo", "LOG");
    }

    @Override
    public void run() throws CommandErrorException {
        String git = "git log --pretty=format:'{\"key\":\"%h\", \"author\": \"%an\", \"emai\":\"%am\", \"time\":\"%ar\", \"msg\":\"$s\"}";
        out = "[";
        ArrayList<String> tmp = new ArrayList<>();
        try {
            Process process = exec(git);
            if(process.isAlive()){
                Scanner sc = new Scanner(process.getInputStream());
                while(sc.hasNextLine()){
                    tmp.add(sc.nextLine());
                }
                sc.close();
                out += String.join(",", tmp) + "]";
                sc = new Scanner(process.getErrorStream());
                while(sc.hasNextLine()){
                    error += sc.nextLine();
                }
                sc.close();
                process.destroy();
            }

        } catch (IOException e) {
            throw new CommandErrorException(e);
        }
    }
}
