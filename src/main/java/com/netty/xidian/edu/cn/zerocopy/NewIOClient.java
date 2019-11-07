package com.netty.xidian.edu.cn.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewIOClient {

    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 2234));
        socketChannel.configureBlocking(true);

        String fileName = "D:\\navicat_workspaceLogImport.txt";

        FileChannel fileChannel = new FileInputStream(fileName).getChannel();

        long startTime = System.currentTimeMillis();

        /**
         * 这里transferTo()方法借助零拷贝方式
         */
        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        System.out.println("send total bytes" + transferCount + ",time:" + (System.currentTimeMillis() - startTime));
        fileChannel.close();

    }
}
