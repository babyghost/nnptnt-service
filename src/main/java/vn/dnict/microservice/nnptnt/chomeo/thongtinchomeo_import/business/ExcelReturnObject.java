package vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo_import.business;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.SystemUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import vn.dnict.microservice.core.dao.model.CoreAttachment;

@Service
@Slf4j
public class ExcelReturnObject {
//	@SuppressWarnings("resource")
	public List<HashMap<String, String>> excel2Object(CoreAttachment coreAttachment, String[] keyArray) throws IOException {
			String path;
			if (SystemUtils.IS_OS_WINDOWS) {
				path = Paths.get(coreAttachment.getFolder() + "\\" + coreAttachment.getCode()).toString();
			} else {
				path = Paths.get(coreAttachment.getFolder() + "/" + coreAttachment.getCode()).toString();
			}
	        FileInputStream inputStream = new FileInputStream(new File(path));
	    	BufferedInputStream in = new BufferedInputStream(inputStream);
	        Workbook workbook = new XSSFWorkbook(in);
	        Sheet firstSheet = workbook.getSheetAt(0);
	        List<HashMap<String, String>> listObject = new ArrayList<HashMap<String,String>>();
	        Iterator<Row> iterator = firstSheet.iterator();
	        int dem = 0;
	        while (iterator.hasNext()) {
	            Row nextRow = iterator.next();
	            if(dem > 0 && keyArray.length > 0) {
		        	int i;
		        	HashMap<String, String> temp = new HashMap<String, String>();
		        	int numbercolumn = keyArray.length;
		        	if(nextRow != null && nextRow.getCell(0) != null && nextRow.getCell(0).getCellType().name() == "NUMERIC" ) {
		        		for(i = 0;i < numbercolumn; i++) {
			        		if(nextRow.getCell(i) != null) {
			        			 switch (nextRow.getCell(i).getCellType()) {
				                     case STRING:
				                    	 temp.put(keyArray[i], nextRow.getCell(i).getStringCellValue());
				                         break;
				                     case NUMERIC: 
				                    	 temp.put(keyArray[i], nextRow.getCell(i).getNumericCellValue()+"");
				                         break;
				                     case BLANK: 
				                    	 temp.put(keyArray[i], null);
				                         break;    
				                    default: 
				                    	 temp.put(keyArray[i], "SAI_DU_LIEU");
				                 }
			        		}else {
			        			temp.put(keyArray[i], "SAI_DU_LIEU");
			        		}
			        	}	
		        		listObject.add(temp);
	        		}
		        }
	            dem++;
	        }
	        workbook.close();
	        inputStream.close();
		return listObject;
	}
	
	public List<HashMap<String, String>> excel2ObjectIndex(CoreAttachment coreAttachment, String[] keyArray,Integer index) throws IOException {
		String path;
		if (SystemUtils.IS_OS_WINDOWS) {
			path = Paths.get(coreAttachment.getFolder() + "\\" + coreAttachment.getCode()).toString();
		} else {
			path = Paths.get(coreAttachment.getFolder() + "/" + coreAttachment.getCode()).toString();
		}
        FileInputStream inputStream = new FileInputStream(new File(path));
    	BufferedInputStream in = new BufferedInputStream(inputStream);
        Workbook workbook = new XSSFWorkbook(in);
        Sheet firstSheet = workbook.getSheetAt(0);
        List<HashMap<String, String>> listObject = new ArrayList<HashMap<String,String>>();
        Iterator<Row> iterator = firstSheet.iterator();
        int dem = index;
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if(dem > 0 && keyArray.length > 0) {
	        	int i;
	        	HashMap<String, String> temp = new HashMap<String, String>();
	        	int numbercolumn = keyArray.length;
	        	if(nextRow != null && nextRow.getCell(0) != null && nextRow.getCell(0).getCellType().name() == "NUMERIC" ) {
	        		for(i = 0;i < numbercolumn; i++) {
		        		if(nextRow.getCell(i) != null) {
		        			 switch (nextRow.getCell(i).getCellType()) {
			                     case STRING:
			                    	 temp.put(keyArray[i], nextRow.getCell(i).getStringCellValue());
			                         break;
			                     case NUMERIC: 
			                    	 temp.put(keyArray[i], nextRow.getCell(i).getNumericCellValue()+"");
			                         break;
			                     case BLANK: 
			                    	 temp.put(keyArray[i], null);
			                         break;    
			                    default: 
			                    	 temp.put(keyArray[i], "SAI_DU_LIEU");
			                 }
		        		}else {
		        			temp.put(keyArray[i], "SAI_DU_LIEU");
		        		}
		        	}	
	        		listObject.add(temp);
        		}
	        }
            dem++;
        }
        workbook.close();
        inputStream.close();
	return listObject;
}
}