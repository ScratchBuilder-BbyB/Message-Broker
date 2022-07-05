package domain;

import domain.base.BaseConsumer;
import interfaces.Queue;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.Data;

@Data
public class SimpleQueue implements Queue {

  private final Map<String, TopicHandler> topicHandlerMap;

  public SimpleQueue() {
    this.topicHandlerMap = new HashMap<>();
  }

  @Override
  public void createTopic(String topicName) {
    Topic topic = new Topic(topicName);
    topicHandlerMap.putIfAbsent(topic.getName(), new TopicHandler(topic));
  }

  @Override
  public void subscribe(BaseConsumer... consumers) {
    Arrays.stream(consumers).sequential().forEach(consumer -> {
      TopicHandler handler = topicHandlerMap.getOrDefault(consumer.getTopicName(), null);
      if(Objects.nonNull(handler)){
        handler.getTopic().addConsumer(consumer);
      }
    });
  }

  @Override
  public void resetOffset(int newOffset, String topicName) {
    if(newOffset < 0){
      return;
    }
    topicHandlerMap.computeIfPresent(topicName, (name, handler) -> {
      Map<String, BaseConsumer> consumers = handler.getTopic().getConsumers();
      consumers.values().forEach(consumer -> consumer.getOffset().set(newOffset));
      return handler;
    });
  }

  @Override
  public void publishMessage(Message message, String topicName) {
    topicHandlerMap.computeIfPresent(topicName, (name, handler) -> {
      System.out.println("Publishing message :: "+ message);
      handler.getTopic().addMessage(message);
      handler.notifyAllConsumers();
      return handler;
    });
  }
}
