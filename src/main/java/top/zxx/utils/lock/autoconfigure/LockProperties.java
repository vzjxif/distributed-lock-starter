package top.zxx.utils.lock.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "lock")
public class LockProperties {

    private String prefix;

    private Integer timeout;
}
