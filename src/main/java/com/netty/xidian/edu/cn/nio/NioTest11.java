package com.netty.xidian.edu.cn.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * 关于Buffer的Scattering(散开)与Gathering(收集)
 */
public class NioTest11 {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(3352);
        serverSocketChannel.socket().bind(address);

        int messageLength = 2 + 3 + 4;
        ByteBuffer[] buffers = new ByteBuffer[3];

        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();

        while (true){
            int bytesRead = 0;
            while (bytesRead < messageLength){
                long r = socketChannel.read(buffers);
                bytesRead += r;

                System.out.println("bytesRead:" + bytesRead);

                Arrays.asList(buffers).stream().map(buffer -> "postion:" + buffer.position() + ", limit" + buffer.limit()).
                        forEach(System.out::println);
            }

            Arrays.asList(buffers).forEach(buffer->{
                buffer.flip();
            });

            long bytesWritten = 0;
            while (bytesWritten < messageLength){
                long r = socketChannel.write(buffers);
                bytesWritten += r;
            }

            Arrays.asList(buffers).forEach(buffer->{
                buffer.clear();
            });
            System.out.println("bytesRead:" + bytesRead + ", bytesWritten:" + bytesWritten + ", messageLength:" + messageLength);
        }




    }
}
