"""
This is a API module that emulates the simulations that are used to optimise the components.
"""

from flask import Flask, request

app = Flask(__name__)

@app.route('/foo')
def test_string():
    """This function returns the string 'test String'."""

    return "test String"

@app.route('/add', methods=['POST'])
def add():
    """This function takes a JSON file and sums the values in the array in the file."""

    json = request.get_json()
    value = 0
    for elem in json['array']:
        value += float(elem)
    return str(value)

if __name__ == '__main__':
    app.run(host="0.0.0.0")
