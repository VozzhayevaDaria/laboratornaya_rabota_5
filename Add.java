import CollectionStrusture.MusicBand;

import java.util.Scanner;

/**
 * The inheritor class of the {@link Command} that implements the 'add' command
 */
public class Add extends Command {
    /**
     * Constructor initializing the 'add' command
     * @param app
     */
    public void Execute(App app){
        MusicBand element = app.createBand();
        if (element != null){
            System.out.println("A new instance has been successfully created, here's how it looks:");
            System.out.println(element.toString());
            System.out.println("Add an item to a collection? (Enter Yes/No)");
            Scanner keyboard = new Scanner(System.in);
            String line;
            while (true) {
                line = keyboard.nextLine();
                if (line.equals("Exit")) {
                    Exit e = new Exit();
                    e.Execute(app);
                }
                if (line.equals("stop")) {
                    break;
                } else if (line.equals("Yes")) {
                    app.getCollection().add(element);
                    System.out.println("The item has been added to the collection");
                    break;
                } else if (line.equals("No")) {
                    System.out.println("The element is not added, the collection remains the same");
                    break;
                } else {
                    System.out.println("The command is not recognized: enter Yes or No");
                }
            }
    }

    }
}

