package com.marck_devs.GitServer.CommandExec;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CommandRemove extends AbstractCommand{
    protected CommandRemove() {
        super("Remove repo", "1.0.0", "Remove repository from the server", "RMREPO");
        setHelp("Remove one repository from the server. It need the name of the repository");
        setUsage("RMREPO [\"|']<repo_name>[\"|']");
    }

    @Override
    public void run() throws CommandErrorException {
        File file = new File(repo);
        File parent = file.getParentFile();
        String command = "rmdir /q /s " ;
        if(!isWindow())
            command = "rm -rf ";
        command +=  file.getName();

        try {
            Process process = exec(command, parent);
            out = "";
            error = "";
           if(process.isAlive()){
               Scanner sc = new Scanner(process.getInputStream());
               while(sc.hasNextLine()){
                   out += sc.nextLine() + "\n";
               }
               sc.close();
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
