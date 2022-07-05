package domain.base;

import interfaces.Consumer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Data;
import lombok.NonNull;
import utils.BrokerUtils;

@Data
public abstract class BaseConsumer implements Consumer {


  private final String consumerGroupId;
  private final String topicName;
  private static final Map<String, Semaphore> semaphoreMap = new HashMap<>();
  private static final Map<String, AtomicInteger> offsetMap = new HashMap<>();

  public BaseConsumer(@NonNull final String consumerGroupId, @NonNull final String topicName) {
    this.consumerGroupId = consumerGroupId;
    this.topicName = topicName;
    offsetMap.putIfAbsent(getMapKey(topicName, consumerGroupId), new AtomicInteger(0));
    semaphoreMap.putIfAbsent(getMapKey(topicName, consumerGroupId), new Semaphore(1, true));
  }

  private String getMapKey(String topicName, String consumerGroupId) {
    return topicName.concat(consumerGroupId);
  }

  public AtomicInteger getOffset() {
    return offsetMap.get(getMapKey(topicName, consumerGroupId));
  }

  @Override
  public String getId() {
    return BrokerUtils.getId();
  }

  @Override
  public Semaphore getLock() {
    return semaphoreMap.get(getMapKey(topicName, consumerGroupId));
  }
}
