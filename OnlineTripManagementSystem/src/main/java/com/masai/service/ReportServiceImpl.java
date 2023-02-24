package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.LoginException;
import com.masai.exceptions.ReportException;
import com.masai.exceptions.UserException;
import com.masai.models.CurrentUserSession;
import com.masai.models.Report;
import com.masai.repository.ReportDAO;
import com.masai.repository.SessionDAO;

@Service
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	private ReportDAO rdao;
	
	@Autowired
	private SessionDAO sdao;

	@Override
	public Report AddReport(Report report, String uuid) throws ReportException {
		CurrentUserSession existingUser = sdao.findByUuid(uuid);
		if(existingUser == null) {
			throw new UserException("User not Logged In");
		}
		else if(existingUser.getUserType().equalsIgnoreCase("customer")) {
			throw new LoginException("Access denied");
		} else {
			Report newreport = rdao.save(report);
			return newreport;
		}
	}

	@Override
	public Report DeleteReportById(Integer reportId, String uuid) throws ReportException {
		CurrentUserSession existingUser = sdao.findByUuid(uuid);
		if(existingUser == null) {
			throw new UserException("User not Logged In");
		}
		else if(existingUser.getUserType().equalsIgnoreCase("customer")) {
			throw new LoginException("Access denied");
		} else {
			Optional<Report> opt = rdao.findById(reportId);
			if (opt.isPresent()) {
				Report existingReport = opt.get();
				rdao.delete(existingReport);
				return existingReport;
			} else {
				throw new ReportException("Report does not exist with Id :" + reportId);
			}			
		}
	}

	@Override
	public Report ViewReportById(Integer reportId, String uuid) throws ReportException {
		CurrentUserSession existingUser = sdao.findByUuid(uuid);
		if(existingUser == null) {
			throw new UserException("User not Logged In");
		}
		else if(existingUser.getUserType().equalsIgnoreCase("customer")) {
			throw new LoginException("Access denied");
		} else {
			Optional<Report> opt = rdao.findById(reportId);
			if (opt.isPresent()) {
				Report foundReport = opt.get();
				return foundReport;
			} else {
				throw new ReportException("Report does not exist with Id :" + reportId);
			}
		}
	}

	@Override
	public List<Report> ViewAllReport(String uuid) throws ReportException {
		CurrentUserSession existingUser = sdao.findByUuid(uuid);
		if(existingUser == null) {
			throw new UserException("User not Logged In");
		}
		else if(existingUser.getUserType().equalsIgnoreCase("customer")) {
			throw new LoginException("Access denied");
		} else {
			List<Report> report = rdao.findAll();
			if (report.size() == 0)
				throw new ReportException("No Reports found..");
			else
				return report;
		}
	}
}















	
