package com.marck_devs.GitServer.CommandExec;

public interface Command {

    void run() throws CommandErrorException;

    class CommandErrorException extends Exception{
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
    final class CommandNotFoundException extends CommandErrorException{
        public CommandNotFoundException() {
        }

        public CommandNotFoundException(String message) {
            super(message);
        }

        public CommandNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

        public CommandNotFoundException(Throwable cause) {
            super(cause);
        }

        public CommandNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }
    final class RepoNotFoundException extends CommandErrorException{
        public RepoNotFoundException() {
        }

        public RepoNotFoundException(String message) {
            super(message);
        }

        public RepoNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

        public RepoNotFoundException(Throwable cause) {
            super(cause);
        }

        public RepoNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }
}
