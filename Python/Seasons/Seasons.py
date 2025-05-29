input_month = input()
input_day = int(input())

months = ('January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November',
          'December')

#  This block checks the input for validity. (Is it a valid month and day?)
if not(input_month in months):
    print('Invalid')
elif not(0 < input_day < 31):
    print('Invalid')

# These lines (15-60) check input_month to see which month it is.
# Once the month is found then the day is checked for validity.
# March, June, September & December have nested if/else to choose correct season.
elif input_month == 'March':

    if input_day >= 20:
        print('Spring')
    else:
        print('Winter')


elif input_month == 'April' and input_day <= 30:
    print('Spring')

elif input_month == 'May' and input_day <= 31:
    print('Spring')

elif input_month == 'June':
    if input_day <= 20:
        print('Spring')
    else:
        print('Summer')

# July and August were combined since they are in the same season with 31 days each.
elif (input_month == 'July' or 'August') and input_day >= 31:
    print('Summer')

elif input_month == 'September':
    if input_day <= 22:
        print('Summer')
    else:
        print('Autumn')

elif input_month == 'October' and input_day <= 31:
    print('Autumn')

elif input_month == 'November' and input_day <= 30:
    print('Autumn')

elif input_month == 'December':
    if input_day <= 21:
        print('Autumn')
    else:
        print('Winter')

elif input_month == 'January' and input_day <= 31:
    print('Winter')

elif input_month == 'February' and input_day <= 29:
    print('Winter')
