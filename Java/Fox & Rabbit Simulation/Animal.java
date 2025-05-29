import java.util.List;
import java.util.Random;
/**
 * A class representing shared characteristics of animals.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public abstract class Animal
{
    // Whether the animal is alive or not.
    private boolean alive;
    // The animal's field.
    private Field field;
    // The animal's position in the field.
    private Location location;
    
    private int age;
    
    private int age2;
    
    private static final int rabbitAge = 40;
    
    private static final int foxAge = 150;
    
    private static final int RABBIT_FOOD_VALUE = 9;
    
    private int foodLevel;
    
    private static final Random rand = Randomizer.getRandom();
    /**
     * Create a new animal at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Animal(boolean randomAge, boolean randomAge2, Field field, Location location)
    {
        alive = true;
        this.field = field;
        setLocation(location);
        age = 0;
        age2 = 0;
        
        if(randomAge) {
            age = rand.nextInt(rabbitAge);
        }
        
        if(randomAge2) {
            age2 = rand.nextInt(foxAge);
            foodLevel = rand.nextInt(RABBIT_FOOD_VALUE);
        }
        else {
            age2 = 0;
            foodLevel = RABBIT_FOOD_VALUE;
        }
    }
    
    protected int getFood()
    {
        return foodLevel;
    }
    
    protected int getRabbitFood()
    {
        return RABBIT_FOOD_VALUE;
    }
    
    /**
     * Make this animal act - that is: make it do
     * whatever it wants/needs to do.
     * @param newAnimals A list to receive newly born animals.
     */
    abstract public void act(List<Animal> newAnimals);

    /**
     * Check whether the animal is alive or not.
     * @return true if the animal is still alive.
     */
    protected boolean isAlive()
    {
        return alive;
    }

    /**
     * Indicate that the animal is no longer alive.
     * It is removed from the field.
     */
    protected void setDead()
    {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    /**
     * Return the animal's location.
     * @return The animal's location.
     */
    protected Location getLocation()
    {
        return location;
    }
    
    /**
     * Place the animal at the new location in the given field.
     * @param newLocation The animal's new location.
     */
    protected void setLocation(Location newLocation)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }
    
    /**
     * Return the animal's field.
     * @return The animal's field.
     */
    protected Field getField()
    {
        return field;
    }
    
    protected int getAge()
    {
        return age;
    }
    
    /**
     * Increase the age.
     * This could result in the rabbit's death.
     */
    protected void incrementRabbit()
    {
        age++;
        if(age > rabbitAge) {
            setDead();
        }
    }
    
    protected int getAge2()
    {
        return age2;
    }
    
    protected void incrementFox()
    {
        age2++;
        if(age2 > foxAge) {
            setDead();
        }
    }
}
