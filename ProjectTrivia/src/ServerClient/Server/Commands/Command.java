package ServerClient.Server.Commands;

public enum Command {
    NAME("/name", new NameHandler());

    private String description;
    private CommandHandler handler;
    Command(String description, CommandHandler handler){
        this.description = description;
        this.handler = handler;
    }

    public static Command getCommand(String description){
        for (Command command : values()) {
            if (description.equals(command.description)) {
                return command;
            }
        }
        return null;
    }

    public CommandHandler getHandler(){
        return handler;
    }

    public String getDescription() {
        return description;
    }
}
