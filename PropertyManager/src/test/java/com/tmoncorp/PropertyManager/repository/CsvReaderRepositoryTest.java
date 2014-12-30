package com.tmoncorp.PropertyManager.repository;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author piro
 *
 */

public class CsvReaderRepositoryTest {
	private CsvReaderRepository csvReaderRepository;
	
	@Before
	public void setup(){
		csvReaderRepository = new CsvReaderRepository();
	}
	
	@Test
	public void CSV파일을_읽어들이고나서_정상적으로_반환하는지_테스트() throws IOException{
		File file = new File("WebContent/csv/test_member.csv");
		assertNotNull(csvReaderRepository.parsingCsv(file));
	}

}
