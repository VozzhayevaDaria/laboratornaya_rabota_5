/**
 * The inheritor class of the {@link Command} that implements the 'show' command
 */
public class Show extends Command {
    /**
     * Constructor initializing the 'show' command
     * @param app
     */
    public void Execute(App app){
        Object[] list = app.getCollection().toArray();
        if (list.length == 0){
            System.out.println("Collection is empty");
        } else {
            for(int i = 0; i < list.length; i++) {
                System.out.println(list[i].toString());
            }
        }
    }
}
