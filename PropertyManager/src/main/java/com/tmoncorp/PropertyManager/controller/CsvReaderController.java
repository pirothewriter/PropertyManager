package com.tmoncorp.PropertyManager.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tmoncorp.PropertyManager.service.CsvReaderService;
import com.tmoncorp.PropertyManager.util.ConvertMultipartFileToFile;

@Controller
public class CsvReaderController {
	@Autowired
	private CsvReaderService csvReaderService;

	@RequestMapping(value = "/uploadMembers", method = RequestMethod.POST)
	public @ResponseBody String insertMultipleMembers(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		Iterator<String> iterator = request.getFileNames();
		String msg;

		if (isUploadedFile(iterator) == false)
			return "NO FILE";

		MultipartFile csvFile = request.getFile(iterator.next());

		if (isCsvFile(csvFile) == false)
			return "NOT CSV";

		msg = inserting("member", csvFile);

		return msg;
	}

	@RequestMapping(value = "/uploadProperty", method = RequestMethod.POST)
	public @ResponseBody String insertMultipleProperties(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		Iterator<String> iterator = request.getFileNames();
		String msg;

		if (isUploadedFile(iterator) == false)
			return "NO FILE";

		MultipartFile csvFile = request.getFile(iterator.next());

		if (isCsvFile(csvFile) == false)
			return "NOT CSV";

		msg = inserting("property", csvFile);

		return msg;
	}

	private String inserting(String insertType, MultipartFile csvFile) throws IOException, ParseException {
		String msg;
		int result = 0;
		ConvertMultipartFileToFile convertMultipartFileToFile = new ConvertMultipartFileToFile();
		File file = convertMultipartFileToFile.convert(csvFile);

		if (insertType.compareTo("member") == 0)
			result = csvReaderService.multipleMemberInsert(file);
		else if (insertType.compareTo("property") == 0)
			result = csvReaderService.multiplePropertiesInsert(file);

		if (result > 0)
			msg = "SUCCESS";
		else
			msg = "ERROR";
		return msg;
	}

	private boolean isCsvFile(MultipartFile csvFile) {
		String filename = csvFile.getOriginalFilename().trim();
		int pathPoint = filename.lastIndexOf(".");
		String filePoint = (String) filename.subSequence(pathPoint + 1, filename.length());
		String type = filePoint.toLowerCase();

		return (type.compareTo("csv") == 0);
	}

	private boolean isUploadedFile(Iterator<String> iterator) {
		return iterator.hasNext();
	}
}
