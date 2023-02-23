package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.masai.exceptions.ReportException;
import com.masai.models.Report;
import com.masai.repository.ReportDAO;

@Service
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	private ReportDAO rdao;

	@Override
	public Report AddReport(Report report) throws ReportException {
		Report newreport = rdao.save(report);
		return newreport;
	}

	@Override
	public Report DeleteReportById(Integer reportId) throws ReportException {
		Optional<Report> opt = rdao.findById(reportId);
		if (opt.isPresent()) {
			Report existingReport = opt.get();
			rdao.delete(existingReport);
			return existingReport;
		} else {
			throw new ReportException("Report does not exist with Id :" + reportId);
		}
	}

	@Override
	public Report ViewReportById(Integer reportId) throws ReportException {
		Optional<Report> opt = rdao.findById(reportId);
		if (opt.isPresent()) {
			Report foundReport = opt.get();
			return foundReport;
		} else {
			throw new ReportException("Report does not exist with Id :" + reportId);
		}
	}

	@Override
	public List<Report> ViewAllReport() throws ReportException {
		List<Report> report = rdao.findAll();
		if (report.size() == 0)
			throw new ReportException("No Reports found..");
		else
			return report;
	}

}















	
