import CollectionStrusture.MusicBand;

import java.util.*;
/**
 * The inheritor class of the {@link Command} that implements the 'remove_greater' command
 */
public class RemoveGreater extends Command{
    /**
     * Constructor initializing the 'remove_greater' command
     * @param app
     */
    public void Execute(App app){
        MusicBand element = app.createBand();
        if (element != null) {
            LinkedList<MusicBand> collectionCopy = (LinkedList<MusicBand>) app.getCollection().clone();
            Comparator<MusicBand> compareByAlphabet = Comparator.comparing(MusicBand::getName);
            collectionCopy.add(element);
            collectionCopy.sort(compareByAlphabet);
            int count = 0;
            for (MusicBand greaterBand : collectionCopy) {
                if (greaterBand.equals(element)) {
                    break;
                }
                app.getCollection().remove(app.getByID(greaterBand.getId()));
                count++;
            }
            System.out.println(Integer.valueOf(count).toString() + " 5 items have been removed from the collection");
        }
    }
}
