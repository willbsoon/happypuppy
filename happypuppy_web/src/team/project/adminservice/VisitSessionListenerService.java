package team.project.adminservice;
import java.sql.SQLException;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import team.project.dao.AdminDao;
import team.project.dto.Time_now;
//세션이 새로 생성되면 총방문자수 증가

public class VisitSessionListenerService implements HttpSessionListener {
	
	////세션이 새로 생성될 경우 실행됨 즉 한명이라도 접속할경우 실행되는 서비스
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
//		HttpSessionListener.super.sessionCreated(se);
		System.out.println("sessionCreated()");
		if(se.getSession().isNew()){// 세션이 새로 생성되면 visitcount()을 실행한다.
			visitcount();//총 방문자수 증가시킴 

		}
	}
	
    ////세션이 끝날 경우 실행됨 즉 한명이라도 접속해제 할경우 실행되는 서비스
	//세션이 계속 죽어있어도 new Schedule().main(); 을 실행함
	//--------------------------------------------------------------------------
	//***즉 사이트가 한번이라도 켜지거나 꺼지면 주기적으로 Schedule을 실행함***
	//--------------------------------------------------------------------------
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
//		HttpSessionListener.super.sessionDestroyed(se);
		System.out.println("sessionDestroyed()");
	}

	//총방문자수 증가
	//AdminDao의 insertVisit(Time_now now) 메소드를 실행
	public void visitcount() {
        try {
        	Time_now now = new DateToday().getDate();
        	AdminDao.getInstance().insertVisit(now);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
