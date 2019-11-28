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
        //1. ��ȡSelectorѡ����
        Selector selector = Selector.open();
        //2. ��ȡͨ��
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //3. ����Ϊ������
        serverSocketChannel.configureBlocking(false);
        //4. ������
        serverSocketChannel.bind(new InetSocketAddress(2265));
        //5.��ͨ���󶨵�Selector��ע�����Ϊ���ղ���
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //6.������ѯ�ķ�ʽ����ѯ��ȡ������ע����Ĳ���
        while (selector.select() > 0){
            //7. ��ȡ��ǰѡ����������ע���ѡ��������׼������������
            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
            while (iter.hasNext()){
                //8. ��ȡ׼��������ʱ��
                SelectionKey selectionKey = iter.next();
                //9. �ж�key������ʲô�¼�
                if (selectionKey.isAcceptable()){
                    //10. ������ܵ��¼��ǽ��ܾ����������ͻ�ȡ�ͻ�������
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //11. �л�Ϊ������ģʽ
                    socketChannel.configureBlocking(false);
                    //12. ����ͨ��ע�ᵽselectorѡ����
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }else if (selectionKey.isReadable()){
                    //13. ��ȡ��ѡ�����ϵĶ�����״̬��ͨ��
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    //14. ��ȡ����
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = socketChannel.read(buffer)) != -1){
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, len));
                        buffer.clear();
                    }
                    socketChannel.close();
                }
                //15. �Ƴ�ѡ����
                iter.remove();
            }
        }
        //16. �ر�����
        serverSocketChannel.close();
    }
}
