#I am a fake module
import sys
import json

def execute(json_input):
    jsons = json.load(json_input)
    array = jsons['array']
    print(sum(array))

if __name__ == "__main__":
    execute(sys.argv[1])