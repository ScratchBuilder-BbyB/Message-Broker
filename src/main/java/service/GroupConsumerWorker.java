package service;

import domain.GroupConsumer;
import domain.Topic;
import interfaces.ConsumerWorker;

public class GroupConsumerWorker implements ConsumerWorker {

  private final GroupConsumer consumer;
  private final Topic topic;

  public GroupConsumerWorker(GroupConsumer consumer, Topic topic) {
    this.consumer = consumer;
    this.topic = topic;
  }

  @Override
  public void wakeUpConsumer() {
    synchronized (consumer){
      consumer.notify();
    }
  }

  @Override
  public void run() {
    synchronized (consumer){
      do{
        
      }while (true);
    }
  }
}
