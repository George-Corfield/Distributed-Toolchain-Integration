#This works as follows:
#From the ProcessBuilder, this program should be passes the function as the first argument
#and then all of the arguments to the function after that.
#then the main function will be called and it will run the function and print the output. 
#I need to stress this, YOU CANNOT USE PRINT OTHER THAN FOR OUTPUT.
#If you do, whatever you print will be sent back to the process builder.

#Here are example commands:

# To run from the terminal:
# py src\main\python\test.py foo

# To run using the PythonCaller class:
# String res = PythonCaller.call("src\\main\\python\\test.py");

import sys

def main(args):
    match args[1]:
        case "foo":
            print(foo())
        case "add":
            print(add(args[2:]))
        case _:
            print("Error: Function not found.")


def add(args):
    sum = 0
    for num in args:
        sum += int(num)
    return sum

def foo():
    return "Test String"

if __name__ == "__main__":
    main(sys.argv)

