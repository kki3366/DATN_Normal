package com.DATN.Entity.Report;

import java.util.Date;

public class ReportCommentPerDay {

	Date date;
	long quanlityComment;
	public ReportCommentPerDay(Date date, long quanlityComment) {
		super();
		this.date = date;
		this.quanlityComment = quanlityComment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public long getQuanlityComment() {
		return quanlityComment;
	}
	public void setQuanlityComment(long quanlityComment) {
		this.quanlityComment = quanlityComment;
	}
	
	
	
}
