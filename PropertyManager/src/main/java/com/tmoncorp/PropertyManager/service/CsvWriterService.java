package com.tmoncorp.PropertyManager.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.bytecode.opencsv.CSVWriter;

import com.tmoncorp.PropertyManager.model.InspectionModel;
import com.tmoncorp.PropertyManager.repository.InspectionRepository;

/**
 * 
 * @author pirothewriter
 *
 */

@Service
public class CsvWriterService {
	@Autowired
	private InspectionRepository inspectionRepository;

    public File generateCsvFile(int nth, String adAccount, String memberName, String flagDifference) throws IOException {
		List<InspectionModel> result = inspectionRepository.generateCsvFile(nth, adAccount, memberName, flagDifference);
		
		File catalinaBase = new File( System.getProperty( "catalina.base" ) ).getAbsoluteFile();
		File propertyFile = new File( catalinaBase, "wtpwebapps/PropertyManager/csv/inspection_result.csv" );

		CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(new FileOutputStream(propertyFile), "UTF-8"), ',', '"');

		String[] header = new String[] { "자산실사 차수", "자산번호", "자산명", "소유자", "소유자 AD계정", "실소유자", "실소유자 AD 계정", "자산실사일" };

		csvWriter.writeNext(header);

		for (int index = 0; index < result.size(); index++) {
			String nthString = String.valueOf(result.get(index).getNth());
			String propertyNumber = result.get(index).getPropertyNumber();
			String propertyName = result.get(index).getPropertyName();
			String memberNameString = result.get(index).getMemberName();
			String memberAdAccount = result.get(index).getAdAccount();
			String realMemberName = result.get(index).getRealMemberName();
			String realAdAccount = result.get(index).getRealAdAccount();
			String inspectedDate = result.get(index).getInspectionDate().toString();

			csvWriter.writeNext(new String[] { nthString, propertyNumber, propertyName, memberNameString, memberAdAccount, realMemberName, realAdAccount, inspectedDate });
		}

		csvWriter.close();

		return propertyFile;
	}
}
