package top.damoncai.wogua.app.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 西门小狼狗
 * @Date: 2019/5/22 19:28
 */

/**
 * 服务器端的业务处理类
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {

    public static List<Channel> channels = new ArrayList<Channel>();

    //通道就绪
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel inChannel = ctx.channel();
        channels.add(inChannel);
        String msg = "[系统提醒]:" + inChannel.remoteAddress().toString().substring(1) + "上线了";
        System.out.println(msg);
    }

    //通道未就绪
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel inChannel = ctx.channel();
        channels.remove(inChannel);
        String msg = "[系统提醒]:" + inChannel.remoteAddress().toString().substring(1) + "下线了";
        System.out.println(msg);
    }

    //读取数据
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        System.out.println(s);
        Channel inChannel = ctx.channel();
        //将消息广播出去
        for(Channel channel : channels){
            if(inChannel != channel){
                channel.writeAndFlush("[" + inChannel.remoteAddress().toString().substring(1) + "]" + "说:" + s.trim() +"\n");
            }
        }
    }
}
