package Game.Game.GameCommands;

public enum GameCommand {
    SCORE("/score", new ScoreHandler()),
    ROLL("/roll", new RollDiceHandler()),
    ANSWER("/answer", new AnswerHandler()),
    FORFEIT("/forfeit", new ForfeitHandler()),
    START_GAME("/startGame", new StartGameHandler()),
    CATEGORIES("/categories", new CategoriesHandler()),
    LEAVE_LOBBY("/leaveLobby", new LeaveLobbyHandler()),
    HELP("/help", new HelpGameHandler()),
    NOT_FOUND("Command not found", new GameCommandNotFound());

    private final String description;
    private final GameCommandHandler handler;

    GameCommand(String description, GameCommandHandler handler) {
        this.description = description;
        this.handler = handler;
    }

    public static GameCommand getCommand(String description) {
        for (GameCommand command : values()) {
            if (description.equals(command.description)) {
                return command;
            }
        }
        return NOT_FOUND;
    }

    public GameCommandHandler getHandler() {
        return handler;
    }
}
