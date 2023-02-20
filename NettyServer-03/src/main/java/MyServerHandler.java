import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description: 自定义管道数据处理器
 * date: 2023/02/20 下午 1:17
 *
 * @author Quan
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    private static final String CRLF = "\r\n";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 当客户端连接的时候，添加到channelGroup中
        ChannelHandler.channelGroup.add(ctx.channel());

        // 日志信息
        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("链接报告开始 {公众号：bugstack虫洞栈 >获取学习源码}");
        System.out.println("链接报告信息：有一客户端链接到本服务端");
        System.out.println("链接报告IP:" + channel.localAddress().getHostString());
        System.out.println("链接报告Port:" + channel.localAddress().getPort());
        System.out.println("链接报告完毕");

        // 通知客户端连接成功
        String str = "通知客户端连接建立成功" + " " + new Date() + channel.localAddress().getHostString() + CRLF;
        ChannelHandler.channelGroup.writeAndFlush(str);
    }

    /**
     * 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端的关闭了通信通道并且不可以传输数据
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端断开链接" + ctx.channel().localAddress().toString());
        // 客户端退出，移出channelGroup
        ChannelHandler.channelGroup.remove(ctx.channel());
    }

    // 接收到信息，并打印出来
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //接收msg消息{与上一章节相比，此处已经不需要自己进行解码}
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收到消息：" + msg);

        // 群发客户端消息
        String str = "服务端收到：" + new Date() + " " + msg + CRLF;
        ChannelHandler.channelGroup.writeAndFlush(str);
    }

    /**
     * 抓住异常，当发生异常的时候，可以做一些相应的处理，比如打印日志、关闭链接
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常信息：\r\n" + cause.getMessage());
    }
}
