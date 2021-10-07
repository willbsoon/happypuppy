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
	private String before6Month_start;
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
	public Time_now(String simpletoday, String simple_today, String timetoday, String simple_before, String day,
			String before6Month_start) {
		super();
		this.simpletoday = simpletoday;
		this.simple_today = simple_today;
		this.timetoday = timetoday;
		this.simple_before = simple_before;
		this.day = day;
		this.before6Month_start = before6Month_start;
	}
	
	
	
	
}
