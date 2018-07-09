package client;

public class Main {
    public static void main(String[] args) throws Exception{
        WorkClient workClient = new WorkClient();
        commonData.Data.reload(true);
        workClient.run(commonData.Data.getPORT(), commonData.Data.getHOST());
    }
}
