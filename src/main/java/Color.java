public class Color {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static StringBuilder txtToPrint;

    public Color() {
        txtToPrint = new StringBuilder();
    }

    public void print(boolean newline) {
        if (newline==true) {
            System.out.println(txtToPrint.toString());
        } else {
            System.out.print(txtToPrint.toString());
        }
    }

    public void clearTxtBuffer() {
        this.txtToPrint.setLength(0);
    }

    public Color printTxtRed(String txt) {
        txtToPrint.append(ANSI_RED + txt + ANSI_RESET);
        return this;
    }

    public Color printTxtGreen(String txt) {
        txtToPrint.append(ANSI_GREEN + txt + ANSI_RESET);
        return this;
    }

    public Color printTxtYellow(String txt) {
        txtToPrint.append(ANSI_YELLOW + txt + ANSI_RESET);
        return this;
    }

    public Color printTxtBlue(String txt) {
        txtToPrint.append(ANSI_BLUE + txt + ANSI_RESET);
        return this;
    }
}