import CollectionStrusture.MusicBand;

import java.util.Comparator;
/**
 * The inheritor class of the {@link Command} that implements the 'sort' command
 */
public class Sort extends Command{
    /**
     * Constructor initializing the 'sort' command
     * @param app
     */
    public void Execute(App app){
        Comparator<MusicBand> compareByAlphabet = Comparator.comparing(MusicBand::getName);
        app.getCollection().sort(compareByAlphabet);
        System.out.println("Коллекция отсортирована в алфавитном порядке");
    }
}
