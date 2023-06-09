/**
 * The inheritor class of the {@link Command} that implements the 'Info' command
 */
public class Info extends Command {
    /**
     * Constructor initializing the 'Info' command
     * @param app
     */
    public void Execute(App app){
        System.out.println(
                "Collection type: LinkedList" + "\n" +
                "Objects type: MusicBand" + "\n" +
                "Items count: " + Integer.toString(app.getCollection().toArray().length));
    }
}
