import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * Description: 自定义管道数据处理器
 * date: 2023/02/20 下午 1:17
 *
 * @author Quan
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    // 接收到信息，并打印出来
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // Netty数据传输使用的是ByteBuf。需要进行转换
        ByteBuf buf = (ByteBuf) msg;
        // 将数据存储到byte数组中
        byte[] msgByte = new byte[buf.readableBytes()];
        buf.readBytes(msgByte);

        System.out.print(new Date() + "接收到消息：");
        System.out.println(new String(msgByte, Charset.forName("GBK")));
    }
}
