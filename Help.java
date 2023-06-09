/**
 * The inheritor class of the {@link Command} that implements the 'Help' command
 */
public class Help extends Command {
    /**
     * Constructor initializing the 'Help' command
     * @param app
     */
    public void Execute(App app){
        System.out.println("Help : output Help for available commands\n" +
            "Info : output to the standard output stream information about the collection (type, initialization date, number of elements, etc.)\n" +
            "show : output to the standard output stream all elements of the collection in a string representation\n" +
            "add : add a new element to the collection\n" +
            "update_by_id id : update the value of the element a collection whose id is equal to the specified\n" +
            "RemoveById id : remove an item from the collection by its id\n" +
            "Clear : Clear the collection\n" +
            "Save : Save the collection to a file\n" +
            "Script file_name : read and execute the Script from the specified file. The Script contains commands in the same form in which they are entered by the user in interactive mode.\n" +
            "Exit : terminate the program (without saving to a file)\n" +
            "add_if_min : add a new element to the collection if its value is less than that of the smallest element of this collection\n" +
            "remove_greater : remove from the collection all elements exceeding the specified\n" +
            "sort : sort the collection in natural order\n");
    }
}
