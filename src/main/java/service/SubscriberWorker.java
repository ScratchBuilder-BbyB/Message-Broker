package service;

import domain.base.BaseConsumer;
import domain.Message;
import domain.Topic;
import interfaces.ConsumerWorker;

public class SubscriberWorker implements ConsumerWorker {

  private final BaseConsumer consumer;
  private final Topic topic;

  public SubscriberWorker(BaseConsumer consumer, Topic topic) {
    this.consumer = consumer;
    this.topic = topic;
  }

  @Override
  public void run() {
    synchronized (consumer){
      do{
        int currentOffset = consumer.getOffset().get();
        while(topic.getQueueSize() <= currentOffset){
          try {
            consumer.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        if(consumer.getLock().tryAcquire()){
          if(currentOffset == consumer.getOffset().get()){
            // consume message
            Message message = topic.getMessage(currentOffset);
            consumer.consume(message);

            // increment offset
            consumer.getOffset().compareAndSet(currentOffset, currentOffset + 1);
          }
          consumer.getLock().release();
        }

      }while (true);
    }
  }

  @Override
  public void wakeUpConsumer(){
    synchronized (consumer){
      consumer.notify();
    }
  }
}
