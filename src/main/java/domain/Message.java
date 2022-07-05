package domain;

import java.util.UUID;
import lombok.Data;

@Data
public class Message{
  private final String data;
  private final String id;

  public Message(String data) {
    this.data = data;
    this.id = UUID.randomUUID().toString();
  }
}
