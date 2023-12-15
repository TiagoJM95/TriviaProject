package ServerClient.Server.Commands;

import Game.Game.Game;
import ServerClient.Server.Server.Server;

public class QuestionsCategories implements CommandHandler {
    @Override
    public void execute(Server server, Server.ClientConnectionHandler playerHandler) {
        // playerHandler.send(server.ListQuestionSubjects(), Messages.QUESTION_SUBJECTS);
        // todo method para mostrar o tipo de questoes available
    }
}
