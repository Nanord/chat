package commonData;

public class CommandString {
    private String className;
    private  String help;

    public CommandString(String className, String help) {
        this.className = className;
        this.help = help;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }
}
