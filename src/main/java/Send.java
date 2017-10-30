import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 发送消息
 *
 * @auther：Eden
 * @date：2017/10/30
 */
public class Send {
	private final static String QUEUE_NAME = "hello";
	public static void main(String[] args) throws IOException, TimeoutException {
		//创建连接
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		//创建通道，channel是api操作的核心
		Channel channel = connection.createChannel();

		//声明将消息发送给的队列  publish a message to the queue:
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String message = "Hello World!";
		//发布消息
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");

		//关闭通道，关闭连接
		channel.close();
		connection.close();
	}
}
