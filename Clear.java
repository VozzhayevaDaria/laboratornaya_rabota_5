import CollectionStrusture.MusicBand;

import java.util.LinkedList;
/**
 * The inheritor class of the {@link Command} that implements the 'Clear' command
 */
public class Clear extends Command{
    /**
     * Constructor initializing the 'Clear' command
     * @param app
     */
    public void Execute(App app){
        app.setCollection(new LinkedList<MusicBand>());
        System.out.println("The collection has been cleared");
    }
}
