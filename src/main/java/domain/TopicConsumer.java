package domain;

import interfaces.Consumer;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Data;

@Data
public class TopicConsumer {
  private AtomicInteger offset;
  private final Consumer consumer;

  public TopicConsumer(final Consumer consumer) {
    this.consumer = consumer;
    offset = new AtomicInteger(0);
  }
}
