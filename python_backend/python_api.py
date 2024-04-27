"""
This is a API module that emulates the simulations that are used to optimise the components.
"""

from flask import Flask, request, jsonify
from modules import module
import subprocess

app = Flask(__name__)

@app.route('/foo')
def test_string():
    """This function returns the string 'test String'."""

    return module.string(), 200

@app.route('/add', methods=['POST'])
def add():
    """This function takes a JSON file and sums the values in the array in the file."""

    json = request.get_json()
    return str(module.add(json['array'])), 200

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
        result = subprocess.run(["python", 'python_file2.py', 'json_file2.json'], 
                                check=True,
                                stdout=subprocess.PIPE,
                                stderr=subprocess.PIPE,
                                text=True)
        return jsonify(result.stdout), 200
    except:
        return jsonify("Error executing the module"), 500 

if __name__ == '__main__':
    app.run(host="0.0.0.0", debug=True)
