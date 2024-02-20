"""
This is a API module that emulates the simulations that are used to optimise the components.
"""

from flask import Flask, request
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

if __name__ == '__main__':
    app.run(host="0.0.0.0")
