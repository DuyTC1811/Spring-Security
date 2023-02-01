package com.example.springsecurity.common.cqrs.commands;

import com.example.springsecurity.common.cqrs.messages.Message;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class BaseCommand extends Message {
    public BaseCommand(String id) {
        super(id);
    }
}
