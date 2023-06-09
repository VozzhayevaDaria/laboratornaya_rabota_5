import CollectionStrusture.MusicBand;

import java.util.Comparator;
import java.util.LinkedList;
/**
 * The inheritor class of the {@link Command} that implements the 'PrintAscending' command
 */
public class PrintAscending extends Command{
    /**
     * Constructor initializing the 'PrintAscending' command
     * @param app
     */
    public void Execute(App app){
        LinkedList<MusicBand> collectionCopy = (LinkedList<MusicBand>) app.getCollection().clone();
        Comparator<MusicBand> compareByAlphabet = Comparator.comparing(MusicBand::getName);
        collectionCopy.sort(compareByAlphabet);
        for (MusicBand band: collectionCopy){
            System.out.println(band.toString());
        }
    }
}
