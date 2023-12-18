package ServerClient.Server.Messages;

public abstract class Messages {

    public static final String SERVER_STARTED = "Server started on port: %s";
    public static final String NO_SUCH_COMMAND = " ❌ Invalid Command ❌";
    public static final String INVALID_NAME = " ❌ Invalid Name ❌";
    public static final String COMMAND_LIST_SERVER = """
            \n\tList of available commands:
            
            /name -> Let's you choose your name for the Game
            /listPlayers -> Gets you a list of all players online
            /createGame -> Create a new Game lobby
            /listGames -> Gets you a list of all Game lobbies
            /joinGame -> Allows you to join an available Game
            /disconnect -> Use this to disconnect from the server
            /help -> Use this to review the commands again
            """;
    public static final String PLAYER_DISCONNECTED = " left the server";
    public static final String ALL_GAME_CREATED = "A new Game was just created! GameId: ";
    public static final String GAME_CREATED = "You've created a game!";
    public static final String DEFAULT_PLAYER_NAME = "Player";
    public static final String WELCOME = "\n\t\tWELCOME ";
    public static final String HAS_CONNECTED = " has connected to this server!";
    public static final String ALREADY_IN_LOBBY = "You're in a lobby already";
    public static final String NOT_IN_LOBBY = "You're not in a lobby";
    public static final String LEFT_LOBBY = "You have left the lobby!";
    public static final String CLIENT_EXISTS = "Client already exists";
    public static final String SELF_NAME_CHANGE = "You changed your name to ";
    public static final String NAME_CHANGE = " changed name to ";
    public static final String SERVER_ERROR_1 = "Something went wrong with server! Couldn't start.";
    public static final String NO_GAME_LOBBY = "No Game lobbies created!";
    public static final String GAME_LIST = "\tAvailable Game lobbies: \n";
    public static final String JOIN_LOBBY = "You've joined the lobby of game";
    public static final String PLAYER_JOINED_LOBBY = " has joined your lobby";
    public static final String NO_SUCH_GAME = "There is no game with such ID!";
    public static final String FULL_LOBBY = "This lobby is full";
    public static final String GAME_BANNER = """
         

    \t        ████████╗██████╗ ██╗██╗   ██╗██╗ █████╗ ██╗          \s
    \t        ╚══██╔══╝██╔══██╗██║██║   ██║██║██╔══██╗██║          \s
    \t           ██║   ██████╔╝██║██║   ██║██║███████║██║          \s
    \t           ██║   ██╔══██╗██║╚██╗ ██╔╝██║██╔══██║██║          \s
    \t           ██║   ██║  ██║██║ ╚████╔╝ ██║██║  ██║███████╗     \s
    \t           ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═══╝  ╚═╝╚═╝  ╚═╝╚══════╝     \s
    \t                                                             \s
    \t        ██████╗ ██╗   ██╗██████╗ ███████╗██╗   ██╗██╗████████╗
    \t        ██╔══██╗██║   ██║██╔══██╗██╔════╝██║   ██║██║╚══██╔══╝
    \t        ██████╔╝██║   ██║██████╔╝███████╗██║   ██║██║   ██║  \s
    \t        ██╔═══╝ ██║   ██║██╔══██╗╚════██║██║   ██║██║   ██║  \s
    \t        ██║     ╚██████╔╝██║  ██║███████║╚██████╔╝██║   ██║  \s
    \t        ╚═╝      ╚═════╝ ╚═╝  ╚═╝╚══════╝ ╚═════╝ ╚═╝   ╚═╝  \s
                                                                 \s""";
}
