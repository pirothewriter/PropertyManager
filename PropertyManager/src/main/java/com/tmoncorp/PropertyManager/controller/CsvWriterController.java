package com.tmoncorp.PropertyManager.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmoncorp.PropertyManager.service.CsvWriterService;
import com.tmoncorp.PropertyManager.service.InspectionService;

/**
 * 
 * @author piro
 *
 */

@Controller
public class CsvWriterController {
	@Autowired
	private InspectionService inspectionService;

	@Autowired
	private CsvWriterService csvWriterService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/generateResult", method = RequestMethod.GET)
	public @ResponseBody File generateResult(HttpServletRequest request) throws IOException {
		int nth;
		String adAccount;
		String memberName;
		String flagDifference;

		if (request.getParameter("nth") != null)
			nth = Integer.parseInt(request.getParameter("nth"));
		else
			nth = inspectionService.getLastestNth();

		if (request.getParameter("adAccount") != null)
			adAccount = request.getParameter("adAccount");
		else
			adAccount = null;

		if (request.getParameter("memberName") != null)
			memberName = request.getParameter("memberName");
		else
			memberName = null;

		if (request.getParameter("flagDifference") != null)
			flagDifference = request.getParameter("flagDifference");
		else
			flagDifference = null;

		File result = csvWriterService.generateCsvFile(nth, adAccount, memberName, flagDifference);

		return result;
	}
}
