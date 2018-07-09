package client;

import commonData.DATA;

public class Main {
    public static void main(String[] args) throws Exception{
        WorkClient workClient = new WorkClient();
        DATA.reload(false);
        workClient.run(DATA.getPORT(), DATA.getHOST());
    }
}
