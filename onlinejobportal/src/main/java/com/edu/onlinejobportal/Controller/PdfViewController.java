package com.edu.onlinejobportal.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.onlinejobportal.Pojo.Applicant;

@Controller
public class PdfViewController {

	@RequestMapping(value = "/resume.pdf", method = {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<byte[]> getPDF1(@RequestParam("name") String name) throws IOException {

		System.out.println(name);
		

		Path pdfPath = Paths.get("C:\\Users\\Admin\\Desktop\\work\\OnlineJobPortal\\document\\"+name);
		byte[] pdf = Files.readAllBytes(pdfPath);
		
	    HttpHeaders headers = new HttpHeaders();

	    headers.setContentType(MediaType.parseMediaType("application/pdf"));
	    String filename = "pdf1.pdf";

	    headers.add("content-disposition", "inline;filename=" + filename);

	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	    ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdf, headers, HttpStatus.OK);
	    return response;
	}   
}
