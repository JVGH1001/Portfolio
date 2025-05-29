
/**
 * This class tests the functionality of incrementMotorLifetime
 */
public class KivaMotorLifetimeTester {
    
    
    String defaultLayout = ""
                           + "-----*\n"
                           + "|K D|*\n"
                           + "| P |*\n"
                           + "|* *|*\n"
                           + "-----*\n";
                           
                           
    FloorMap defaultMap = new FloorMap(defaultLayout);
    
    public void testMotorLifetimeIncrement(){
        Kiva kiva = new Kiva(defaultMap);
        System.out.println(kiva.getMotorLifetime());
        kiva.move(KivaCommand.TURN_RIGHT);
        System.out.println(kiva.getMotorLifetime());
        kiva.move(KivaCommand.FORWARD);
        System.out.println(kiva.getMotorLifetime());
        kiva.move(KivaCommand.TURN_RIGHT);
        System.out.println(kiva.getMotorLifetime());
        kiva.move(KivaCommand.FORWARD);
        System.out.println(kiva.getMotorLifetime());
        kiva.move(KivaCommand.TAKE);
        System.out.println(kiva.getMotorLifetime());
        
    }


}
