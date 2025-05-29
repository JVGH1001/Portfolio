public enum KivaCommand {
    
    FORWARD('F'), 
    TURN_LEFT('L'),
    TURN_RIGHT('R'),
    TAKE('T'),
    DROP('D');
    
    char Command;
    
    KivaCommand(char Command)
    {
        this.Command = Command;
    }
    
    
    public char getDirectionKey()
    {
        return Command;
    }
    

}
