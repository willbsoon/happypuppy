package team.project.service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import team.project.dto.Recommend;

public class NoticePasing {
	public static int INDENT_FACTOR = 4;
	
	public JSONObject pasing(Recommend rec) throws IOException, JSONException {
   
		StringBuilder urlBuilder = new StringBuilder("http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=pFgcEMtxZueqIzaDsd4rZ4yVCaBNK2t68oBu3BUwEcNDsPU6tXmQGP79pqPJtgq3oLfNz42UgdO2cmu2s9nrlg%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("bgnde","UTF-8") + "=" + URLEncoder.encode(rec.getBgnde(), "UTF-8")); /*유기날짜 (검색 시작일) (YYYYMMDD) */
        urlBuilder.append("&" + URLEncoder.encode("endde","UTF-8") + "=" + URLEncoder.encode(rec.getEndde(), "UTF-8")); /*유기날짜 (검색 종료일) (YYYYMMDD) */
        urlBuilder.append("&" + URLEncoder.encode("upkind","UTF-8") + "=" + URLEncoder.encode(rec.getUpkind(), "UTF-8")); /*축종코드 - 개 : 417000 - 고양이 : 422400 - 기타 : 429900 */
        urlBuilder.append("&" + URLEncoder.encode("kind","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*품종코드 (품종 조회 OPEN API 참조) */
        urlBuilder.append("&" + URLEncoder.encode("upr_cd","UTF-8") + "=" + URLEncoder.encode(rec.getUpr_cd(), "UTF-8")); /*시도코드 (시도 조회 OPEN API 참조) */
        urlBuilder.append("&" + URLEncoder.encode("org_cd","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*시군구코드 (시군구 조회 OPEN API 참조) */
        urlBuilder.append("&" + URLEncoder.encode("care_reg_no","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*보호소번호 (보호소 조회 OPEN API 참조) */
        urlBuilder.append("&" + URLEncoder.encode("state","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*상태 - 전체 : null(빈값) - 공고중 : notice - 보호중 : protect */
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(rec.getNumOfRows(), "UTF-8")); /*페이지당 보여줄 개수*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        
        JSONObject xmlJSONObj = XML.toJSONObject(sb.toString());
        System.out.println("NotieceParsing Finished");
		return xmlJSONObj;

    }
}