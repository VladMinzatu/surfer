from bottle import route, get, post, run, static_file, request
import json
import socket

scores = {}

HOST, PORT = "localhost", 9999
socket_server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
socket_server.bind((HOST, PORT))
socket_server.listen(10)

print('Waiting for connection from Spark Streaming...')
conn, addr = socket_server.accept()
print('Got a connection. Ready to receive requests')

@get('/')
def index():
    return static_file('index.html', root='resources')

@route('/static/app/<filename>')
def get_static(filename):
    return static_file(filename, root='resources/app')

@get('/items')
def get_scores():
    items = [{'item':x, 'score':scores[x]} for x in scores]
    return json.dumps(sorted(items, key=lambda x: -x['score']))

@post('/items')
def update_snapshot():
    global scores
    new_scores = json.loads(str(request.body.getvalue().decode("utf-8")))
    for x in new_scores:
        scores[x['item']] = x['score']

@post('/items/<name>')
def interact(name):
    conn.send(bytes(name + '\n','utf-8'))

run(host='localhost', port=8080)
