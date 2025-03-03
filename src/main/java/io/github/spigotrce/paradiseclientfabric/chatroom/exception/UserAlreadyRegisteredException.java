package io.github.spigotrce.paradiseclientfabric.chatroom.exception;

public class UserAlreadyRegisteredException extends Exception {
    public UserAlreadyRegisteredException(String username) {
        super("User '" + username + "' is already registered.");
    }

    public UserAlreadyRegisteredException() {
    }
}
