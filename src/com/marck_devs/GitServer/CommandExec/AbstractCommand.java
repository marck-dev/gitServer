package com.marck_devs.GitServer.CommandExec;

import com.marck_devs.GitServer.Constant;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractCommand implements  Command{
    protected String name;
    protected String version;
    protected String desc;
    protected String command;
    protected String usage;
    protected String help;
    protected String out;
    protected String input;
    protected String repo;
    protected String error;
    protected static final String REGEX = "^(?<command>\\w*)(\\s*[\"|']?(?<repo>\\w*))?[\"|']?(\\s*[\"|']?(?<run>.*[^\"]))?\"?$";
    protected ArrayList<String> args = new ArrayList<>();
    private boolean window = false;

    protected AbstractCommand(String name, String version, String desc, String command) {
        this.name = name;
        this.version = version;
        this.desc = desc;
        this.command = command;
        load();
    }

    public void load(String name){
        String key = name;
        if(name == null)
            key = command;
        CommandDB.instace().put(key, this);
    }

    public void load(){
        load(null);
    }

    public void in(String input){
        this.input = input;
        processIn();
    }

    protected void processIn(){
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(input);
        if(m.find()){
            args = new ArrayList<>();
            args.add(m.group("command"));
            args.add(m.group("repo"));
            args.add(m.group("run"));
        }
    }

    public void exec() throws CommandErrorException{
        if(args.size() == 0)
            throw new CommandNotFoundException("No se ha encontrado el commando");
        if(!args.get(0).equalsIgnoreCase(command))
            throw new CommandNotFoundException("No se ha encontrado el commando");
        String repo = Constant.REPO_HOME + args.get(1) + Constant.REPO_EXT;
        if(!args.get(1).isEmpty() && !this.getClass().equals(CommandMkDir.class) && !Files.exists(Paths.get(repo))){
            throw new RepoNotFoundException("Repo "+ repo + " not found");
        }

        this.repo = repo;
        run();
    }

    /**
     * Check if the os is windows
     * @return boolean     */
    protected boolean isWindow(){
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

    protected Process exec(String command,  File file) throws IOException {
//        for windows
        if(isWindow())
            return Runtime.getRuntime().exec(new String[]{"cmd","/c" ,command}, null, (file != null ? file : new File(repo)));
        else {
//            for unix
            return Runtime.getRuntime().exec(new String[]{"sh","-c" ,command}, null, (file != null ? file : new File(repo)));
        }
    }

    protected Process exec(String command) throws IOException {
        return exec(command, null);
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

    public String getRepo() {
        return repo;
    }

    public String getError() {
        return error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractCommand that = (AbstractCommand) o;
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
