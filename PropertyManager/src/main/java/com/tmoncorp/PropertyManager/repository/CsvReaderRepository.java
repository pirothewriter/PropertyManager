package com.tmoncorp.PropertyManager.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.stereotype.Repository;

import au.com.bytecode.opencsv.CSVReader;

/**
 * 
 * @author piro
 *
 */

@Repository
public class CsvReaderRepository {
	public List<String[]> parsingCsv(File csvFile) throws IOException {
		CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream(csvFile), "EUC-KR"));

		return csvReader.readAll();
	}
}
