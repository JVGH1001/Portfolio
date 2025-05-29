##############################################################################
# @author Jeffrey VanMeter
#############################################################################

import re
import string
productList = []
                            
#Creates and displays a list of items sold based on the input file
def createCount():
    file = open(".\Release\CS210_Project_Three_Input_File.txt") 
    products = file.read().split() 
    for product in products:
        if product not in productList:
            productList.append(product)
    print("\n##############################################################################")
    print("#                         Total Produce Purchased Today                      #")
    print("##############################################################################\n")
    for product in productList:
        print(product, products.count(product))
    print()
    file.close()

    
    #Allows the user to search for an item that was sold.
def itemSearch(search):
    file = open(".\Release\CS210_Project_Three_Input_File.txt") 
    products = file.read().split() 
    return products.count(search)
    file.close()


    #This creates the list of items sold and writes them to a file names frequency.dat
def createList():
    file = open(".\Release\CS210_Project_Three_Input_File.txt") 
    products = file.read().split() 
    for product in products:
        if product not in productList:
            productList.append(product)
    f = open('frequency.dat', 'w')
    for product in productList:
        f.write(str(product))
        f.write(' ')
        count = products.count(product)
        f.write(str(count))
        f.write('\n')
    f.close()
    file.close()