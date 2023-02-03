package com.example.springsecurity.controllers.commands;


import io.cqrs.commands.BaseCommand;
import lombok.Data;

@Data
public class OpenAccountCommand extends BaseCommand {
    private String accountHolder;
//    private AccountType accountType;
    private double openingBalance;
}