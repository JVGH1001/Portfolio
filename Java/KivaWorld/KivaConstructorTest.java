import edu.duke.Point; 

/**
 * 
 * This class tests both kiva Constructors and if the kiva is properly instantiated
 */
public class KivaConstructorTest { 
    String defaultLayout = "" 
                            + "-------------\n" 
                            + "        P   *\n"
                            + "   **       *\n"
                            + "   **       *\n"
                            + "  K       D *\n"
                            + " * * * * * **\n"
                            + "-------------\n";

    FloorMap defaultMap = new FloorMap(defaultLayout);

    public void testSingleArgumentConstructor() { 
        // GIVEN 
        // The default map we defined earlier 

        // WHEN 
        // We create a Kiva with the single-argument constructor         
        Kiva kiva = new Kiva(defaultMap); 

        // THEN 
        // The initial Kiva location is (2, 4) 
        Point initialLocation = kiva.getCurrentLocation();
        Point expectedLocation = new Point(2, 4);
        if (sameLocation(initialLocation, expectedLocation)) { 
            System.out.println("testSingleArgumentConstructor SUCCESS"); 

        } else { 
            System.out.println(String.format( "testSingleArgumentConstructor FAIL: %s != (2,4)!", initialLocation)); 
        } 
    } 

    private boolean sameLocation(Point a, Point b) { 
        return a.getX() == b.getX() && a.getY() == b.getY(); 
    }
    
    //Test: testTwoArgumentConstructor()
    //Input: Pass the constructor the default map and a location of (5, 6) as the arguments, then validate that the currentLocation of the Kiva is as expected.
    //Output: “testTwoArgumentConstructor SUCCESS” is printed to the console.
    
    public void testTwoArgumentConstructor(){
        Point kivaLocation = new Point(5,6);
        Kiva kiva = new Kiva(defaultMap, kivaLocation);
        
        Point initialLocation = kiva.getCurrentLocation();
        Point expectedLocation = new Point(5,6);
                
        if (sameLocation(initialLocation, expectedLocation)) { 

            System.out.println("testTwoArgumentConstructor SUCCESS"); 

        } else { 
            System.out.println(String.format( "testTwoArgumentConstructor FAIL: %s != (2,4)!", initialLocation)); 
        } 
        
    }

}