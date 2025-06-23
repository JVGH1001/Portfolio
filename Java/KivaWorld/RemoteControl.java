import edu.duke.FileResource;
import java.util.Arrays; 


/**
 * This is the class that controls Kiva's actions. Implement the <code>run()</code>
 * method to deliver the pod and avoid the obstacles.
 */
public class RemoteControl {
    KeyboardResource keyboardResource;

    /**
     * Build a new RemoteControl.
     */
    public RemoteControl() {
        keyboardResource = new KeyboardResource();
    }

    /**
     * The controller that directs Kiva's activity. Prompts the user for the floor map
     * to load, displays the map, and asks the user for the commands for Kiva to execute.
     */
    public void run() {
        System.out.println("Please select a map file.");
        FileResource fileResource = new FileResource();
        String inputMap = fileResource.asString();
        FloorMap floorMap = new FloorMap(inputMap);
        System.out.println(floorMap);
        Kiva kiva = new Kiva(floorMap);
        System.out.println("Kiva's starting location is: "+ floorMap.getInitialKivaLocation());
        System.out.println("Kiva's direction is pointing: " + kiva.getDirectionFacing());
        System.out.println("Please enter the directions for the Kiva Robot to take.");
        System.out.println("Valid commands are: F, L, R, T, D");
        String directions = keyboardResource.getLine();
        System.out.println("Directions that you typed in: " + directions);
        KivaCommand[] commands = convertToKivaCommands(directions);
        for(int i=0; i<commands.length; i++){
            KivaCommand send = commands[i];
            kiva.move(send);
        }
        
        if(commands[commands.length -1] != KivaCommand.DROP){
            System.out.println("I'm sorry. The Kiva Robot did not pick up the pod and then drop it off in the right place");
        }
        else if(kiva.isSuccessfullyDropped() == true){
           System.out.println("Successfully picked up the pod and dropped it off. Thank you!");
        }
        else{
            System.out.println("I'm sorry. The Kiva Robot did not pick up the pod and then drop it off in the right place");
        }
    }
    
    private static KivaCommand[] convertToKivaCommands(String userCommand){

        char[] input = userCommand.toCharArray();
        KivaCommand[] command = KivaCommand.values();
        KivaCommand[] Final = new KivaCommand[input.length]; 
        
        //iterates over the input array and checks chars against command array character values
        for(int n=0; n < input.length; n++){       
            if(input[n] == command[0].getDirectionKey()){
                Final[n] = command[0];
            }
            
            else if(input[n] == command[1].getDirectionKey()){
                Final[n] = command[1];
            }
            
            else if(input[n] == command[2].getDirectionKey()){
                Final[n] = command[2];
            }
            
            else if(input[n] == command[3].getDirectionKey()){
                Final[n] = command[3];
            }
            else if(input[n] == command[4].getDirectionKey()){
                Final[n] = command[4];
            }
            else{
                throw new IllegalArgumentException(String.format("You entered an invalid command %s", input[n]));
            }
        } 
        return Final; //array of KivaCommands as FORWARD, FORWARD, RIGHT, etc..
    }
}
