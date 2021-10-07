package team.project.mains;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import team.project.dto.City;
import team.project.dto.Time_now;
import team.project.service.DateToday;
import team.project.service.RdbmsService;

public class mains {

	public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		System.out.println("==main문 실행============================================");	
		
		// 오늘 날짜와 시간 받아옴
		Time_now now = new DateToday().getDate();
		City city = new City();
		// RDBMS시작=======================================================================
		System.out.println("RDBMS시작");
		RdbmsService rdbms = new RdbmsService();
		ArrayList cityList= new ArrayList();
		cityList.add(city.seoul);
		cityList.add(city.busan);
		cityList.add(city.daegu);
		cityList.add(city.incheon);
		cityList.add(city.gwangjoo);
		cityList.add(city.sejong);
		cityList.add(city.daejeon);
		cityList.add(city.ulsan);
		cityList.add(city.gyeonggido);
		cityList.add(city.gangwondo);
		cityList.add(city.chungcheongbukdo);
		cityList.add(city.chungcheongnamdo);
		cityList.add(city.jeollabukdo);
		cityList.add(city.jeollanamdo);
		cityList.add(city.gyeongsangbukdo);
		cityList.add(city.gyeongsangnamdo);
		cityList.add(city.jeju);
		// 2.1 dailybatch 시작(insertDaily)
		System.out.println("1.1 RdbmsService의 dailybatch(now, admin)실행");
		rdbms.Introdailybatch(now, cityList);
		

		// RDBMS끝=========================================================================
	}
}
