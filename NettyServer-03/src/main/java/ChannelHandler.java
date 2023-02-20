import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Description:
 * date: 2023/02/20 下午 2:51
 *
 * @author Quan
 */
public class ChannelHandler {
    // 用于存放用户Channel信息，也可以简历map结构模拟不同的消息群
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
