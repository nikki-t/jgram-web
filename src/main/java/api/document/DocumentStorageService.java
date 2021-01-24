package api.document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service to store documents that have been uploaded to the server and return
 * a zip file of processed documents.
 * 
 */
@Service
public class DocumentStorageService {
	
	private final Path documentStorageLocation;
	
	@Autowired
	public DocumentStorageService(DocumentStorageProperties documentStorageProperties) {
		// Initialize upload directory
		this.documentStorageLocation = (Paths.get(documentStorageProperties.getUploadDirectory()).toAbsolutePath().normalize());
		
		// Create upload directory if it does not exist
		try {
			Files.createDirectories(this.documentStorageLocation);
		} catch (Exception ex) {
			throw new DocumentStorageException("Could not detect or create upload directory.");
		}
	}
	
	public Path storeDocument(MultipartFile document) {
		
		// Retrieve and normalize document name
		String documentName = StringUtils.cleanPath(document.getOriginalFilename());
		
		// Try to copy file to local file system
		try {
			Path documentOnDisk = this.documentStorageLocation.resolve(documentName);
			Files.copy(document.getInputStream(), documentOnDisk, StandardCopyOption.REPLACE_EXISTING);
			return documentOnDisk;
		} catch (IOException ex) {
			throw new DocumentStorageException("Could not store document: " + documentName, ex);
		}
	}
	
}
