package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.Report;
import com.masai.service.ReportService;

@RestController
@RequestMapping("/reportservice")
public class ReportController {
	
	@Autowired
	private ReportService rService;
	
	@PostMapping("/reports")
	public ResponseEntity<Report> addReportHandler(@RequestBody Report report, @PathVariable("uuid") String uuid){
		Report newReport = rService.AddReport(report, uuid);
		return new ResponseEntity<Report>(newReport, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/reports/{reportId}")
	public ResponseEntity<Report> deleteReportHandler(@PathVariable("reportId") Integer reportId, @PathVariable("uuid") String uuid){
		Report deletedReport = rService.DeleteReportById(reportId, uuid);
		return new ResponseEntity<Report>(deletedReport, HttpStatus.OK);
	}
	
	@GetMapping("/reportId/{reportId}")
	public ResponseEntity<Report> ViewReportByIdHandler(@PathVariable("reportId") Integer reportId, @PathVariable("uuid") String uuid){
		Report report = rService.ViewReportById(reportId, uuid);
		return new ResponseEntity<Report>(report,HttpStatus.OK);
	}
	
	@GetMapping("/reports")
	public ResponseEntity<List<Report>> ViewAllReportsHandler(@PathVariable("uuid") String uuid){
		List<Report> reports = rService.ViewAllReport(uuid);
		return new ResponseEntity<List<Report>>(reports,HttpStatus.OK);
	}
}

