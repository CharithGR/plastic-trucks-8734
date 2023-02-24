package com.masai.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.masai.exceptions.ReportException;
import com.masai.models.Report;

@Service
public interface ReportService {
	
	public Report AddReport(Report report, String uuid)throws ReportException;
	public Report DeleteReportById(Integer reportId, String uuid)throws ReportException;
	public Report ViewReportById(Integer reportId, String uuid)throws ReportException;
	public List<Report> ViewAllReport(String uuid)throws ReportException;
	
}
