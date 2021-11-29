package dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class BoardDTO {
	private int seq;
	private String writer;
	private String title;
	private String contents;
	private Timestamp write_date;
	private int view_count;
	
	public BoardDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BoardDTO(int seq, String writer, String title, String contents, Timestamp write_date, int view_count) {
		super();
		this.seq = seq;
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.write_date = write_date;
		this.view_count = view_count;
	}
	public final int getSeq() {
		return seq;
	}
	public final void setSeq(int seq) {
		this.seq = seq;
	}
	public final String getWriter() {
		return writer;
	}
	public final void setWriter(String writer) {
		this.writer = writer;
	}
	public final String getTitle() {
		return title;
	}
	public final void setTitle(String title) {
		this.title = title;
	}
	public final String getContents() {
		return contents;
	}
	public final void setContents(String contents) {
		this.contents = contents;
	}
	public final Timestamp getWrite_date() {
		return write_date;
	}
	public final void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}
	public final int getView_count() {
		return view_count;
	}
	public final void setView_count(int view_count) {
		this.view_count = view_count;
	}
	
	// 날짜를 가공해서 출력
	public String getFormedDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm");
		return sdf.format(write_date.getTime());
	}
	// 몇분전으로 날짜 뜨게 하는 메소드
	public String getDetailDate() {
		long current_time =System.currentTimeMillis();  // 현재 Timestamp
		long write_time = this.write_date.getTime();  // 글이 작성된 시점의 Timestamp

		long time_gap = current_time - write_time;

		if(time_gap < 60000) {
			return "1분 이내";
		}else if(time_gap < 300000){
			return "5분 이내";
		}else if(time_gap < 3600000) {
			return "1시간 이내";
		}else if(time_gap < 86400000) {
			return "오늘";
		}else {
			return getFormedDate();
		}
	}
}
