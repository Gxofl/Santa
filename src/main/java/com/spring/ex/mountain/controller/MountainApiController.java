package com.spring.ex.mountain.controller;

import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.InputSource;

import com.spring.ex.mountain.model.MountainBean;
import com.spring.ex.mountain.model.MountainDao;

import java.io.BufferedReader;

@Controller
public class MountainApiController {
	
	@Autowired
	static
	MountainDao mdao;

	//요청 값을 담은 변수
	private final static String command = "/mountain/admin/api.mnt";
	
	private static String getPage = "/mountain/mountainApiInsertList";
			
	@RequestMapping(command)
    public static ModelAndView main(String[] args) throws Exception {
    	
    	ModelAndView mav = new ModelAndView();
    	
        StringBuilder urlBuilder = new StringBuilder("http://api.forest.go.kr/openapi/service/trailInfoService/getforeststoryservice"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=4Nr4EpPOmzOdPQucRWM%2BxXD2SXtgJKTe4sXKKDUnfIJYnd8dx4pJ1Bgyhppi1m%2B1iNVym8TwDikVNKTrgklj1A%3D%3D"); /*Service Key*/
        //urlBuilder.append("&" + URLEncoder.encode("mntnNm","UTF-8") + "=" + URLEncoder.encode("지리산", "UTF-8")); /*산명*/
        //urlBuilder.append("&" + URLEncoder.encode("mntnHght","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*산정보 높이*/
        //urlBuilder.append("&" + URLEncoder.encode("mntnAdd","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*산 소재지*/
        //urlBuilder.append("&" + URLEncoder.encode("mntnInfoAraCd","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*산정보지역정보*/
        //urlBuilder.append("&" + URLEncoder.encode("mntnInfoSsnCd","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*산정보계절코드*/
        //urlBuilder.append("&" + URLEncoder.encode("mntnInfoThmCd","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*산정보주제코드*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        
        //나중에 사용할 map을 선언해줍니다.
        Map<String, Object> map = new HashMap<String, Object>();
        
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"UTF-8"));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
        
        List<HashMap<String, String>> list = getResultMap(sb.toString());
        
        List<String> mountainnum = new ArrayList<String>();
        List<String> mountainname = new ArrayList<String>();
        List<String> mountaingreat = new ArrayList<String>();
        List<String> mountainlocal = new ArrayList<String>();
        List<String> mountainheight = new ArrayList<String>();
        List<String> mountaincontent = new ArrayList<String>();
        List<String> mountainimage = new ArrayList<String>();

        for(int i = 0; i< list.size(); i++) {
            map.put("mountainnum", list.get(i).get("mntnid"));
            map.put("mountainname", list.get(i).get("mntnnm"));
            map.put("mountaingreat", list.get(i).get("hndfmsmtnslctnrson"));
            map.put("mountainlocal", list.get(i).get("mntninfopoflc"));
            map.put("mountainheight", list.get(i).get("mntninfohght"));
            map.put("mountaincontent", list.get(i).get("mntninfodtlinfocont"));
            map.put("mountainimage", list.get(i).get("mntnattchimageseq"));
            
//            int cnt = mdao.insertApi(map);
//            System.out.println("cnt : "+cnt);
            
            mountainnum.add(map.get("mountainnum").toString());
            mountainname.add(map.get("mountainname").toString());
            mountaingreat.add(map.get("mountaingreat").toString());
            mountainlocal.add(map.get("mountainlocal").toString());
            mountainheight.add(map.get("mountainheight").toString());
            mountaincontent.add(map.get("mountaincontent").toString());
            mountainimage.add(map.get("mountainimage").toString());

        }
        
        mav.addObject("mountainnum",mountainnum);
        mav.addObject("mountainname",mountainname);
        mav.addObject("mountaingreat",mountaingreat);
        mav.addObject("mountainlocal",mountainlocal);
        mav.addObject("mountainheight",mountainheight);
        mav.addObject("mountaincontent",mountaincontent);
        mav.addObject("mountainimage",mountainimage);
        
        mav.setViewName(getPage);
        
        return mav;
    }
    
    public static List<HashMap<String, String>> getResultMap(String data) throws Exception {
    	 
    	//결과값을 넣어줄 map을 선언해줍니다.
    	List<HashMap<String, String>> resultMap = new LinkedList<HashMap<String, String>>();
    	        
    	InputSource is = new InputSource(new StringReader(data));
    	       
    	//Document 클래스로 xml데이터를 취득합니다.
    	Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
    	 
    	//xPath 팩토리로 객체를 만듭니다.
    	XPath xpath = XPathFactory.newInstance().newXPath();
    	 
    	//xPath를 컴파일한 후에 node단위로 데이터를 수집합니다.
    	NodeList nodeList = (NodeList) xpath.compile("/response/body/items/item").evaluate(document, XPathConstants.NODESET);
    	int nodeListCount = nodeList.getLength();
    	for (int i = 0; i < nodeListCount; i++) {
    	    NodeList childNode = nodeList.item(i).getChildNodes();
    	    HashMap<String, String> nodeMap = new HashMap<String, String>();
    	    int childNodeCount = childNode.getLength();
    	    for (int j = 0; j < childNodeCount; j++) {
    	        nodeMap.put(childNode.item(j).getNodeName(), childNode.item(j).getTextContent());
    	    }
    	    resultMap.add(nodeMap);
    	}
    	 
    	return resultMap;
    	}
}