import java.util.*;

public class Main {
    public static void main(String args[]){
        System.out.println("\u001B[35m"+ "Welcome! To see all available commands, enter 'Help'" + "\u001B[0m\"");
        //App app = new App(new String[]{"D:\\j_ws\\Lab_5\\src\\BandsInput.yaml"});
        App app = new App(args);
        while(app.getStatus()){
            Scanner keyboard = new Scanner(System.in);
            String line = keyboard.nextLine();
            app.readLine(line);
        }
    }
}
//"D:\\j_ws\\Lab_5\\src\\BandsInput.yaml"
//Script D:\\j_ws\\Lab_5\\src\\TestScript.txt