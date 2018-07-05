package commonData;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Data {
    private static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";

    private static String HOST = "localhost";
    private static int PORT = 7837;

    private static HashMap<String, String> commandServer = new HashMap();
    private static HashMap<String, String> commandClient = new HashMap();

    public static void reload() {
        reload(false);
    }

    public static void reload(boolean out){
        Properties prop = new Properties();
        InputStream input = null;

        try {

            String filename = "config.properties";
            //input = Data.class.getClassLoader().getResourceAsStream(filename);
            input = new FileInputStream(PATH_TO_PROPERTIES);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return;
            }
            if(out)
                System.out.println("////////////////////////////////////////////");

            prop.load(input);

            Enumeration<?> e = prop.propertyNames();

            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                String value = prop.getProperty(key);
                if(out)
                    System.out.println("Key : " + key + ", Value : " + value);

                if(key.equalsIgnoreCase("HOST")){
                    HOST = value;
                    continue;
                }

                if(key.equalsIgnoreCase("PORT")){
                    PORT = Integer.parseInt(value);
                    continue;
                }

                if(key.split("\\.")[0].equalsIgnoreCase("commandServer")) {
                    String[] val = value.split(",");
                    commandServer.put(val[0], val[1]);
                    continue;
                }

                if(key.split("\\.")[0].equalsIgnoreCase("commandClient")) {
                    String[] val = value.split(",");
                    commandClient.put(val[0], val[1]);
                }
            }
            if(out)
                System.out.println("////////////////////////////////////////////");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public static String getPathToProperties() {
        return PATH_TO_PROPERTIES;
    }

    public static String getHOST() {
        return HOST;
    }

    public static int getPORT() {
        return PORT;
    }

    public static HashMap<String, String> getCommandServer() {
        return commandServer;
    }

    public static HashMap<String, String> getCommandClient() {
        return commandClient;
    }

    public static void main(String[] args) {
        Data.reload(true);
    }
}
