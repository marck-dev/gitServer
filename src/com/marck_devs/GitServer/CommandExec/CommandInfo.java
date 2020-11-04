package com.marck_devs.GitServer.CommandExec;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class CommandInfo extends AbstractCommand {
    private static CommandInfo instance;

    public static CommandInfo instance(){
        if(instance == null)
            instance = new CommandInfo();
        return instance;
    }

    protected CommandInfo() {
        super("Info", "1.0.0", "Get info from the repo", "INFREPO");
    }

    @Override
    public void run() throws CommandErrorException {

        try {
            Process process = exec("git for-each-ref --format='%(authorname);%(authoremail)'");
            HashSet set = new HashSet();
            if(process.isAlive()){
                Scanner sc = new Scanner(process.getInputStream());
                while(sc.hasNextLine()){
                    set.add(sc.nextLine());
                }
                sc.close();
                out = String.join("\n", set);
                error = "";
                sc = new Scanner(process.getErrorStream());
                while(sc.hasNextLine()){
                    error += sc.nextLine() + "\n";
                }
                sc.close();
                process.destroy();

            }
        } catch (IOException e) {
            throw new CommandErrorException(e);
        }

    }
}
