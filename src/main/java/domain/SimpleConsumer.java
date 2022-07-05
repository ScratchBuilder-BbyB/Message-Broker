package domain;

import com.sun.istack.internal.NotNull;
import domain.base.BaseConsumer;
import java.util.UUID;


public class SimpleConsumer extends BaseConsumer {

  private final String consumerName;

  public SimpleConsumer(@NotNull final String consumerName, @NotNull final String topicName,
      @NotNull final String consumerGroupId) {
    super(consumerGroupId, topicName);
    this.consumerName = consumerName;
  }

  public SimpleConsumer(@NotNull final String consumerName, @NotNull final String topicName) {
    super(UUID.randomUUID().toString(), topicName);
    this.consumerName = consumerName;
  }

  @Override
  public void consume(Message message) {
    System.out.println(
        String.format("Consumed message :: %s, consumer :: %s, offset :: %d, groupID :: %s, semaphore :: %s",
            message, consumerName, getOffset().get(), getConsumerGroupId(), this.getLock()));
  }
}
