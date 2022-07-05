package interfaces;

import domain.Message;
import java.util.concurrent.Semaphore;

public interface Consumer{
  String getId();
  void consume(Message message);
  Semaphore getLock();
}
