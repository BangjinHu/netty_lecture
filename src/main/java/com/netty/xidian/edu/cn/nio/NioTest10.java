package com.netty.xidian.edu.cn.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class NioTest10 {

    public static void main(String[] args) throws IOException {

        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest10.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        FileLock fileLock = fileChannel.lock(3,6,true);
        System.out.println("valid:" + fileLock.isValid());
        System.out.println("lock type:" + fileLock.isShared());

        fileLock.release();
        randomAccessFile.close();

    }
}
