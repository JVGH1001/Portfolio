# Jeffrey VanMeter

# Shows instructions at the start
def show_instructions():
    print('''
Giant Frog Text Adventure
Collect 6 items to win the game, or be eaten by the frog!
Move commands: go South, go North, go East, go West
Add to Inventory: get 'item name'
    ''')


# This outputs the current room the player is in, the players inventory and if the room they entered has an item.
def player_stat(current_room, inventory):
    print('')
    if 'item' in rooms[current_room]:
        print('You see a:', rooms[current_room]['item'])
    print('-' * 25)
    print('Current Room: ', current_room)
    print('Inventory: ', '[%s]' % ', '.join(map(str, inventory)))
    print('-' * 25)


# This takes a valid input direction and moves the player in that direction
def new_room(the_room, direction):
    room = the_room
    for i in rooms:
        if i == room:
            if direction in rooms[room]:
                room = rooms[room][direction]
    return room


# A dictionary linking a room to other rooms
# and linking one item for each room except the Start room (Foyer) and the room containing the villain
rooms = {
    'Foyer': {'South': 'Trophy Room', 'North': 'Greenhouse', 'East': 'Kitchen', 'West': 'Patio'},
    'Trophy Room': {'North': 'Foyer', 'East': 'Garage', 'item': 'Rabbit Foot'},
    'Garage': {'West': 'Trophy Room', 'item': 'Bucket'},
    'Kitchen': {'West': 'Foyer', 'North': 'Pantry', 'item': 'Spoon'},
    'Pantry': {'South': 'Kitchen', 'item': 'Olive Oil'},
    'Greenhouse': {'South': 'Foyer', 'East': 'Garden', 'item': 'Jalapeno'},
    'Garden': {'West': 'Greenhouse', 'item': 'Poinsettia'},
    'Patio': {'East': 'Foyer', 'item': 'Frog'}  # Villain
}


# Main portion of the game
def main():
    # Starting conditions
    show_instructions()
    current_room = 'Foyer'
    inventory = []

    # Loops until the game is over or player types Exit
    while True:
        # Gives the player what room they are in along with their current inventory
        player_stat(current_room, inventory)
        user_input = input('Enter Command: ')

        # This if block allows the player to type Exit at anytime to quit the game.
        if user_input == 'Exit':
            print('Thanks for playing!')
            input('Press Enter to end')
            exit()

        # splits the input into a list
        tokens = user_input.split()

        # only runs when there is a list created from the user input
        if len(tokens) > 1:
            command = tokens[1].title()

            # Command invalid without get or go
            if tokens[0].lower() != 'get' and tokens[0].lower() != 'go':
                print('')
                print('Command not recognized. Please enter go South, go North, go East, go West, '
                      'get \'item name\' or Exit')

            # invalid get when there are no items in the room
            elif tokens[0].lower() == 'get' and 'item' not in rooms[current_room]:
                print('')
                print('There are no items here!')

            # valid get - add to inv
            elif tokens[0].lower() == 'get' and command in rooms[current_room]['item']:
                inventory.append(rooms[current_room]['item'])
                print('')
                print('You added the {} to your inventory!'.format(rooms[current_room]['item']))
                del rooms[current_room]['item']

            # Invalid get when item is in room
            elif tokens[0].lower() == 'get' and command not in rooms[current_room]:
                print('')
                print('get {} is not valid. Try get {}.'.format(command, rooms[current_room]['item']))

            # Invalid move command
            elif tokens[0].lower() == 'go' and command not in rooms[current_room]:
                print('')
                print('go {} is not a valid command for the room you are currently in.'.format(tokens[1]))

            # conditional (LOSE) for when you enter the villain's room without all the needed items
            elif tokens[0].lower() == 'go' and command == 'West' and current_room == 'Foyer' and len(inventory) < 6:
                print('')
                print('The frog ate you too!')
                print('You lose!')
                print('Thank you for playing!')
                input('Press Enter to end')
                exit()

            # Conditional (WIN) for when you enter the villain's room with the needed items
            elif tokens[0].lower() == 'go' and command == 'West' and current_room == 'Foyer' and len(inventory) == 6:
                print('')
                print('You got your Dog back!')
                print('You Win!')
                print('Thank you for playing!')
                input('Press Enter to end')
                exit()
            # this is what calls new_room() when valid
            elif tokens[0].lower() == 'go' and command in rooms[current_room]:
                current_room = new_room(current_room, command)

        # Runs if the input is one word and not valid
        else:
            print('')
            print('Command not recognized. Please enter go South, go North, go East, go West, '
                  'get \'item name\' or Exit')


main()
