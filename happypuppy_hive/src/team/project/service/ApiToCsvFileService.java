package team.project.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import team.project.dto.Recommend;

public class ApiToCsvFileService {
	public static void pasing(Recommend rec, int select) throws IOException, ParserConfigurationException,
			TransformerFactoryConfigurationError, TransformerException {
		System.out.println("pasing");
		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
				+ "=pFgcEMtxZueqIzaDsd4rZ4yVCaBNK2t68oBu3BUwEcNDsPU6tXmQGP79pqPJtgq3oLfNz42UgdO2cmu2s9nrlg%3D%3D");
		urlBuilder.append("&" + URLEncoder.encode("bgnde", "UTF-8") + "=" + URLEncoder.encode(rec.getBgnde(),
				"UTF-8")); /* 유기날짜 (검색 시작일) (YYYYMMDD) */
		urlBuilder.append("&" + URLEncoder.encode("endde", "UTF-8") + "=" + URLEncoder.encode(rec.getEndde(),
				"UTF-8")); /* 유기날짜 (검색 종료일) (YYYYMMDD) */
		urlBuilder.append("&" + URLEncoder.encode("upkind", "UTF-8") + "=" + URLEncoder.encode(rec.getUpkind(),
				"UTF-8")); /* 축종코드 - 개 : 417000 - 고양이 : 422400 - 기타 : 429900 */
		urlBuilder.append("&" + URLEncoder.encode("kind", "UTF-8") + "=" + URLEncoder.encode("",
				"UTF-8")); /* 품종코드 (품종 조회 OPEN API 참조) */
		urlBuilder.append("&" + URLEncoder.encode("upr_cd", "UTF-8") + "=" + URLEncoder.encode(rec.getUpr_cd(),
				"UTF-8")); /* 시도코드 (시도 조회 OPEN API 참조) */
		urlBuilder.append("&" + URLEncoder.encode("org_cd", "UTF-8") + "=" + URLEncoder.encode("",
				"UTF-8")); /* 시군구코드 (시군구 조회 OPEN API 참조) */
		urlBuilder.append("&" + URLEncoder.encode("care_reg_no", "UTF-8") + "=" + URLEncoder.encode("",
				"UTF-8")); /* 보호소번호 (보호소 조회 OPEN API 참조) */
		urlBuilder.append("&" + URLEncoder.encode("state", "UTF-8") + "=" + URLEncoder.encode("",
				"UTF-8")); /*
							 * 상태 - 전체 : null(빈값) - 공고중 : notice - 보호중 : protect
							 */
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
				+ URLEncoder.encode("1", "UTF-8")); /* 페이지 번호 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("10000", "UTF-8")); /* 페이지당 보여줄 개수 */

		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		StringBuilder html = new StringBuilder();

		// ====================================================================================
		// 파싱하여 xml로 저장하는 부분

		conn.setConnectTimeout(100000);
		conn.setUseCaches(false);
		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			File file = new File("/home/eduuser/project/xml", "dog.xml"); // 경로에 xml파일로 저장
			FileOutputStream fileOutput = new FileOutputStream(file);
			InputStream inputStream = conn.getInputStream();

			int downloadedSize = 0;
			byte[] buffer = new byte[1024];
			int bufferLength = 0;

			while ((bufferLength = inputStream.read(buffer)) > 0) {
				fileOutput.write(buffer, 0, bufferLength);
				downloadedSize += bufferLength;
			}
			fileOutput.close();
		}
		conn.disconnect();
		System.out.println("xml파일 생성 완료");
		// ============================================================================================
		// xml파일을 csv파일로 만드는 부분
		String csvNameDaily = rec.getBgnde(); // 시작날짜 가 하루 csv파일의 제목임
		String csvNameMonthly = rec.getBgnde() + "_" + rec.getEndde(); // 시작날짜_끝날짜가 한달 csv파일의 제목임
		File stylesheet = new File("/home/eduuser/project/xml/style_final.xsl"); // xsl형태로 xml파일 변환
		File xmlSource = new File("/home/eduuser/project/xml/dog.xml"); // 받아온 xml파일경로이다. 위의 경로와 같음

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = null;
		try {
			document = builder.parse(xmlSource);
			System.out.println("document 생성 완료");
		} catch (SAXException e) {
			System.out.println("document 생성 실패");
			e.printStackTrace();
		}

		StreamSource stylesource = null;
		stylesource = new StreamSource(stylesheet);
		Transformer transformer = null;
		transformer = TransformerFactory.newInstance().newTransformer(stylesource);
		Source source = null;
		source = new DOMSource(document);
		Process process = null;
		
		if (select==0) { // 하루에 한번// forproject.traincsv
			//리눅스 서버에 저장
			process = null;
			String command_mkdir = "mkdir /home/eduuser/project/csv/daily/train/success";
			process = Runtime.getRuntime().exec(command_mkdir);
			try {
				process.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Result outputTarget = new StreamResult(new File("/home/eduuser/project/csv/daily/train/success/" + csvNameDaily + ".csv")); // csv저장경로 및 이름설정
			transformer.transform(source, outputTarget);
			System.out.println("csv 생성 완료");
			//하둡에 저장
			process = null;
			String command_put = "/home/eduuser/hadoop-2.7.3/bin/hdfs dfs -put /home/eduuser/project/csv/daily/train/success/"+ csvNameDaily + ".csv /project/csv/daily/train/" + csvNameDaily + ".csv";
			process = Runtime.getRuntime().exec(command_put);
			try {
				System.out.println("train 하둡에 저장하기 위해 기다림 waitFor()");
				process.waitFor();
				System.out.println("train 하둡에 하루치 저장완료");
			} catch (InterruptedException e) {
				System.out.println("train 하둡에 하루치 저장실패");
				e.printStackTrace();
			}
			process = null;
			String command_rm = "rm -r /home/eduuser/project/csv/daily/train/success";
			process = Runtime.getRuntime().exec(command_rm);
			try {
				System.out.println("리눅스 train success 디렉토리 제거하기 위해 기다림 waitFor()");
				process.waitFor();
				System.out.println("리눅스 train success 디렉토리 제거완료");
			} catch (InterruptedException e) {
				System.out.println("리눅스 train success 디렉토리 제거실패");
				e.printStackTrace();
			}
		}
		
		else if (select==1) { // 하루에 한번// forproject.month3csv
			//리눅스 서버에 저장
			//Result outputTarget = new StreamResult(new File("c://Users/june/Desktop/test/" + "daily_csv2" + ".csv")); // csv저장경로 및 이름설정
			process = null;
			String command_mkdir = "mkdir /home/eduuser/project/csv/daily/month3/success";
			process = Runtime.getRuntime().exec(command_mkdir);
			try {
				process.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Result outputTarget = new StreamResult(new File("/home/eduuser/project/csv/daily/month3/success/" + csvNameDaily + ".csv")); // csv저장경로 및 이름설정
			transformer.transform(source, outputTarget);
			System.out.println("csv 생성 완료");
			//하둡에 저장
			process = null;
			String command_put = "/home/eduuser/hadoop-2.7.3/bin/hdfs dfs -put /home/eduuser/project/csv/daily/month3/success/"+ csvNameDaily + ".csv /project/csv/daily/month3/" + csvNameDaily + ".csv";
			process = Runtime.getRuntime().exec(command_put);
			try {
				System.out.println("month3csv 하둡에 저장하기 위해 기다림 waitFor()");
				process.waitFor();
				System.out.println("month3csv 하둡에 하루치 저장완료");
			} catch (InterruptedException e) {
				System.out.println("month3csv 하둡에 하루치 저장실패");
				e.printStackTrace();
			}
			process = null;
			String command_rm = "rm -r /home/eduuser/project/csv/daily/month3/success";
			process = Runtime.getRuntime().exec(command_rm);
			try {
				System.out.println("리눅스 month3csv success 디렉토리 제거하기 위해 기다림 waitFor()");
				process.waitFor();
				System.out.println("리눅스 month3csv success 디렉토리 제거완료");
			} catch (InterruptedException e) {
				System.out.println("리눅스 month3csv success 디렉토리 제거실패");
				e.printStackTrace();
			}
		}
		
		//매일 6개월치
		else if(select==2){
			//리눅스 서버에 저장
			Result outputTarget = new StreamResult(new File("/home/eduuser/project/csv/daily/month/" + "month_csv" + ".csv")); // csv저장경로 및 이름설정
			transformer.transform(source, outputTarget);
			System.out.println("csv 생성 완료");
			//하둡에 저장
			process = null;
			String command_day = "/home/eduuser/hadoop-2.7.3/bin/hdfs dfs -put /home/eduuser/project/csv/daily/month/month_csv.csv /project/csv/daily/month/" + csvNameMonthly + ".csv";
			process = Runtime.getRuntime().exec(command_day);
			try {
				System.out.println("month 하둡에 저장하기 위해 기다림 waitFor()");
				process.waitFor();
				System.out.println("month 하둡에 6개월치 저장완료");
			} catch (InterruptedException e) {
				System.out.println("month 하둡에 하루치 저장실패");
				e.printStackTrace();
			}
		}
		
		else if(select==3){// 한달에 한번
			//리눅스 서버에 저장
			Result outputTarget = new StreamResult(new File("/home/eduuser/project/csv/monthly/" + "monthly_csv" + ".csv")); // csv저장경로 및 이름설정
			transformer.transform(source, outputTarget);
			System.out.println("csv 생성 완료");
			//하둡에 저장
			process = null;
			String command_month = "/home/eduuser/hadoop-2.7.3/bin/hdfs dfs -put /home/eduuser/project/csv/monthly/monthly_csv.csv /project/csv/monthly/" + csvNameMonthly + ".csv";
			process = Runtime.getRuntime().exec(command_month);
			try {
				System.out.println("monthly 하둡에 저장하기 위해 기다림 waitFor()");
				process.waitFor();
				System.out.println("monthly 하둡에 6개월치 저장완료");
			} catch (InterruptedException e) {
				System.out.println("monthly 하둡에 하루치 저장실패");
				e.printStackTrace();
			}
		}
		
		
		else if(select==4){// 테스트용
			//리눅스 서버에 저장
			process = null;
			String command_mkdir = "mkdir /home/eduuser/project/csv/daily/month3/success";
			process = Runtime.getRuntime().exec(command_mkdir);
			Result outputTarget = new StreamResult(new File("/home/eduuser/project/csv/daily/month3/success/" + "month3" + ".csv")); // csv저장경로 및 이름설정
			transformer.transform(source, outputTarget);
			System.out.println("csv 생성 완료");
			//하둡에 저장
			process = null;
			String command_test = "/home/eduuser/hadoop-2.7.3/bin/hdfs dfs -put /home/eduuser/project/csv/daily/month3/success/month3.csv /project/csv/daily/month3/" + "month3" + ".csv";
			process = Runtime.getRuntime().exec(command_test);
			try {
				System.out.println("하둡에 저장하기 위해 기다림 waitFor()");
				process.waitFor();
				System.out.println("하둡에 테스트 저장완료");
			} catch (InterruptedException e) {
				System.out.println("하둡에 테스트 저장실패");
				e.printStackTrace();
			}
			process = null;
			String command_rm = "rm -r /home/eduuser/project/csv/daily/month3/success";
			process = Runtime.getRuntime().exec(command_rm);
			try {
				System.out.println("리눅스 테스트 success 디렉토리 제거하기 위해 기다림 waitFor()");
				process.waitFor();
				System.out.println("리눅스 테스트 success 디렉토리 제거완료");
			} catch (InterruptedException e) {
				System.out.println("리눅스 테스트 success 디렉토리 제거실패");
				e.printStackTrace();
			}	
		}
		
		else if(select==5){// 테스트용
			//리눅스 서버에 저장
			process = null;
			String command_mkdir = "mkdir /home/eduuser/project/csv/test/sec_2/success";
			process = Runtime.getRuntime().exec(command_mkdir);
			try {
				process.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Result outputTarget = new StreamResult(new File("/home/eduuser/project/csv/test/sec_2/success/" + csvNameDaily + ".csv")); // csv저장경로 및 이름설정
			transformer.transform(source, outputTarget);
			System.out.println("csv 생성 완료");
			//하둡에 저장
			process = null;
			String command_test = "/home/eduuser/hadoop-2.7.3/bin/hdfs dfs -put /home/eduuser/project/csv/test/sec_2/success/"+ csvNameDaily +".csv /project/csv/test/sec_2/" + csvNameDaily + ".csv";
			process = Runtime.getRuntime().exec(command_test);
			try {
				System.out.println("하둡에 저장하기 위해 기다림 waitFor()");
				process.waitFor();
				System.out.println("하둡에 테스트 저장완료");
			} catch (InterruptedException e) {
				System.out.println("하둡에 테스트 저장실패");
				e.printStackTrace();
			}
			process = null;
			String command_rm = "rm -r /home/eduuser/project/csv/test/sec_2/success";
			process = Runtime.getRuntime().exec(command_rm);
			try {
				System.out.println("리눅스 테스트 success 디렉토리 제거하기 위해 기다림 waitFor()");
				process.waitFor();
				System.out.println("리눅스 테스트 success 디렉토리 제거완료");
			} catch (InterruptedException e) {
				System.out.println("리눅스 테스트 success 디렉토리 제거실패");
				e.printStackTrace();
			}
		}
	}
}
