package com.marck_devs.GitServer.CommandExec;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("ALL")
public class CommandList extends AbstractCommand {

    private static CommandList instance;
    public static CommandList instance(){
        if(instance == null)
            instance = new CommandList();
        return instance;
    }

    private static final String CMD_REGEX = "(?<repo>\\w*)\\.git$";

    protected CommandList() {
        super("List repo", "1.0.0", "Lit all repos", "LSREPO");
        setHelp("List server repositories. Any argument needed.");
        setUsage("LSREPO");
    }

    @Override
    public void run() throws CommandErrorException {
        Pattern pattern = Pattern.compile(CMD_REGEX);
        try {
            out = "";
            error = "";
            Process procces;
            String cmd = isWindow() ? "dir" : "ls -l";
            if((procces = exec(cmd)).isAlive()){
                Scanner sc = new Scanner(procces.getInputStream());
                while(sc.hasNextLine()){
                    String tmp = sc.nextLine();
                    Matcher matcher = pattern.matcher(tmp);
                    if(matcher.find()) {
                        out += matcher.group("repo") + "\n";
                    }
                }
                sc.close();
                sc = new Scanner(procces.getErrorStream());
                while(sc.hasNextLine()){
                    error += sc.nextLine() + "\n";
                }
                sc.close();
                procces.destroy();
            }
        } catch (IOException e) {
            throw new CommandErrorException(e);
        }
    }
}
