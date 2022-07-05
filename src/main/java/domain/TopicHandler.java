package domain;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.NonNull;
import service.SubscriberWorker;
import utils.TaskExecutor;

@Data
public class TopicHandler {

  private final Topic topic;
  private final Map<String, SubscriberWorker> subscriberWorkerMap;

  public TopicHandler(@NonNull final Topic topic) {
    this.topic = topic;
    subscriberWorkerMap = new HashMap<>();
  }

  public void notifyAllConsumers(){
    topic.getConsumers().entrySet().forEach(entry -> {
      String consumerId = entry.getKey();
      if(!subscriberWorkerMap.containsKey(consumerId)){
        subscriberWorkerMap.put(consumerId, new SubscriberWorker(entry.getValue(), topic));
        TaskExecutor.submitTask(subscriberWorkerMap.get(consumerId));
      }

      subscriberWorkerMap.get(consumerId).wakeUpConsumer();
    });
  }
}
