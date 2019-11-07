package com.netty.xidian.edu.cn.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest8 {

    public static void main(String[] args) throws IOException {

        FileInputStream inputStream = new FileInputStream("input.txt");
        FileOutputStream outputStream = new FileOutputStream("output.txt");

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

//        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(512);
        while (true){
            byteBuffer.clear();//postion回到第一位置
            int read = inputChannel.read(byteBuffer);
            System.out.println("read:" + read);
            if (-1 == read){
                break;
            }
            byteBuffer.flip();
            outputChannel.write(byteBuffer);
        }
        inputChannel.close();
        outputChannel.close();

    }
}
