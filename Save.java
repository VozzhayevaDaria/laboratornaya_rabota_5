import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
/**
 * The inheritor class of the {@link Command} that implements the 'Save' command
 */
public class Save extends Command {
    /**
     * Constructor initializing the 'Save' command
     * @param app
     */
    public void Execute(App app){
        try{
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.registerModule(new JavaTimeModule());
            mapper.writeValue(app.getCollectionFile(), app.getCollection());
            System.out.println("КОллекция успешно сохранена в файл " + app.getCollectionFile());
        } catch (IOException e) {
            System.out.println("Файл " + app.getCollectionFile() + " не найден");
        }
    }
}
