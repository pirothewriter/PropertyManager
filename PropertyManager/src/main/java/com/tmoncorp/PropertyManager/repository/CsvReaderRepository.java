package com.tmoncorp.PropertyManager.repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;

import au.com.bytecode.opencsv.CSVReader;

import com.tmoncorp.PropertyManager.model.MemberModel;

/**
 * 
 * @author piro
 *
 */

@Repository
public class CsvReaderRepository {
	public List<String[]> parsingCsv(String csvFile) throws IOException {
		CSVReader csvReader = new CSVReader(new FileReader(csvFile));

		return csvReader.readAll();
	}
}
