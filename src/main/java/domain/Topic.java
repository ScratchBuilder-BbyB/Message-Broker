package domain;

import domain.base.BaseConsumer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.NonNull;
import utils.BrokerUtils;

@Data
public class Topic {
  private final String id;
  private final String name;
  private final Map<String, BaseConsumer> consumers;
  private final List<Message> messageList;

  public Topic(@NonNull final String name) {
    this.name = name;
    this.id = BrokerUtils.getId();
    consumers = new HashMap<>();
    messageList = new ArrayList<>();
  }

  public void addMessage(@NonNull final Message message){
    messageList.add(message);
  }

  public void addConsumer(@NonNull final BaseConsumer consumer){
    consumers.putIfAbsent(consumer.getId(), consumer);
  }

  public int getQueueSize(){
    return messageList.size();
  }

  public Message getMessage(int offset){
    return messageList.get(offset);
  }
}
