import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.apache.commons.lang.CharSet;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Description:
 * date: 2023/02/20 下午 3:15
 *
 * @author Quan
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        // 基于换行符
        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        // 解码转string
        channel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
        // 编码转string
        channel.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
        // 添加我们自定义的接收数据处理方法
        channel.pipeline().addLast(new MyClientHandler());
    }
}
