package utils;

import java.util.UUID;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BrokerUtils {
  public String getId(){
    return UUID.randomUUID().toString();
  }
}
