package com.netty.xidian.edu.cn.handler3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

public class MyClientHandler extends SimpleChannelInboundHandler<PersonProtocol> {

    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++){
            String messageToBeSent = "sent from client";
            byte[] content = messageToBeSent.getBytes(Charset.forName("utf-8"));
            int length = messageToBeSent.getBytes(Charset.forName("utf-8")).length;

            PersonProtocol personProtocol = new PersonProtocol();
            personProtocol.setLength(length);
            personProtocol.setContent(content);

            ctx.writeAndFlush(personProtocol);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        int length = msg.getLength();
        byte[] content = msg.getContent();

        System.out.println("client receive message:");
        System.out.println("length:" + length);
        System.out.println("content:" + new String(content, Charset.forName("utf-8")));
        System.out.println("client receive message count:" + (++this.count));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
