package com.netty.xidian.edu.cn.nio.Reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ReactorServer {

    public static void main(String[] args) throws IOException {
        test();
    }

    public static void test() throws IOException {
        //1. 获取Selector选择器
        Selector selector = Selector.open();
        //2. 获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //3. 设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //4. 绑定连接
        serverSocketChannel.bind(new InetSocketAddress(2265));
        //5.将通道绑定到Selector，注册操作为接收操作
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //6.采用轮询的方式，查询获取就绪的注册过的操作
        while (selector.select() > 0){
            //7. 获取当前选择器中所有注册的选择器（已准备就绪操作）
            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
            while (iter.hasNext()){
                //8. 获取准备就绪的时间
                SelectionKey selectionKey = iter.next();
                //9. 判断key具体是什么事件
                if (selectionKey.isAcceptable()){
                    //10. 如果接受的事件是接受就绪操作，就获取客户端连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //11. 切换为非阻塞模式
                    socketChannel.configureBlocking(false);
                    //12. 将该通道注册到selector选择器
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }else if (selectionKey.isReadable()){
                    //13. 获取该选择器上的读就绪状态的通道
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    //14. 读取数据
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = socketChannel.read(buffer)) != -1){
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, len));
                        buffer.clear();
                    }
                    socketChannel.close();
                }
                //15. 移除选择器
                iter.remove();
            }
        }
        //16. 关闭连接
        serverSocketChannel.close();
    }
}
