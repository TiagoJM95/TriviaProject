package Game.Game.GameCommands;


public enum GameCommand {
    ROLL("/roll", new RollDice()),
    ANSWER("/Answer", new AnswerTheQuestion()),
    FORFEIT("/forfeit", new ForfeitTheGame()),
    QUIT("/quit", new QuitTheGame()),
    CATEGORIES("/categories", new QuestionsCategories()),
    SCORE("/score", new CheckScore()),
    NOT_FOUND("Command not found", new GameCommandNotFound());

    private String description;
    private GameCommandHandler handler;

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
        return null;
    }

    public GameCommandHandler getHandler() {
        //se nao for command retorna o not found;
        return handler;
    }

    public String getDescription() {
        return description;
    }
}
