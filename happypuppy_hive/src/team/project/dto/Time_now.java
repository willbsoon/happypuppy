package team.project.dto;
/**
 * DateToday.java와 관련있음
 * @author june
 *
 */
public class Time_now {
	private String simpletoday;//yyyyMMdd
	private String simple_today;//yyyy-MM-dd
	private String timetoday;//hh:mm:ss
	private String simple_before;//어제의 yyyy-MM-dd
	private String day;//오늘 dd
	private String before3Month_start;
	private String before3Month_end;
	private String before1Month_start;
	private String before6Month_start;
	private String before3Month;
	private String before2weak;
	
	
	
	public Time_now(String simpletoday, String simple_today, String timetoday, String simple_before, String day,
			String before3Month_start, String before3Month_end, String before1Month_start, String before6Month_start,
			String before3Month, String before2weak) {
		super();
		this.simpletoday = simpletoday;
		this.simple_today = simple_today;
		this.timetoday = timetoday;
		this.simple_before = simple_before;
		this.day = day;
		this.before3Month_start = before3Month_start;
		this.before3Month_end = before3Month_end;
		this.before1Month_start = before1Month_start;
		this.before6Month_start = before6Month_start;
		this.before3Month = before3Month;
		this.before2weak = before2weak;
	}
	public String getBefore3Month() {
		return before3Month;
	}
	public void setBefore3Month(String before3Month) {
		this.before3Month = before3Month;
	}
	public String getBefore2weak() {
		return before2weak;
	}
	public void setBefore2weak(String before2weak) {
		this.before2weak = before2weak;
	}
	public String getBefore3Month_start() {
		return before3Month_start;
	}
	public void setBefore3Month_start(String before3Month_start) {
		this.before3Month_start = before3Month_start;
	}
	public String getBefore3Month_end() {
		return before3Month_end;
	}
	public void setBefore3Month_end(String before3Month_end) {
		this.before3Month_end = before3Month_end;
	}
	public String getBefore1Month_start() {
		return before1Month_start;
	}
	public void setBefore1Month_start(String before1Month_start) {
		this.before1Month_start = before1Month_start;
	}
	public String getSimpletoday() {
		return simpletoday;
	}
	public void setSimpletoday(String simpletoday) {
		this.simpletoday = simpletoday;
	}
	public String getSimple_today() {
		return simple_today;
	}
	public void setSimple_today(String simple_today) {
		this.simple_today = simple_today;
	}
	public String getTimetoday() {
		return timetoday;
	}
	public void setTimetoday(String timetoday) {
		this.timetoday = timetoday;
	}
	public String getSimple_before() {
		return simple_before;
	}
	public void setSimple_before(String simple_before) {
		this.simple_before = simple_before;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
	public String getBefore6Month_start() {
		return before6Month_start;
	}
	public void setBefore6Month_start(String before6Month_start) {
		this.before6Month_start = before6Month_start;
	}
	
}
