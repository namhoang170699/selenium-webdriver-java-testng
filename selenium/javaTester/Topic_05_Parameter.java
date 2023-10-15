package javaTester;

public class Topic_05_Parameter {
    static String fullNameGlobal = "Automation Testing";

    public static void main(String[] args) {
        setFullName("Manual Testing");
    }

    public static void setFullName(String fullName){
        fullNameGlobal = fullName;
    }
}
