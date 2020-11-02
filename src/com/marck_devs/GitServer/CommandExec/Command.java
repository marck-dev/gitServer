package com.marck_devs.GitServer.CommandExec;

public interface Command {

    public void run() throws CommandErrorException;

    public static class CommandErrorException extends Exception{
        public CommandErrorException() {
        }

        public CommandErrorException(String message) {
            super(message);
        }

        public CommandErrorException(String message, Throwable cause) {
            super(message, cause);
        }

        public CommandErrorException(Throwable cause) {
            super(cause);
        }

        public CommandErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }
}
