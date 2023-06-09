import java.io.*;
/**
 * The inheritor class of the {@link Command} that implements the 'Script' command
 */
public class Script extends Command{
    /**
     * Constructor initializing the 'Script' command
     * @param str
     * @param app
     */
    public void Execute(String str, App app) {
        try {
            File file = new File(str);
            String line;
            if (! app.getExecutingScripts().contains(str)) {
                app.addScript(str);
                BufferedReader reader = new BufferedReader(new FileReader(file));
                System.out.println("Выполняется скрипт " + str);
                while (true){
                    line = reader.readLine();
                    if (line == null){
                        break;
                    }
                    app.readLine(line);
                }
                reader.close();
                app.dellScript(str);
            } else {
                System.out.println("Скрипт " + str + " уже выполняется");
            }
            System.out.println("Скрипт " + str + "выполнен");
        } catch (IOException e) {
            System.out.println("Файл не найден");
        }
    }
}
