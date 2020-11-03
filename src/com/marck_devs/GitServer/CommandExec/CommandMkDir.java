package com.marck_devs.GitServer.CommandExec;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class CommandMkDir extends AbstractCommand {

    private static CommandMkDir instance;
    public static CommandMkDir instance(){
        if(instance == null)
            instance = new CommandMkDir();
        return instance;
    }

    protected CommandMkDir() {
        super("Make Repo", "1.0.0", "Make new repo", "MKREPO");
        setUsage("MKREPO [\"|']<new_repo_name>[\"|']");
        setHelp("Make new repo in the server. The argument that need it's the name of the new repo");
    }

    @Override
    public void run() throws CommandErrorException {
        try {
            Files.createDirectory(Paths.get(repo));
            Process proces;
            out = "";
            error = "";
            if((proces =exec("git init --bare")).isAlive()){
                Scanner sc = new Scanner(proces.getInputStream());
                while(sc.hasNextLine())
                    out += sc.nextLine() + "\n";
                sc.close();
                sc = new Scanner(proces.getErrorStream());
                while(sc.hasNextLine())
                    error += sc.nextLine() + "\n";
                sc.close();
                proces.destroy();
            }
        } catch (IOException e) {
            throw new CommandErrorException(e);
        }
    }
}
