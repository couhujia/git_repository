package hope;

import org.apache.log4j.Logger;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelFuture;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HopeServer {
	private int port;
	public HopeServer(int port){
		this.port=port;
	}
		
	public void run() throws Exception{
		EventLoopGroup bossGroup =new NioEventLoopGroup();
		EventLoopGroup workerGroup =new NioEventLoopGroup();
		try{
			ServerBootstrap b=new ServerBootstrap();
			b.group(bossGroup,workerGroup)
			 .channel(NioServerSocketChannel.class)
			 .childHandler(new ChannelInitializer<SocketChannel>(){
				  @Override
                  public void initChannel(SocketChannel ch) throws Exception {
                      ch.pipeline().addLast(new HopeServerHandler());
				  }
				 })
			 .option(ChannelOption.SO_BACKLOG, 128)
			 .childOption(ChannelOption.SO_KEEPALIVE, true);	
			System.out.println("socket listen");
			ChannelFuture f=b.bind(port).sync();
			f.channel().closeFuture().sync();
		}finally{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
		System.out.println("in the run");
	}
	
	public static void main(String[] args) throws Exception{
		Logger logger=Logger.getLogger(HopeServer.class);
		logger.debug("Hope Progrom Start!");
		int port;
		port=50001;
		//HopeServer hs=new HopeServer(port);
		logger.info("Server begin run!");
		logger.error(port);
		//hs.run();
		logger.info("Hope Progrom end!");
	}

}
