from bottle import get, post, run, static_file, request
import json
import socket

scores = [{'item':'a','score':0.69}, {'item':'b','score':0.81}, {'item':'c', 'score':0.73}]

HOST, PORT = "localhost", 9999
socket_server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
socket_server.bind((HOST, PORT))
socket_server.listen(10)
print('Waiting for connection from Spark Streaming...')
conn, addr = socket_server.accept()

@get('/')
def index():
    return static_file('index.html', root='resources')

@get('/items')
def get_scores():
    return json.dumps(sorted(scores, key=lambda x: -x['score']))

@post('/items')
def update_snapshot():
    global scores
    new_scores = json.loads(str(request.body.getvalue().decode("utf-8")))
    scores = [{"item":x['item'], "score":x['score']} for x in new_scores]

@post('/items/<name>')
def interact(name):
    conn.send(bytes(name + '\n','utf-8'))

run(host='localhost', port=8080)
