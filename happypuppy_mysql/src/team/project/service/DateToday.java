package team.project.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import team.project.dto.Time_now;

/**
 * 현재 날짜와 시간 설정 service
 * 중복의 제거위해 사용함
 * @author june
 *
 */
public class DateToday {
	public Time_now getDate() {
		System.out.println("DateToday의 Time_now getDate() : 현재 날짜와 어제의 날짜를 만들고 Time_now 객체에 저장");
		Calendar calendar = new GregorianCalendar(Locale.KOREA);
		SimpleDateFormat todayf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat today_f = new SimpleDateFormat("yyyy-MM-dd");//하이픈 있는거
		SimpleDateFormat timef = new SimpleDateFormat("kk:mm:ss");
		SimpleDateFormat dayf = new SimpleDateFormat("dd");
		String simpletoday = todayf.format(calendar.getTime());//yyyyMMdd
		String simple_today = today_f.format(calendar.getTime());//yyyy-MM-dd
		String timetoday = timef.format(calendar.getTime());//kk:mm:ss
		String day = dayf.format(calendar.getTime());//dd
		System.out.println("오늘날짜 : " + simple_today);
		calendar.add(Calendar.DAY_OF_YEAR, -1);// 하루전 날짜
		String simple_before = today_f.format(calendar.getTime());// 하루전 1일
		System.out.println("어제날 : " + simple_before);
		calendar.add(Calendar.DAY_OF_YEAR, +1);// 날짜 초기화
		
		
		//=======api 6개월전==============================
		Calendar calendar2 = new GregorianCalendar(Locale.KOREA);
		calendar2.add(Calendar.MONTH, -6);
		String before6Month_start = today_f.format(calendar2.getTime());
		System.out.println("6개월전 : " + before6Month_start);

		Time_now now = new Time_now(simpletoday, simple_today, timetoday, simple_before, day, before6Month_start);
		
		return now;
	}
}
