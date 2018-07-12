package commonData;

import server.db.model.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class DATA {
    static {
        reload();
    }

    private static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";

    private static String HOST = "localhost";
    private static int PORT = 7837;

    private static String mainGroup = "general";

    private static Map<String, String> commandServer;
    private static Map<String, String> commandClient;

    public static void reload() {
        reload(false);
    }

    public static String reload(boolean out){
        StringBuilder str = new StringBuilder();

        Properties prop = new Properties();
        InputStream input = null;

        try {

            String filename = "config.properties";

            input = new FileInputStream(PATH_TO_PROPERTIES);

            commandClient = new ConcurrentHashMap<String, String>();
            commandServer = new ConcurrentHashMap<String, String>();

            if(out)
                str.append("////////////////////////////////////////////" + "\n");

            prop.load(input);

            Enumeration<?> e = prop.propertyNames();

            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                String value = prop.getProperty(key);
                if(out)
                    str.append("Key : " + key + ", Value : " + value + "\n");

                if(key.equalsIgnoreCase("HOST")){
                    HOST = value;
                    continue;
                } else
                if(key.equalsIgnoreCase("PORT")){
                    PORT = Integer.parseInt(value);
                    continue;
                } else
                if(key.equalsIgnoreCase("mainGroup")){
                    mainGroup = value;
                }
                else {
                    String[] comm = key.split("\\.");
                    String commandStr = "/" + comm[1];
                    if(comm[0].equalsIgnoreCase("commandServer")) {
                        commandServer.put(commandStr, value);
                    } else if(comm[0].equalsIgnoreCase("commandClient")) {
                        commandClient.put(commandStr, value);
                    }
                }
            }
            if(out)
                str.append("////////////////////////////////////////////" + "\n");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    str.append(e.toString());
                }
            }
            return str.toString();
        }
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    public static String getPathToProperties() {
        return PATH_TO_PROPERTIES;
    }

    public static String getHOST() {
        return HOST;
    }

    public static int getPORT() {
        return PORT;
    }

    public static Stream<Map.Entry<String, String>> getCommandServer() {
        return commandServer.entrySet().stream();
    }

    public static Stream<Map.Entry<String, String>> getCommandClient() {
        return commandClient.entrySet().stream();
    }

    public static String getMainGroup() {
        return mainGroup;
    }

    public static void main(String[] args) {
        DATA.reload(true);
    }
}
