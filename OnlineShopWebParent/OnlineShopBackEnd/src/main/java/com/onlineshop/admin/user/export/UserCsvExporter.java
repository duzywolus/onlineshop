package com.onlineshop.admin.user.export;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.onlineshop.admin.AbstractExporter;
import com.onlineshop.common.entity.User;

public class UserCsvExporter extends AbstractExporter{
	
	public void export(List<User> listUsers, HttpServletResponse response) throws IOException {
		
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		super.setResponseHeader(response, "text/csv", ".csv", "users_");
		String[] csvHeader = {"User ID", "E-mail", "First Name", "Last Name", "Roles", "Status of Enabling"};
		String[] filedMapping = {"id", "email", "firstName", "lastName", "roles", "enabled"};
		
		csvWriter.writeHeader(csvHeader);
		
		for (User user : listUsers) {
			csvWriter.write(user, filedMapping);
		}
		
		csvWriter.close();
	}

}
