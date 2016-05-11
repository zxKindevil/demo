package com.kindevil.demo;

/**
 * @author zhangxin.zhang created on 16-4-12.
 */
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.springframework.jdbc.support.incrementer.PostgreSQLSequenceMaxValueIncrementer;

import java.util.ArrayList;

/**
 * client和server接收消息共用的handler
 * 由于两个都是继承自SimpleChannelUpstreamHandler,所以就写在一起了。
 * @author Ransom
 *
 */
public class Handler extends SimpleChannelUpstreamHandler
{
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
            throws Exception
    {
        System.out.println("recive message,message content:"+e.getMessage());

    }

    public void exceptionCaught(
            ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        System.err.println("Client has a error,Error cause:"+e.getCause());
        e.getChannel().close();
    }
}
