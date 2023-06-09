import CollectionStrusture.MusicBand;
/**
 * The inheritor class of the {@link Command} that implements the 'update_by_id' command
 */
public class UpdateById extends Command{
    /**
     * Constructor initializing the 'update_by_id' command
     * @param stringID
     * @param app
     */
    public void Execute(String stringID, App app){
        try {
            MusicBand oldElement = app.getByID(Long.valueOf(stringID));
            if (oldElement != null) {
                MusicBand newElement = app.createBand();
                if (newElement != null) {
                    oldElement.setName(newElement.getName());
                    oldElement.setCoordinates(newElement.getCoordinates());
                    oldElement.setNumberOfParticipants(newElement.getNumberOfParticipants());
                    oldElement.setSinglesCount(newElement.getSinglesCount());
                    oldElement.setAlbumsCount(newElement.getAlbumsCount());
                    oldElement.setGenre(newElement.getGenre());
                    oldElement.setBestAlbum(newElement.getBestAlbum());
                    System.out.println("The item has been successfully updated. Here's what it looks like:" + "\n" + oldElement.toString());
                }
            } else {
                System.out.println("Element wasn't found");
            }
        } catch (RuntimeException e) {
            System.out.println("Wrong argument for this command (must be digit)");
        }
    }
}
