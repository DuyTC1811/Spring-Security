package com.example.springsecurity.common.cqrs.infrastructure;


import com.example.springsecurity.common.cqrs.commands.BaseCommand;
import com.example.springsecurity.common.cqrs.commands.CommandHandlerMethod;

public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}
