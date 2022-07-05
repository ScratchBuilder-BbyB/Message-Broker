package interfaces;

import domain.base.BaseConsumer;
import domain.Message;

public interface Queue {
  void createTopic(String topicName);
  void subscribe(BaseConsumer... consumers);
  void resetOffset(int newOffset, String topicName);
  void publishMessage(Message message, String topicName);
}
