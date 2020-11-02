package com.marck_devs.GitServer.CommandExec;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class CommandRun extends AbstractCommand {

    private static CommandRun instance;
    public static CommandRun instance(){
        if(instance == null)
            instance = new CommandRun();
        return instance;
    }


    private CommandRun() {
        super("Run", "1.0.0", "Execute git command", "RN");
        setUsage("RN ['|\"]<repo_name>['|\"] ['|\"]<command>['|\"]");
        setHelp("Run any git command and return the result");
    }

    @Override
    public void run() throws CommandErrorException {
        if(args.get(2).isEmpty())
            throw new CommandErrorException("Empty command");
        try {
            Process process;
//            run process
            if((process = exec("git "+ args.get(2))).isAlive()){
                Scanner sc = new Scanner(process.getInputStream());
                out = "";
                this.error = "";
//                save the output
                while (sc.hasNextLine()){
                    out = out + (sc.nextLine() + "\n");
                }
                sc.close();
//                save the errors
                Scanner error = new Scanner(process.getErrorStream());
                while (error.hasNextLine()){
                    this.error = this.error + (error.nextLine() + "\n");
                }
                error.close();
//                destroy the process
                process.destroy();
                
            }else
                throw new CommandErrorException("shell error: cd " + repo);
            

        } catch (IOException e) {
            throw new CommandErrorException(e);
        }
    }
}
