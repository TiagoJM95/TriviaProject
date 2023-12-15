package ServerClient.Server.Commands;

import ServerClient.Server.Server.Server;
import Game.Game.Game_old;

public class QuestionsCategories implements CommandHandler {
    @Override
    public void execute(Server server, Game_old.PlayerHandler playerHandler) {
        // playerHandler.send(server.ListQuestionSubjects(), Messages.QUESTION_SUBJECTS);
        // todo method para mostrar o tipo de questoes available
    }
}
