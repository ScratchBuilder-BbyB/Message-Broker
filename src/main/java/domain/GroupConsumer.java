package domain;

import domain.base.BaseConsumer;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.NonNull;

public class GroupConsumer{

  private final String consumerGroupId;
  private final Map<String, BaseConsumer> consumerMap;

  public GroupConsumer() {
      this.consumerGroupId = UUID.randomUUID().toString();
      consumerMap = new HashMap<>();
  }

  public void addConsumer(@NonNull final BaseConsumer consumer){
      consumerMap.putIfAbsent(consumer.getId(), consumer);
  }
}
