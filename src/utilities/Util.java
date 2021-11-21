package utilities;

public class Util {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void createWarnMessage(String message){
        System.out.printf("\n%s[%sWarn%s]%s%s%s\n", ANSI_RESET,ANSI_YELLOW,ANSI_RESET,ANSI_PURPLE,message,ANSI_RESET);
    }
    public static void createErrorMessage(String message){
        System.out.printf("\n%s[%sError%s]%s%s%s\n", ANSI_RESET,ANSI_RED,ANSI_RESET,ANSI_PURPLE,message,ANSI_RESET);
    }
    public static void createInfoMessage(String message){
        System.out.printf("\n%s[%sInfo%s]%s%s%s\n", ANSI_RESET,ANSI_GREEN,ANSI_RESET,ANSI_PURPLE,message,ANSI_RESET);
    }
    public static String randomColor(){
        int num = (int) (Math.random()*7);
        switch (num){
            case 1:
                return ANSI_BLACK;
            case 2:
                return ANSI_RED;
            case 3:
                return ANSI_GREEN;
            case 4:
                return ANSI_YELLOW;
            case 5:
                return ANSI_BLUE;
            case 6:
                return ANSI_PURPLE;
            case 7:
                return ANSI_CYAN;
        }
        return ANSI_WHITE;
    }
}
