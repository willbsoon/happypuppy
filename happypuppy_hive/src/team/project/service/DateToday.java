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

		calendar.add(Calendar.DAY_OF_YEAR, -1);// 하루전 날짜
		String simple_before = today_f.format(calendar.getTime());// 하루전 1일
		calendar.add(Calendar.DAY_OF_YEAR, +1);// 날짜 초기화
		
		
		//==API===========================================================
		//=======api 3개월전 1일 ~ 마지막일 ==============================
		//3개월 전 1일
		calendar.add(Calendar.MONTH, -3); // 세달을 빼준다.
		calendar.add(Calendar.DAY_OF_YEAR, -24);// 25-24 = 1일로 만들어 준다.
		String before3Month_start = todayf.format(calendar.getTime());
		//System.out.println("3개월전 1일 : " + before3Month_start);
		//3개월전 마지막일
		calendar.add(Calendar.MONTH, +1); // 세달을 빼준다.
		calendar.add(Calendar.DAY_OF_YEAR, -1);//마지막일로 만들어 준다.
		String before3Month_end = todayf.format(calendar.getTime());
		//System.out.println("3개월전 마지막일 : " + before3Month_end);
		
		//=======api 1개월전==============================
		Calendar calendar2 = new GregorianCalendar(Locale.KOREA);
		calendar2.add(Calendar.MONTH, -1); // 한달을 빼준다.
		String before1Month_start = today_f.format(calendar2.getTime());//yyyy-MM-dd
		
		//=======api 6개월전==============================
		calendar2.add(Calendar.MONTH, -5);
		String before6Month_start = todayf.format(calendar2.getTime());
		//System.out.println("6개월전 시작일 : " + before6Month_start);
		
		//=======api 2주전==============================
		Calendar calendar3 = new GregorianCalendar(Locale.KOREA);
		calendar3.add(Calendar.DAY_OF_YEAR, -14);
		String before2weak = todayf.format(calendar3.getTime());
		System.out.println("before2weak : "+ before2weak);
		//=======api 3개월전==============================
		calendar3.add(Calendar.DAY_OF_YEAR, -8);
		calendar3.add(Calendar.MONTH, -2);		
		String before3Month = todayf.format(calendar3.getTime());
		System.out.println("before3Month : " + before3Month);
		
		
		
		Time_now now = new Time_now(simpletoday, simple_today, timetoday, 
				simple_before, day, before3Month_start, before3Month_end, 
				before1Month_start, before6Month_start, before3Month, before2weak);
		
		
		return now;
	}
}
