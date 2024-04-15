import socket
import threading
from datetime import datetime as dt
import time
from dateutil import parser
import datetime

def send_time(client):
    while True:
        val = datetime.timedelta(minutes=5)
        new_val = dt.now() - val
        client.send(str(new_val).encode())
        print("Recent Time Sent By Client ", client.getsockname())
        time.sleep(5)

def receive_time(client):
    while True:
        synch_time = parser.parse(client.recv(1024).decode())
        print(f"Synchronized Time at Client {client.getsockname()}: {str(synch_time)}")

def Client():
    host = '127.0.0.1'
    port = 8080

    client = socket.socket()
    client.connect((host, port))
    # start sending time to server
    thread1 = threading.Thread(target=send_time, args=(client,))

    thread1.start()

    # start receiving synchronized time from server
    thread2 = threading.Thread(target=receive_time, args=(client,))
    thread2.start()


if __name__ == '__main__':
    Client()