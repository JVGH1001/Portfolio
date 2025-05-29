from random import randint
print('Welcome to the higher/lower game, Bella!')
lower = int(input('Enter the lower bound:\n'))
upper = int(input('Enter the upper bound:\n'))

if lower >= upper:
    print('lower bound must be less than upper bound\n')
    lower = int(input('Enter the lower bound:\n'))
    upper = int(input('Enter the upper bound:\n'))


guess = int(input('Great, now guess a number between {} and {}'.format(lower, upper)))
x = randint(lower, upper)


while guess != x:
    if guess < lower or guess > upper:
        guess = int(input('choose a number between {} and {}'.format(lower, upper)))
    elif guess > x:
        print('Nope, too high.')
        guess = int(input('Guess another number: '))
    elif guess < x:
        print('Nope, too low.')
        guess = int(input('Guess another number: '))

if guess == x:
    print('You got it!')

