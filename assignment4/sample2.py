import socket
import threading
from dateutil import parser
from datetime import datetime as dt
import datetime
import time
global_obj = {}

def receive_time(conn, addr):
    while True:
        address = f"{addr[0]}:{addr[1]}"
        clock_time = parser.parse(conn.recv(1024).decode())
        clock_time_diff = dt.now() - clock_time
        global_obj[address] = {
            'conn': conn,
            'time_diff': clock_time_diff,
            'clock_time': clock_time
        }

        print(f"Sever Recv Time From {addr[1]} : {str(clock_time)}")

        time.sleep(5)


def accept_clients(master):
    conn, addr = master.accept()
    print(f"Connected to {addr}")

    start_recv = threading.Thread(target=receive_time, args=(conn,addr))
    start_recv.start()

def get_average_clock_diff():
    l1 = [client['time_diff'] for client_addr, client in global_obj.items()]
    s1 = sum(l1, datetime.timedelta(0, 0))/len(global_obj)
    print(f"Average Time Val: {s1}")
    return s1


def synchronize_all_clocks():  
    while True:
        print("New Sync Cycle Started")
        print("Number Of Clients Are: ", len(global_obj))
        if len(global_obj) > 0:
            avg_clock_diff = get_average_clock_diff()
        
        sync_time = dt.now() + avg_clock_diff
        for client_addr, client in global_obj.items():
            try:
                client['conn'].send(str(sync_time).encode())
            except Exception as e:
                print(f"Error While Sending Sync Time To {client_addr}")
        print("\n\n")
        time.sleep(5)

def Server():
    master = socket.socket()
    master.bind(('127.0.0.1', 8080))
    master.listen(10)

    thread_to_accept = threading.Thread(target=accept_clients, args=(master,))

    thread_to_accept.start()

    thread_to_sync = threading.Thread(target=synchronize_all_clocks)
    thread_to_sync.start()


if __name__ == '__main__':
    Server()