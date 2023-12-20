package Game.Game.Messages;

public class Messages {

    public static final String COMMAND_LIST_GAME = """
            /roll -> Rolls your dice in the game.
            /answer <X> -> Let's you answer the question. Choose only one option.
            /forfeit -> Use this if you want to give up on the current game.
            """;
    public static final String PLAYER_FORFEIT = " has forfeit the game";
    public static final String GAME_BEGINS = "\nGame is starting in: ";
    public static final String CORRECT_ANSWER = "The answer is correct!";
    public static final String WRONG_ANSWER = "The answer is wrong!";
    public static final String ROLL_DICE = "The dice rolled: ";
    public static final String WIN_MESSAGE = " has won the game";
    public static final String GAME_PIECES = "This is your game piece: ";
    public static final String NO_SUCH_COMMAND = " ❌ Invalid Command ❌";
    public static final String NOT_YOUR_TURN = "Not your turn!";

    public static final String INVALID_USE_OF_COMMAND = "Invalid use of the command!";
    public static final String GAME_ALREADY_STARTED = "Game already started!";
}
