package team.project.mains;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import team.project.dto.Admin;
import team.project.dto.Time_now;
import team.project.service.DateToday;
import team.project.service.RdbmsService;

public class mains {

	public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		System.out.println("==main문 실행============================================");	
		
		// 오늘 날짜와 시간 받아옴
		Time_now now = new DateToday().getDate();
		
		// RDBMS시작=======================================================================
		System.out.println("RDBMS시작");
		// 1. admin객체 및 날짜 선언
		Admin admin = new Admin();
		RdbmsService rdbms = new RdbmsService();
		
		// 2.1 dailybatch 시작(insertDaily)
		System.out.println("1.1 RdbmsService의 dailybatch(now, admin)실행");
		rdbms.dailybatch(now, admin);
		
		// 2.2 allbatch 시작(insertAll)
		System.out.println("1.2 RdbmsService의 allbatch(admin)실행");
		rdbms.allbatch(now, admin);
	
		// 2.3 monthlybatch 시작
		if (now.getDay().equals("20")) { // 매달 20일마다 실행 //2017-08-20
			System.out.println("1.3 RdbmsService의 monthlybatch(admin)실행");
			rdbms.monthlybatch(now, admin);
		}	

		// RDBMS끝=========================================================================
	}
}
