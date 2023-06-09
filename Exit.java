/**
 * The inheritor class of the {@link Command} that implements the 'Exit' command
 */
public class Exit extends Command {
    /**
     * Constructor initializing the 'Exit' command
     * @param app
     */
    public void Execute(App app){
        System.out.println("You have exited the program, the data has not been saved");
        app.switchOff();
    }
}