package ServerClient.Server.Commands;

public enum Command {

    //server commands
    NAME("/name", new NameHandler()),
    LIST("/list", new ListPlayers()),
    HELP("/help", new HelpPlayers()),
    DISCONNECT("/disconnect", new DisconnectFromServer()),
    LIST_GAMES("/listgames", new ListAvailableGames()),
    JOIN("/join", new JoinAvailableGame()),

    // Game commands
    ROLL("/roll", new RollDice()),
    ANSWER("/Answer", new AnswerTheQuestion()),
    FORFEIT("/forfeit", new ForfeitTheGame()),
    QUIT("/quit", new QuitTheGame()),
    CATEGORIES("/categories", new QuestionsCategories()),
    SCORE("/score", new CheckScore()),

    // General command
    NOT_FOUND("Command not found", new CommandNotFound());



    private String description;
    private CommandHandler handler;

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
        return null;
    }

    public CommandHandler getHandler() {
        //se nao for command retorna o not found;
        return handler;
    }

    public String getDescription() {
        return description;
    }
}
