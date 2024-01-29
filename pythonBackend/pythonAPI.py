from flask import Flask, request
from python import testModule

app = Flask(__name__)

@app.route('/foo')
def foo():
    return testModule.foo()

@app.route('/add', methods=['POST'])
def add():
    json = request.get_json()
    sum = 0
    for elem in json['array']:
        sum += float(elem)
    return str(sum)

#@app.route('/optimiseReq')
#def add():
#    return testModule.optimiseReq()

if __name__ == '__main__':
    app.run()