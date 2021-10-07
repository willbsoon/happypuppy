package team.project.animalservice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import au.com.bytecode.opencsv.CSVReader;
import team.project.service.NextPage;
import team.project.service.UserService;

//최근 유기동물을 보여주는 서비스
public class AnimalChartService implements UserService {
	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("2. AnimalChartService Start");
		NextPage nextPage = new NextPage();
		/*String[] careAddr = { "서울특별시", "부산광역시", "대구광역시", "인천광역시", "광주광역시",
				"세종특별자치시", "대전광역시", "울산광역시", "경기도", "강원도",
				"충청북도", "충청남도", "전라북도", "전라남도", "경상북도",
				"경상남도", "제주특별자치도" };
		HashMap<String, Integer> care = new HashMap<>();
		for (int i = 0; i < careAddr.length; i++) {
			care.put(careAddr[i], 0);
		}

		String local = "";
		 */
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("C://Users/june/Desktop/강원도170101_170824.csv"), "EUC-KR");
			CSVReader reader = new CSVReader(inputStreamReader);
			List<String[]> list = reader.readAll();
			for (int i=0; i<list.size();i++) {
				System.out.println();
				String [] str = list.get(i);
				//모두 출력
				/*for (int j=0; j<str.length;j++) {
					System.out.print(str[j] + ",");
				}*/
				//공고일 출력
				System.out.print(str[10].substring(6));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		int result = 1;
		if (result != 0) {
			System.out.println("널값 아니다");
			nextPage.setPageName("./graph.jsp");
			nextPage.setRedirect(false);
		}

		// 4-2. AnimalDetailService성공
		else {
			System.out.println("널값이다");
			request.setAttribute("errorMSG", "AnimalDetailService Failed.");
			nextPage.setPageName(request.getContextPath() + "/error.jsp");
			nextPage.setRedirect(false);
		}

		return null;

	}
}