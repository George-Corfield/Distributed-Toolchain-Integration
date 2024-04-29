"""This is a fake module, mostly used to test sending module files in POST requests."""
import sys
import json

def execute(json_input):
    """ Executes the module """
    jsons = json.load(json_input)
    array = jsons['array']
    print(sum(array))

if __name__ == "__main__":
    execute(sys.argv[1])
