package com.onlineshop.admin.category;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.onlineshop.admin.AbstractExporter;
import com.onlineshop.common.entity.Category;

public class CategoryCsvExporter extends AbstractExporter{
	
	public void export(List<Category> listCategories, HttpServletResponse response) throws IOException {
		
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		super.setResponseHeader(response, "text/csv", ".csv", "categories_");
		String[] csvHeader = {"Category ID", "Category Name"};
		String[] filedMapping = {"id", "name"};
		
		csvWriter.writeHeader(csvHeader);
		
		for (Category category: listCategories) {
			category.setName(category.getName().replace("--", "  "));
			csvWriter.write(category, filedMapping);
		}
		
		csvWriter.close();
	}

}
