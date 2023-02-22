package com.masai.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.masai.exceptions.ReportException;
import com.masai.models.Report;

@Service
public interface ReportService {
	
	public Report AddReport(Report report)throws ReportException;
	public Report DeleteReportById(Integer reportId)throws ReportException;
	public Report ViewReportById(Integer reportId)throws ReportException;
	public List<Report> ViewAllReport()throws ReportException;
	
}
