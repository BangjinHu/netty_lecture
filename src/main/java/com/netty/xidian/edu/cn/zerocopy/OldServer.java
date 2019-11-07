package com.netty.xidian.edu.cn.zerocopy;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class OldServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(2234);
        while (true){
            Socket socket = serverSocket.accept();//阻塞直到有连接
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            try {
                byte[] bytes = new byte[4096];
                while (true){
                    int count = dataInputStream.read(bytes, 0, bytes.length);
                    if (-1 == count){
                        break;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
