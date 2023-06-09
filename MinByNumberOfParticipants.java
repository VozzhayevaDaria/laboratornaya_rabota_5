import CollectionStrusture.MusicBand;

/**
 * The inheritor class of the {@link Command} that implements the 'MinByNumberOfParticipants' command
 */
public class MinByNumberOfParticipants extends Command{
    /**
     * Constructor initializing the 'MinByNumberOfParticipants' command
     * @param app
     */
    public void Execute(App app){
        long minCount = app.getCollection().get(0).getNumberOfParticipants();
        MusicBand minElement = app.getCollection().get(0);
        for (MusicBand band: app.getCollection()){
            if (band.getNumberOfParticipants() < minCount) {
                minElement = band;
            }
        }
        System.out.println("Item with minimal number of participants");
        System.out.println(minElement.toString());
    }
}
