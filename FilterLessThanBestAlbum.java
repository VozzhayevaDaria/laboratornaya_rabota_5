import CollectionStrusture.MusicBand;
/**
 * The inheritor class of the {@link Command} that implements the 'FilterLessThanBestAlbum' command
 */
public class FilterLessThanBestAlbum extends Command{
    /**
     * Constructor initializing the 'FilterLessThanBestAlbum' command
     * @param stringSales
     * @param app
     */
    public void Execute(String stringSales, App app){
        try {
            float sales = Float.valueOf(stringSales);
            System.out.println("Bands whose best album sales are less than " + stringSales);
            for (MusicBand band: app.getCollection()){
                if (band.getBestAlbum().getSales() < sales) {
                    System.out.println(band.toString());
                }
            }
        } catch (RuntimeException e) {
            System.out.println("Wrong argument for this command (must be digit)");
        }
    }
}
