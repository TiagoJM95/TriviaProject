package ServerClient.Server.Commands;

public enum Command {

    NAME("/name", new NameHandler()),
    LIST("/listPlayers", new ListPlayersHandler()),
    HELP("/help", new HelpHandler()),
    DISCONNECT("/disconnect", new DisconnectHandler()),
    LIST_GAMES("/listGames", new ListAvailableGamesHandler()),
    CREATE_GAME("/createGame", new CreateGameHandler()),
    JOIN_GAME("/joinGame", new JoinGameHandler()),
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