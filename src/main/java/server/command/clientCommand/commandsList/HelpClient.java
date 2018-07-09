package server.command.clientCommand.commandsList;

import commonData.Data;
import commonData.InfoSend;
import commonData.MessageSend;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HelpClient implements ClientCommand{
    @Override
    public void make(MessageSend msg, InfoSend infoSend) throws IOException {
        StringBuilder str = new StringBuilder();
        Data.getCommandClient().forEach(x -> str.append(x.getKey()).append(" 123-123 ").append(x.getValue()).append('\n'));
        /*Map<String,String> map = Data.getCommandClient().collect(Collectors.toMap(
                i -> i.getKey(),
                i -> i.getValue()
        ));
        map.forEach((x,y) -> {
            str.append(x).append(" - ").append(y);
        });*/
        infoSend.sendMessage(new MessageSend(
                null,
                msg.getCommandText(),
                "ResponseServer: " + str,
                "general"
        ));
    }
}
