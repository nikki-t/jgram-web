package api.document;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@ConfigurationProperties(prefix = "file")
@RequiredArgsConstructor
public class DocumentStorageProperties {
	
	private String uploadDirectory;
	
}
