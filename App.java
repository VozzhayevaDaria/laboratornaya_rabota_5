import CollectionStrusture.Album;
import CollectionStrusture.Coordinates;
import CollectionStrusture.MusicBand;
import CollectionStrusture.MusicGenre;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


/**
 * Class that implements the operation of the application
 * This class is the input point of the app
 */
public class App {
    /** Field view of the collection at a given time */
    private LinkedList<MusicBand> collection;
    /** Field provides looped command input */
    private Boolean isWorking;
    /** Field stores the file entered by the user for reading and writing the collection*/
    private File collectionFile;
    /** Field stores id's of all musicbands in collection*/
    public static HashSet<Long> idList;
    /** Field provides unlooped Script executing*/
    private HashSet<String> executingScripts;

    /**
     * reads data from {@link App#collectionFile} and passes it to a {@link App#collection}
     * @param args
     */
    public App(String args[]){
        this.isWorking = true;
        this.idList = new HashSet<Long>();
        this.executingScripts = new HashSet<String>();
        if (args.length == 0) {
            this.collection = new LinkedList<MusicBand>();
            System.out.println("The file was not entered, an empty collection was created");
            collectionFile = new File("D:\\j_ws\\Lab_5\\src\\BandsInput.yaml");
        } else {
            try {
                ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
                collectionFile = new File(args[0]);
                CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(LinkedList.class, MusicBand.class);
                objectMapper.registerModule(new JavaTimeModule());
                this.collection = objectMapper.readValue(collectionFile, listType);
                System.out.println("The file was successfully read, the collection is filled with data from the file");
                for (MusicBand band : collection) {
                    idList.add(band.getId());

                }
            } catch (IOException e) {
                this.collection = new LinkedList<MusicBand>();
                System.out.println("The file was not found, the collection is empty");
            }
        }
    }

    public void switchOff() {
        isWorking = false;
    }

    public HashSet<String> getExecutingScripts() {
        return executingScripts;
    }
    /** adds Script to {@link App#executingScripts}*/
    public void addScript(String script) {
        this.executingScripts.add(script);
    }
    /** removes Script акщь {@link App#executingScripts}*/
    public void dellScript(String script) {
        this.executingScripts.remove(script);
    }

    public Boolean getStatus() {
        return isWorking;
    }

    public File getCollectionFile() {
        return collectionFile;
    }

    public LinkedList<MusicBand> getCollection() {
        return collection;
    }

    public static HashSet<Long> getIdList(){
        return idList;
    }
    public static void addId(Long id){
        idList.add(id);
    }

    public void setCollection(LinkedList<MusicBand> collection) {
        this.collection = collection;
    }
    /** reads a command and creates a new instance of {@link Command} inheritor*/
    public void readLine(String line) {
        try {
            HashMap<String, String> commandNames = new HashMap<>();
            commandNames.put("help", "Help");
            commandNames.put("info", "Info");
            commandNames.put("show", "Show");
            commandNames.put("add", "Add");
            commandNames.put("update_by_id", "UpdateById");
            commandNames.put("remove_by_id", "RemoveById");
            commandNames.put("clear", "Clear");
            commandNames.put("save", "Save");
            commandNames.put("script", "Script");
            commandNames.put("exit", "Exit");
            commandNames.put("add_if_min", "AddIfMin");
            commandNames.put("remove_greater", "RemoveGreater");
            commandNames.put("sort", "Sort");
            commandNames.put("min_by_number_of_participants", "MinByNumberOfParticipants");
            commandNames.put("filter_less_than_best_album", "FilterLessThanBestAlbum");
            commandNames.put("print_ascending", "PrintAscending");
            String[] command = line.split(" "); //разделение строки на комманду и аргумент
            if (command.length == 1) { //комманда без аргументов
                Command object = (Command) Class.forName(commandNames.get(command[0])).getConstructor(App.class).newInstance(new Object[]{this});
            } else { //команда с аргументами
                Command object = (Command) Class.forName(commandNames.get(command[0])).getConstructor(String.class, App.class).newInstance(new Object[]{command[1], this});
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Command not found");
        } catch (java.lang.ClassCastException e) {
            System.out.println("Command not found");
        } catch (NoSuchMethodException e) {
            System.out.println("Command not found");
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param id
     * @return {@link MusicBand}
     */
    public MusicBand getByID(Long id) {
        for (MusicBand band : this.getCollection()) {
            if (band.getId().equals(id)) {
                return band;
            }
        }
        return null;
    }

    /**
     * creates a new band reading data line by line from the command line
     * can be interrupted with commands 'stop' and 'Exit'
     * @return {@link MusicBand}
     */
    public MusicBand createBand() {
        boolean creatingInProcess = true;
        System.out.println("Creating of new element is in process..." + "\n" +
                "Enter 'stop' to interrupt creating" + "\n" +
                "Enter 'exit' to exit the app");
        Scanner keyboard = new Scanner(System.in);
        String line;
        MusicBand element;
        do{
            element = new MusicBand("");
        } while (idList.contains(element.getId()));
        //Ввод имени
        System.out.println("Enter bands name");
        while (creatingInProcess) {
            line = keyboard.nextLine();
            if (line.equals("exit")) {
                Exit e = new Exit();
                e.Execute(this);
                creatingInProcess = false;
                break;
            }
            if (line.equals("stop")) {
                creatingInProcess = false;
                break;
            }
            if (line == null || line == "") {
                System.out.println("Bands name cant be null or empty string, enter other name, please");
            } else {
                element.setName(line);
                break;
            }
        }
        //Ввод координат
        if (creatingInProcess) {
            System.out.println("Enter x and y coordinates, separating them with space" + "\n" +
                    "x must be lower than 241, y lower than -579, separating sign - dot");
        }
        while (creatingInProcess) {
            line = keyboard.nextLine();
            if (line.equals("exit")) {
                Exit e = new Exit();
                e.Execute(this);
                creatingInProcess = false;
                break;
            }
            if (line.equals("stop")) {
                creatingInProcess = false;
                break;
            }
            if (line == null || line == "") {
                System.out.println("Coordinates cant be null or empty string, enter other coordinates, please");
            } else {
                String[] coordinates = line.split(" ");
                try {
                    Double x = Double.valueOf(coordinates[0]);
                    float y = (float) Float.valueOf(coordinates[1]);
                    if (x > 241 || y > -579) {
                        System.out.println("The coordinates do not meet the required range, enter other coordinates, please");
                    } else {
                        element.setCoordinates(new Coordinates(x, y));
                        System.out.println(element.getCoordinates().toString());
                        break;
                    }
                } catch (RuntimeException e) {
                    System.out.println("The coordinates do not meet the required format, enter other coordinates, please");
                }
            }
        }
        //Ввод количества учатсников
        if (creatingInProcess) {
            System.out.println("Enter number of participants of band");
        }
        while (creatingInProcess) {
            line = keyboard.nextLine();
            if (line.equals("exit")) {
                Exit e = new Exit();
                e.Execute(this);
                creatingInProcess = false;
                break;
            }
            if (line.equals("stop")) {
                creatingInProcess = false;
                break;
            }
            try {
                if (line == null) {
                    element.setNumberOfParticipants(null);
                    break;
                }
                Long count = Long.valueOf(line);
                if (count > 0) {
                    element.setNumberOfParticipants(count);
                    break;
                } else {
                    System.out.println("Number of participants must be positive number, enter other number, please");
                }
            } catch (RuntimeException e) {
                System.out.println("The number of participants do not meet the required format, enter other coordinates, please");
            }
        }
        //Ввод количества синглов
        if (creatingInProcess) {
            System.out.println("Enter singles count");
        }
        while (creatingInProcess) {
            line = keyboard.nextLine();
            if (line.equals("exit")) {
                Exit e = new Exit();
                e.Execute(this);
                creatingInProcess = false;
                break;
            }
            if (line.equals("stop")) {
                creatingInProcess = false;
                break;
            }
            if (line == null || line == "") {
                System.out.println("Singles count cant be null or empty string, enter other name, please");
            } else {
                try {
                    long count = Long.valueOf(line);
                    if (count > 0) {
                        element.setSinglesCount(count);
                        break;
                    } else {
                        System.out.println("Singles count must be positive number, enter other number, please");
                    }
                } catch (RuntimeException e) {
                    System.out.println("Singles count do not meet the required format, enter other coordinates, please");
                }
            }
        }
        //Ввод количства альбомов
        if (creatingInProcess) {
            System.out.println("Enter albums count");
        }
        while (creatingInProcess) {
            line = keyboard.nextLine();
            if (line.equals("exit")) {
                Exit e = new Exit();
                e.Execute(this);
                creatingInProcess = false;
                break;
            }
            if (line.equals("stop")) {
                creatingInProcess = false;
                break;
            }
            try {
                if (line == null) {
                    element.setAlbumsCount(null);
                    break;
                }
                Long count = Long.valueOf(line);
                if (count > 0) {
                    element.setAlbumsCount(count);
                    break;
                } else {
                    System.out.println("Albums count must be positive number, enter other number, please");
                }
            } catch (RuntimeException e) {
                System.out.println("The entered quantity does not meet the required format, please enter a different value");
            }
        }
        //Ввод музыкального жанра
        if (creatingInProcess) {
            System.out.println("Enter one of the suggested musical genres: rock, progressive rock, post rock, punk rock, post punk, null");
        }
        HashSet<String> genres = new HashSet<>(List.of(new String[]{"rock", "progressive rock", "post rock", "punk rock", "post punk"}));
        while (creatingInProcess) {
            line = keyboard.nextLine();
            if (line.equals("exit")) {
                Exit e = new Exit();
                e.Execute(this);
                creatingInProcess = false;
                break;
            }
            if (line.equals("stop")) {
                creatingInProcess = false;
                break;
            }
            if (line == null) {
                element.setGenre(null);
                break;
            } else if (genres.contains(line)) {
                switch (line) {
                    case ("rock") -> element.setGenre(MusicGenre.ROCK);
                    case ("progressive rock") -> element.setGenre(MusicGenre.PROGRESSIVE_ROCK);
                    case ("post rock") -> element.setGenre(MusicGenre.POST_ROCK);
                    case ("punk rock") -> element.setGenre(MusicGenre.PUNK_ROCK);
                    case ("post punk") -> element.setGenre(MusicGenre.POST_PUNK);
                }
                break;
            } else {
                System.out.println("Entered genre not found");
            }
        }
        //Ввод любимого альбома
        if (creatingInProcess) {
            System.out.println("Enter best albums name");
        }
        String name = null;
        float sales = 0;
        while (creatingInProcess) {
            line = keyboard.nextLine();
            if (line.equals("exit")) {
                Exit e = new Exit();
                e.Execute(this);
                creatingInProcess = false;
                break;
            }
            if (line.equals("stop")) {
                creatingInProcess = false;
                break;
            }
            if (line == null || line == "") {
                System.out.println("Albums name cant be null or empty string, enter other name, please");
            } else {
                name = line;
                break;
            }
        }
        if (creatingInProcess) {
            System.out.println("Enter best albums sales count");
        }
        while (creatingInProcess) {
            line = keyboard.nextLine();
            if (line.equals("exit")) {
                Exit e = new Exit();
                e.Execute(this);
                creatingInProcess = false;
                break;
            }
            if (line.equals("stop")) {
                creatingInProcess = false;
                break;
            }
            try {
                float count = Float.valueOf(line);
                if (count > 0) {
                    sales = count;
                    break;
                } else {
                    System.out.println("Best albums sales count must be positive number, enter other number, please");
                }
            } catch (RuntimeException e) {
                System.out.println("The entered quantity does not meet the required format, please enter a different value");
            }
        }
        if (creatingInProcess) {
            element.setBestAlbum(new Album(name, sales));
            return element;
        }
        return null;
    }
}
