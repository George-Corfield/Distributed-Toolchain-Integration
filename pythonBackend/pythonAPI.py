from flask import Flask, request

app = Flask(__name__)

@app.route('/foo')
def foo():
    return "test String"

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
    app.run(host="0.0.0.0")