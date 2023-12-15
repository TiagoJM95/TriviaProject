package ServerClient.Server.Commands;

import ServerClient.Server.Server.Server;
import Game.Game.Game;

public class QuestionsCategories implements CommandHandler {
    @Override
    public void execute(Server server, Game.PlayerHandler playerHandler) {
        // playerHandler.send(server.ListQuestionSubjects(), Messages.QUESTION_SUBJECTS);
        // todo method para mostrar o tipo de questoes available
    }
}
