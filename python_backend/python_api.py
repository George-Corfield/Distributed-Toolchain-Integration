"""
This is a API module that emulates the simulations that are used to optimise the components.
"""

import json
import os
from flask import Flask, request, jsonify
from modules import module

app = Flask(__name__)

@app.route('/foo')
def test_string():
    """This function returns the string 'test String'."""

    return module.string(), 200

@app.route('/add', methods=['POST'])
def add():
    """This function takes a JSON file and sums the values in the array in the file."""

    json_data = request.get_json()
    return str(module.add(json_data['array'])), 200

@app.route('/optimise', methods=['POST'])
def optimise():
    """Takes the JSON file and the Module and runs the module with the JSON file as input"""

    files = request.files.getlist('file')

    if len(files) != 2:
        return jsonify("Incorrect number of files"), 400
    python_file = files.pop()
    json_file = files.pop()

    python_file.save('python_file2.py')
    json_file.save('json_file2.json')

    try:
        #The module will need to be run here
        #at the moment, it does not run the module, instead it calculates the mean
        #of the bounds and returns that
        with open('json_file2.json', 'r', encoding="utf-8") as json_data:
            val = json.load(json_data)
            value = val[0]
            return jsonify((value['upBound'] + value['lowBound'])/2), 200
    except FileNotFoundError:
        return jsonify("File not found"), 404
    except PermissionError:
        return jsonify("Permission denied"), 403
    finally:
        if os.path.exists("json_file2.json"):
            os.remove("json_file2.json")
        if os.path.exists("python_file2.py"):
            os.remove("python_file2.py")
if __name__ == '__main__':
    app.run(host="0.0.0.0", debug=True)
