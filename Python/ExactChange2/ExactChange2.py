#  Breaks change into their respective denominations
def exact_change(user_total):
    dollars = int(user_total / 100)
    user_total %= 100
    quarters = int(user_total / 25)
    user_total %= 25
    dimes = int(user_total / 10)
    user_total %= 10
    nickels = int(user_total / 5)
    user_total %= 5
    pennies = int(user_total)
    return dollars, quarters, dimes, nickels, pennies


if __name__ == '__main__':
    input_val = int(input())
    num_dollars, num_quarters, num_dimes, num_nickels, num_pennies = exact_change(input_val)

    total = {'dollars': num_dollars, 'quarters': num_quarters, 'dimes': num_dimes, 'nickels': num_nickels,
             'pennies': num_pennies}
    change = total

    if input_val == 0:
        print('no change')

    #  This for loop chooses when to use plurals and prints out the totals
    for x in change:
        if change[x] == 0:
            pass
        if change[x] == 1:
            print('{} {}'.format(change[x], x.replace('s', '').replace('pennie', 'penny')))
        if change[x] > 1:
            print('{} {}'.format(change[x], x))
