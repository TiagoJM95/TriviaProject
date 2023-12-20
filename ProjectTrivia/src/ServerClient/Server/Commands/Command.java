package ServerClient.Server.Commands;

public enum Command {

    HELP("/help", new HelpHandler()),
    NAME("/name", new NameHandler()),
    ONLINE("/online", new OnlineHandler()),
    JOIN_GAME("/join", new JoinGameHandler()),
    CREATE_GAME("/create", new CreateGameHandler()),
    DISCONNECT("/disconnect", new DisconnectHandler()),
    LIST_GAMES("/list", new ListAvailableGamesHandler()),
    NOT_FOUND("Command not found", new CommandNotFoundHandler());


    private final String description;
    private final CommandHandler handler;

    Command(String description, CommandHandler handler) {
        this.description = description;
        this.handler = handler;
    }

    public static Command getCommand(String description) {
        for (Command command : values()) {
            if (description.equals(command.description)) {
                return command;
            }
        }
        return NOT_FOUND;
    }

    public CommandHandler getHandler() {
        return handler;
    }

    public String getDescription() {
        return description;
    }
}