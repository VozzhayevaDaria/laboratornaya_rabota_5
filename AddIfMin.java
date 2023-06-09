import CollectionStrusture.MusicBand;

import java.util.*;
/**
 * The inheritor class of the {@link Command} that implements the 'add_if_min' command
 */
public class AddIfMin extends Command{
    /**
     * Constructor initializing the 'add_if_min' command
     * @param app
     */
    public void Execute(App app){
        MusicBand element = app.createBand();
        LinkedList<MusicBand> collectionCopy = (LinkedList<MusicBand>) app.getCollection().clone();
        Comparator<MusicBand> compareByAlphabet = Comparator.comparing(MusicBand::getName);
        collectionCopy.add(element);
        collectionCopy.sort(compareByAlphabet);
        if (collectionCopy.peekLast().equals(element)){
            System.out.println(collectionCopy.toString());
            app.getCollection().add(element);
            System.out.println("The element is smaller than the minimum element of the collection, so it was added");
        } else {
            System.out.println("The element is larger than the minimum element of the collection, so it was not added");
        }
    }
}
