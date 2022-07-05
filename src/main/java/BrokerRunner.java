import domain.base.BaseConsumer;
import domain.Message;
import domain.SimpleConsumer;
import domain.SimpleQueue;
import interfaces.Queue;
import java.util.Scanner;
import domain.base.BaseConsumer;

public class BrokerRunner {

  public static void main(String[] args) throws InterruptedException {

    Queue queue = new SimpleQueue();
    queue.createTopic("topic1");
    queue.createTopic("topic2");

    BaseConsumer consumer1 = new SimpleConsumer("consumer1", "topic1", "Group1");
    BaseConsumer consumer2 = new SimpleConsumer("consumer2", "topic1", "Group1");
    BaseConsumer consumer3 = new SimpleConsumer("consumer3", "topic1");
    BaseConsumer consumer4 = new SimpleConsumer("consumer4", "topic2");
    BaseConsumer consumer5 = new SimpleConsumer("consumer5", "topic2", "Group1");
    BaseConsumer consumer6 = new SimpleConsumer("consumer6", "topic2", "Group1");

    queue.subscribe(consumer1, consumer2, consumer3, consumer4, consumer5, consumer6);



//    queue.publishMessage(new Message("Message1"), "topic1");
//    queue.publishMessage(new Message("Message2"), "topic1");
//    queue.publishMessage(new Message("Message3"), "topic1");
//    queue.publishMessage(new Message("Message4"), "topic1");
//
//    queue.publishMessage(new Message("Message4"), "topic2");
//    queue.publishMessage(new Message("Message5"), "topic2");
//    queue.publishMessage(new Message("Message6"), "topic2");
//    queue.publishMessage(new Message("Message7"), "topic2");

    int msgIndex = 0;

    while(true){
      Scanner kb = new Scanner(System.in);
      String topicName = kb.nextLine();
      if(topicName.equals("exit")){
        System.exit(0);
      }
      if(topicName.equals("reset")){
        queue.resetOffset(0, "topic1");
      }
      queue.publishMessage(new Message("Message"+msgIndex), topicName);
      msgIndex++;

    }



  }
}
