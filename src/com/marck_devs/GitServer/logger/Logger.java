package com.marck_devs.GitServer.logger;

import com.marck_devs.GitServer.Constant;

import java.util.ArrayList;
import java.util.HashMap;

public class Logger {

    private ArrayList<Exception> log = new ArrayList<>();
    public static final Logger LOGGER= new Logger();
    private static HashMap<String, Integer> clients = new HashMap<>();

    private Logger(){}

    public Logger log(Exception error){
        log.add(error);
        if(log.size() > Constant.MAX_LOG_LENGTH){
            log = new ArrayList<>(log.subList(log.size() -(Constant.MAX_LOG_LENGTH + 1), log.size() -1));
        }
        return this;
    }

    synchronized public Exception getNext(String index){
        Integer i = clients.get(index);
        Exception out = log.get(i);
        clients.put(index, (i < Constant.MAX_LOG_LENGTH ) ? i+1 : 0);
        return out;
    }

    public String genIndex(){
        String pool =  "qwertyuiop1234567890asdfghjklzxcvbnm";
        StringBuilder out =  new StringBuilder();
        for (int i = 0; i < 20; i++) {
            out.append(pool.charAt((int) (Math.random()*pool.length())));
        }
        clients.put(out.toString(), 0);
        return out.toString();
    }

    public boolean hasNext(String key){
        Integer i = clients.get(key);
        return i < log.size();
    }
}
