package com.netty.xidian.edu.cn.example1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestServer {

    public static void main(String[] args) throws Exception {
        //�¼�ѭ���飬��ѭ��
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //handler�Ǵ���bossGroup�ģ���childHandler����workerGroup��
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).
                    childHandler(new TestServerInitializer());

            //�������󶨶˿ںţ��ȴ��ͻ�������
            ChannelFuture channelFuture = serverBootstrap.bind(3622).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            //netty���Źر�
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
