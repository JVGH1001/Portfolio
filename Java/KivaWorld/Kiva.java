import edu.duke.Point;

/**
 * Written by: Jeffrey VanMeter
 * 
 * This class represents a Kiva robot with functions such as moving, turning, taking, dropping
 * The Kiva also tracks it's own location, direction facing & lifetime
 * 
 */
   public class Kiva {

       private Point currentLocation;
       private FacingDirection directionFacing;
       private FloorMap map;
       private boolean carryingPod;
       private boolean successfullyDropped;
       private int motorLifetime;

       /**
        * constructor to instantiate a new kiva
        * @param the chosen floor map get passed to the kiva to initialize it's location
        */
       public Kiva(FloorMap map){
           this.map = map;
           currentLocation = map.getInitialKivaLocation();
           directionFacing = FacingDirection.UP;
           carryingPod = false;
           successfullyDropped = false;
       }

       /**
        * also constructor to instantiate a new kiva
        * @param the chosen floor map get passed to the kiva, 
        * the second parameter instantiates the kiva's starting location from a point 
        */
       public Kiva(FloorMap map, Point currentLocation){
          this.map = map;
          this.currentLocation = currentLocation;
          directionFacing = FacingDirection.UP;
          carryingPod = false;
          successfullyDropped = false;

       }

       /**
        * This method chooses the correct command for the kiva to use
        */
       public void move(KivaCommand Command){
           if(Command == KivaCommand.FORWARD){
               moveForward();
           }
                        
           else if(Command == KivaCommand.TURN_LEFT){
               turnLeft();
           }
                        
           else if(Command == KivaCommand.TURN_RIGHT){
               turnRight();
           }
                        
           else if(Command == KivaCommand.TAKE){
               take();
           }
                        
           else if(Command == KivaCommand.DROP){
               drop();
           }
       }
                    
       /**
        * This method moves the kiva forward based up what direction it is facing while 
        * checking for obstacles like walls or pods. Also increments MotorLifetime
        */
       private void moveForward(){
                      
           if (directionFacing == FacingDirection.UP){
               Point next = new Point (currentLocation.getX(), (currentLocation.getY() -1));
                  if (next.getX() <= 0 || next.getY() <= 0){
                     throw new IllegalMoveException(String.format("Cannot move off the map! Either X or Y is < 0.  CurrentLocation: %s, Next location would be: %s", currentLocation, next));
                  }
                                    
                  else if(next.getX() >= map.getMaxColNum() || next.getY() >= map.getMaxRowNum()){
                     throw new IllegalMoveException(String.format("Cannot move off the map! Either X or Y is > maximum # of columns or rows. CurrentLocation: %s, Next Location would be: %s", currentLocation, next));
                  }
                  else if(map.getObjectAtLocation(next) == FloorMapObject.OBSTACLE){
                     throw new IllegalMoveException(String.format("Cannot move into an obstacle! CurrentLocation: %s, Obstacle at: %S", currentLocation, next));
                  }
                  else if(carryingPod == true && map.getObjectAtLocation(next) == FloorMapObject.POD){
                     throw new IllegalMoveException(String.format("Cannot move into a Pod while carrying a Pod! Pod location %s", next));
                  }
               currentLocation = next;
               this.incrementMotorLifetime();
           }
                                
           else if (directionFacing == FacingDirection.RIGHT){
               Point next = new Point ((currentLocation.getX() +1), currentLocation.getY());
                  if (next.getX() <= 0 || next.getY() <= 0){
                     throw new IllegalMoveException(String.format("Cannot move off the map! Either X or Y is < 0.  CurrentLocation: %s, Next location would be: %s", currentLocation, next));
                  }
                                    
                  else if(next.getX() >= map.getMaxColNum() || next.getY() >= map.getMaxRowNum()){
                     throw new IllegalMoveException(String.format("Cannot move off the map! Either X or Y is > maximum # of columns or rows. CurrentLocation: %s, Next Location would be: %s", currentLocation, next));
                  }
                  else if(map.getObjectAtLocation(next) == FloorMapObject.OBSTACLE){
                     throw new IllegalMoveException(String.format("Cannot move into an obstacle! CurrentLocation: %s, Obstacle at: %S", currentLocation, next));
                  }
                  else if(carryingPod == true && map.getObjectAtLocation(next) == FloorMapObject.POD){
                     throw new IllegalMoveException(String.format("Cannot move into a Pod while carrying a Pod! Pod location %s", next));
                  }
               currentLocation = next;
               this.incrementMotorLifetime();
           }
                                
               else if (directionFacing == FacingDirection.LEFT){
                  Point next = new Point ((currentLocation.getX() -1), currentLocation.getY()); 
                     if (next.getX() <= 0 || next.getY() <= 0){
                        throw new IllegalMoveException(String.format("Cannot move off the map! Either X or Y is < 0.  CurrentLocation: %s, Next location would be: %s", currentLocation, next));
                     }
                                    
                     else if(next.getX() >= map.getMaxColNum() || next.getY() >= map.getMaxRowNum()){
                        throw new IllegalMoveException(String.format("Cannot move off the map! Either X or Y is > maximum # of columns or rows. CurrentLocation: %s, Next Location would be: %s", currentLocation, next));
                     }
                     else if(map.getObjectAtLocation(next) == FloorMapObject.OBSTACLE){
                        throw new IllegalMoveException(String.format("Cannot move into an obstacle! CurrentLocation: %s, Obstacle at: %S", currentLocation, next));
                     }
                     else if(carryingPod == true && map.getObjectAtLocation(next) == FloorMapObject.POD){
                        throw new IllegalMoveException(String.format("Cannot move into a Pod while carrying a Pod! Pod location %s", next));
                     }
                  currentLocation = next;
                  this.incrementMotorLifetime();
           }
                                
                  else if (directionFacing == FacingDirection.DOWN){
                     Point next = new Point (currentLocation.getX(), (currentLocation.getY() +1));
                        if (next.getX() <= 0 || next.getY() <= 0){
                           throw new IllegalMoveException(String.format("Cannot move off the map! Either X or Y is < 0.  CurrentLocation: %s, Next location would be: %s", currentLocation, next));
                        }
                                   
                        else if(next.getX() >= map.getMaxColNum() || next.getY() >= map.getMaxRowNum()){
                           throw new IllegalMoveException(String.format("Cannot move off the map! Either X or Y is > maximum # of columns or rows. CurrentLocation: %s, Next Location would be: %s", currentLocation, next));
                        }
                        else if(map.getObjectAtLocation(next) == FloorMapObject.OBSTACLE){
                           throw new IllegalMoveException(String.format("Cannot move into an obstacle! CurrentLocation: %s, Obstacle at: %S", currentLocation, next));
                        }
                        else if(carryingPod == true && map.getObjectAtLocation(next) == FloorMapObject.POD){
                           throw new IllegalMoveException(String.format("Cannot move into a Pod while carrying a Pod! Pod location %s", next));
                        }
                     currentLocation = next;
                     this.incrementMotorLifetime();
           }
                                
       }
       
       /**
        * This method turns the kiva left based upon the direction it is currently facing
        * Also increments MotorLifetime
        */
       private void turnLeft(){
          if (directionFacing == FacingDirection.UP){
             directionFacing = FacingDirection.LEFT;
             this.incrementMotorLifetime();
          }
                                      
          else if (directionFacing == FacingDirection.RIGHT){
             directionFacing = FacingDirection.UP;
             this.incrementMotorLifetime();
          }
                                       
          else if (directionFacing == FacingDirection.LEFT){
             directionFacing = FacingDirection.DOWN;
             this.incrementMotorLifetime();
          }
                                       
          else if (directionFacing == FacingDirection.DOWN){
             directionFacing = FacingDirection.RIGHT;
             this.incrementMotorLifetime();
          }
       }
                                    
       /**
        * This method turns the kiva right based upon the direction it is currently facing
        * Also increments MotorLifetime
        */
       private void turnRight(){
          if (directionFacing == FacingDirection.UP){               
             directionFacing = FacingDirection.RIGHT;
             this.incrementMotorLifetime();
          }
                     
          else if (directionFacing == FacingDirection.RIGHT){
             directionFacing = FacingDirection.DOWN;
             this.incrementMotorLifetime();
          }
                      
          else if (directionFacing == FacingDirection.LEFT){
             directionFacing = FacingDirection.UP;
             this.incrementMotorLifetime();
          }
                                                
          else if (directionFacing == FacingDirection.DOWN){
             directionFacing = FacingDirection.LEFT;
             this.incrementMotorLifetime();
          }
       }
                                            
       /**
        * This method takes a pod, but only if Kiva doesn't currently have a pod & Kiva is at a pod location
        */
       private void take(){
          if (carryingPod == true){
             throw new InvalidKivaCommandException("Cannot take pod while currently carrying a pod!");
          }
          else if(map.getObjectAtLocation(this.getCurrentLocation()) == FloorMapObject.POD){
             carryingPod = true;                                             
          }
          else{
              throw new NoPodException(String.format("There is no Pod to take! CurrentLocation: %s, PodLocation: %s", currentLocation, map.getPodLocation()));
          }
       }
                      
       /**
        * This method drops a pod, but only if Kiva is carrying a pod & Kiva's location is a drop zone location
        */
       private void drop(){
          if (carryingPod == false){
             throw new NoPodException("Cannot drop without carrying a pod!");
          }
          else if(map.getObjectAtLocation(this.getCurrentLocation()) == FloorMapObject.DROP_ZONE){
             successfullyDropped = true;
             carryingPod = false;
          }
          else{
             throw new IllegalDropZoneException(String.format("There is no Drop Zone to drop! CurrentLocation: %s, DropZoneLocation: %s", currentLocation, map.getDropZoneLocation()));
          }
       }
                                                    
       /**
        * MotorLifetime gets incremented by 1000 which = 1 second
        */
       private void incrementMotorLifetime(){
         motorLifetime = motorLifetime + 1000;
       }

       /**
        * @returns kiva's current location in (x,y) coordinates
        */
       public Point getCurrentLocation(){
          return currentLocation;
       }

       /**
        * @returns kiva's current direction facing
        */
       public FacingDirection getDirectionFacing(){
          return directionFacing;
       }

       /**
        * @returns current motor lifetime value
        */
       public int getMotorLifetime(){
          return motorLifetime;
       }

       /**
        * @returns if kiva is carrying a pod or not
        */
       public boolean isCarryingPod(){       
          return carryingPod;
       }

       /**
        * @returns if Kiva successfully dropped a pod or not
        */
       public boolean isSuccessfullyDropped(){
          return successfullyDropped;
       }

}

