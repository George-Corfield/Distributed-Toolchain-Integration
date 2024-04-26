"""
This is a API module that emulates the simulations that are used to optimise the components.
"""

from flask import Flask, request, jsonify
from modules import module

app = Flask(__name__)

@app.route('/foo')
def test_string():
    """This function returns the string 'test String'."""

    return module.string()

@app.route('/add', methods=['POST'])
def add():
    """This function takes a JSON file and sums the values in the array in the file."""

    json = request.get_json()
    return str(module.add(json['array']))

@app.route('/optimise', methods=['POST'])
def optimise():
    """Takes the JSON file and the Module and runs the module with the JSON file as input"""

    files = request.files.getlist('file')
    print(request.files)

    if len(files) != 2:
        return jsonify("Incorrect number of files"), 400

    
    return jsonify("ggs"), 200

if __name__ == '__main__':
    app.run(host="0.0.0.0", debug=True)
