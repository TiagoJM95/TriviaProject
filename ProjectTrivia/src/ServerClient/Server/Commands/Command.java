package ServerClient.Server.Commands;

public enum Command {

    HELP("/help", new HelpHandler()),
    NAME("/name", new NameHandler()),
    JOIN_GAME("/join", new JoinGameHandler()),
    ONLINE("/online", new OnlineHandler()),
    DISCONNECT("/disconnect", new DisconnectHandler()),
    CREATE_GAME("/create", new CreateGameHandler()),
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
}