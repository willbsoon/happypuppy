package team.project.animalservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import team.project.service.NextPage;
import team.project.service.UserService;

import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

public class AnimalImageSearchService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println();
		System.out.println("2. AnimalImageSearchService Start");

		// 1. 파라미터 받아오기
//		String directoryPath = request.getServletContext().getRealPath("/animalImage/");
//		String directoryPath = request.get("/animalImage/");
		String directoryPath = "C:\\Users\\BS_Laptop\\images";
//		/happypuppy_web/WebContent/images
		String imageFilePath = "";
		int maxSize = 1024 * 1024 * 100;
		String encType = "UTF-8";
		MultipartRequest multipartRequest;
		File imageFile = null;

		String dogCode = "";
		NextPage nextPage = new NextPage();

		// 2.이미지 저장
		try {
			multipartRequest = new MultipartRequest(request, directoryPath, maxSize, encType);
			imageFile = multipartRequest.getFile("file");
			imageFilePath = imageFile.getAbsolutePath();

			System.out.println("directoryPath : " + directoryPath);
			System.out.println("filePath : " + imageFilePath);
			Process process = null;

			// 3. imageFilePath를 파라미터로 받는 CNN python파일 실행
			process = Runtime.getRuntime().exec("python /usr/local/tomcat/webapps/py/test.py " + imageFilePath);
			//process = Runtime.getRuntime().exec("python C:\\Users\\june\\Desktop\\test.py " + imageFilePath);
			//process = Runtime.getRuntime().exec("python C:\\Users\\apfhd\\Documents\\py_connector\\cnn_predict.py " + imageFilePath);
			process.waitFor();
			System.out.println("CNN python파일 실행 완료");

			// 4. return되는 OutputStream 받아오기
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				//5. dogCode값 저장
				dogCode = line;
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		// 4-1. AnimalImageSearchService성공
		if (dogCode != "") {
			request.getSession().setAttribute("colorCd", dogCode);
			//nextPage.setPageName(request.getContextPath() + "/jsp/animalimagesearchshow.jsp");
			nextPage.setPageName("AnimalImageSearchShow.puppy");
			nextPage.setRedirect(true);
		}

		// 4-2. AnimalImageSearchService실패
		else {
			request.setAttribute("errorMSG", "AnimalImageSearchService failed.");
			nextPage.setPageName(request.getContextPath() + "/error.jsp");
			nextPage.setRedirect(false);
		}
		return nextPage;
	}

}
