package ServerClient.Server.Commands;

import Game.Game.GameCommands.LeaveLobbyHandler;
import Game.Game.GameCommands.StartGameHandler;

public enum Command {

    HELP("/help", new HelpHandler()),
    NAME("/name", new NameHandler()),
    JOIN_GAME("/joinGame", new JoinGameHandler()),
    LIST("/listPlayers", new ListPlayersHandler()),
    START_GAME("/startGame", new StartGameHandler()),
    DISCONNECT("/disconnect", new DisconnectHandler()),
    LEAVE_LOBBY("/leaveLobby", new LeaveLobbyHandler()),
    CREATE_GAME("/createGame", new CreateGameHandler()),
    LIST_GAMES("/listGames", new ListAvailableGamesHandler()),
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