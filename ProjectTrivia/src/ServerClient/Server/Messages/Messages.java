package ServerClient.Server.Messages;

public abstract class Messages {

    public static final String SERVER_STARTED = "Server started on port: %s";
    public static final String NO_SUCH_COMMAND = " ❌ Invalid Command";
    public static final String COMMAND_LIST = """
            list of available Commands:
            /list -> Gets you a list of the connected Players.
            /name -> Let's you choose your name for the Game.
            /roll -> Rolls your dice in the game.
            /answer -> Let's you answer the question.
            /questions -> Shows you the categories of questions you will get.
            /forfeit -> Use this if you want to give up on the current game.
            /quit -> Exits the game server.
            """;
    public static final String PLAYER_DISCONNECTED = " left the game server";
    public static final String PLAYER_FORFEIT = " has forfeit the game";
    public static final String GAME_BEGINS = "The game has started!";
    public static final String CORRECT_ANSWER = "The answer is correct!";
    public static final String WRONG_ANSWER = "The answer is wrong!";
    public static final String ROLL_DICE = "The dice rolled: ";
    public static final String SCORES = "The current scores are: ";
    public static final String QUESTIONS_CATEGORIES = "The possible subjects for questions are: ";
}
