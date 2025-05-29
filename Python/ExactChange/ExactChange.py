change = int(input())

dollars = 0
quarters = 0
dimes = 0
nickels = 0
pennies = 0


#  Checks input change to see if there is any.
if int(change) <= 0:
    print('No Change')

#  Checks for how many dollars will be needed.
if int(change) >= 100:
    dollars = int(change / 100)
    change = change % 100

#  Breaks change into the smaller denominations and assigns to correct identifier, ex. Quarter, Dime, etc.
if int(change) < 100:
    quarters = int(change / 25)
    change = change % 25
    dimes = int(change / 10)
    change = change % 10
    nickels = int(change / 5)
    change = change % 5
    pennies = int(change)

#  This block prints out the total change based upon the value assigned to the different change identifiers.
if dollars == 1:
    print(dollars, 'Dollar')
elif dollars > 1:
    print(dollars, 'Dollars')
if quarters == 1:
    print(quarters, 'Quarter')
elif quarters > 1:
    print(quarters, 'Quarters')
if dimes == 1:
    print(dimes, 'Dime')
elif dimes > 1:
    print(dimes, 'Dimes')
if nickels == 1:
    print(nickels, 'Nickel')
elif nickels > 1:
    print(nickels, 'Nickels')
if pennies == 1:
    print(pennies, 'Penny')
elif pennies > 1:
    print(pennies, 'Pennies')
