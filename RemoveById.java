import CollectionStrusture.MusicBand;
/**
 * The inheritor class of the {@link Command} that implements the 'remo_by_id' command
 */
public class RemoveById extends Command{
    /**
     * Constructor initializing the 'RemoveById' command
     * @param stringID
     * @param app
     */
    public void Execute(String stringID, App app){
        try {
            Long id = Long.valueOf(stringID);
            MusicBand band = app.getByID(id);
            if (band == null){
                System.out.println("Item with id " + id.toString() + " not found");
            } else {
                app.getCollection().remove(band);
                System.out.println("Item with id " + id.toString() + " successfully removed");
            }
        } catch (RuntimeException e) {
            System.out.println("Wrong argument for this command (must be digit)");
        }
    }
}
