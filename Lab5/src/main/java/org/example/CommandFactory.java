package org.example;

public class CommandFactory{
    public static Command getCommand(String name){
        return switch(name.toLowerCase()){
            case "add" -> new AddCommand();
            case "view"  -> new ViewCommand();
            case "save" -> new SaveCommand();
            case "load"  -> new LoadCommand();
            case "report" -> new ReportCommand();
            default -> null;
        };
    }
}
