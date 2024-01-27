from flask import Flask, request
from python import testModule

app = Flask(__name__)

@app.route('/foo')
def foo():
    return testModule.foo()

@app.route('/add')
def add():
    num1 = float(request.args.get('num1'))
    return testModule.add([num1])

#@app.route('/optimiseReq')
#def add():
#    return testModule.optimiseReq()

if __name__ == '__main__':
    app.run()