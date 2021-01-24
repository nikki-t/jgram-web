package api.document;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * API endpoint for documents uploaded to the server. Accepts a MultipartFile
 * array of documents to process and returns processed documents as a zip file.
 *
 */
@RestController
@RequestMapping(path="/grade")
@CrossOrigin(origins="*")
public class DocumentController {
	
	@Autowired
	private DocumentStorageService documentStorageService;
	
	public Path uploadDocument(MultipartFile document) {
		
		System.out.println(document.getOriginalFilename() + ", "  + document.getContentType());
		return documentStorageService.storeDocument(document);
		
	}
	
	@PostMapping("/uploadDocuments")
	public String uploadDocuments(@RequestParam("document") MultipartFile[] documents) {
		
		// Store documents to be graded
		List<Path> documentPathList = Arrays.asList(documents)
			.stream()
			.map(document -> uploadDocument(document))
			.collect(Collectors.toList());
		System.out.println(documentPathList);
		
		// Grade documents TODO
		
		// Return a zip file of graded documents TODO
		
		return "good";
	}

}
