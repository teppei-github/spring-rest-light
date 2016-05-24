package njc.framework.springbase;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * SpringFrameworkの共通クラス
 * @author n-koreeda
 * @version 0.1
 */
@Controller
@EnableWebMvc
public class RequestReciever {
	
	@ResponseBody
	@RequestMapping(value = "/api/{api_content}/", headers="Accept=*/*",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public BlockRestResponce content_rest_api(@PathVariable String api_content,
			HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody(required = false) Map<String, Object> input // GETリクエストはRequestBody無効なので、required = false
            ) {
		//============================
		// 変数準備
		//============================
		// 返り値用意
		BlockRestResponce result = new BlockRestResponce(); 
		// Method取得
		String methodType = request.getMethod();
		// Database
		CommonDBAccesser database = new CommonDBAccesser();
		// null時初期化
		if(input == null){
			input = new HashMap<String, Object>();
		}
		
		try {
			//============================
			// Database接続要求
			//============================
			database.openDatabaseSource();

			//============================
			// リクエストマッピング
			//============================
			BlockRestInterface ContentClass = RequestMapper.searchRestClass(CommonPara.REQUEST_TYPE_RESTAPI, api_content,methodType);
			
			// コンテンツクラスのチェック
			if(ContentClass == null){// 不正なリクエストを全てDBに保存するとログでいっぱいになるので、NOT_FOUNDを返す。不正リクエストはApacheのログを見る
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				result.setStatusError(CommonPara.STATUS_RESPONSE_ERROR, CommonMsg.REQ_CONTENT_ERROR_NOTFOUND);
				return result;
			}

			//============================
			// コンテンツクラス実行
			//============================
			Boolean content_resultBoolean;
			
			if (methodType.equals("GET")) {
				// 改修更新日を出力する
				response.setDateHeader("Last-Modified", new Date().getTime());
				
				// GET はRequestBodyが使えないので、クエリを利用する
				if (request.getParameterMap() != null) {
					Map<?,?> reqMap = request.getParameterMap();
					Set<?> keyset = reqMap.keySet();
					
					Iterator<?> iteKey = keyset.iterator();
					while (iteKey.hasNext()) {
						String key = String.valueOf(iteKey.next());
						input.put(key, request.getParameter(key));
					}
				}
				content_resultBoolean = ContentClass.get(database, request, input, methodType, result);
				
			} else if (methodType.equalsIgnoreCase("POST")) {
				content_resultBoolean = ContentClass.post(database, request, input, methodType, result);
			} else if (methodType.equalsIgnoreCase("PUT")) {
				content_resultBoolean = ContentClass.put(database, request, input, methodType, result);
			} else if (methodType.equalsIgnoreCase("DELETE")) {
				content_resultBoolean = ContentClass.delete(database, request, input, methodType, result);
			} else {
				content_resultBoolean = false;
			}
			// 処理結果のチェック
			if (content_resultBoolean == false) {// エラー
				throw new Exception(CommonMsg.REQ_CONTENT_ERROR_OTHER);
			}
			
			//============================
			// 返り値設定
			//============================
			// Http Respoce Code設定
			if (result.status == CommonPara.STATUS_RESPONSE_SUCCESS) {
				response.setStatus(HttpServletResponse.SC_OK);
			} else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			// 出力時間を更新
			result.updateResponceDate();
			
		} catch (Exception e) {
			//============================
			// エラーハンドリング
			//============================
			CommonLog.error(CommonPara.REQUEST_TYPE_RESTAPI, e.getMessage(), e);
			result.setStatusError(CommonPara.STATUS_RESPONSE_ERROR, e.getMessage());
		} finally{
			//============================
			// Database切断要求
			//============================
			try {
				database.closeDatabaseSource();
			} catch (Exception e) {
				CommonLog.error(CommonPara.REQUEST_TYPE_RESTAPI, CommonMsg.DATABASE_ERROR_DISCONNECT, e);
			}
		}
		
		// JSONを返す
		return result;
	}
	
	/**
	 * JSPリクエスト受付
	 * @param api_content
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/content/{api_content}")
	public String content_jsp(@PathVariable String api_content,
			HttpServletRequest request,
			HttpServletResponse response,
			Model model) {
		//============================
		// 変数準備
		//============================
		// コンテンツクラス
		BlockJspAbstruct ContentClass = null;
		// 返り値用意
		BlockJspResponce result = new BlockJspResponce(); 
		// Method取得
		String methodType = request.getMethod();
		// Database
		CommonDBAccesser database = new CommonDBAccesser();
		
		try {
			//============================
			// Database接続要求
			//============================
			database.openDatabaseSource();
			
			//============================
			// リクエストマッピング
			//============================
			ContentClass = RequestMapper.searchJspClass(CommonPara.REQUEST_TYPE_JSP,api_content,methodType);
			
			// サブシステムのチェック
			if( ContentClass == null ){// 不正なリクエストの場合、NOT_FOUNDを返す。不正リクエストはApacheのログを見る
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				result.setStatusError(CommonPara.STATUS_RESPONSE_ERROR,CommonMsg.REQ_CONTENT_ERROR_NOTFOUND);
				model.addAttribute("error_message", CommonMsg.REQ_CONTENT_ERROR_NOTFOUND);
				return CommonPara.TEMPLATE_JSP_ERROR;
			}
			
			//============================
			// コンテンツクラス実行
			//============================
			Boolean content_resultBoolean = ContentClass.run(database,methodType, request, response,  model, result);
			// 処理結果のチェック
			if( content_resultBoolean == false ){// エラー
				throw new Exception( CommonMsg.REQ_CONTENT_ERROR_OTHER );
			}
			
			//============================
			// 返り値設定
			//============================
			// Http Responce Code設定
			if( result.status == CommonPara.STATUS_RESPONSE_SUCCESS ){
				response.setStatus(HttpServletResponse.SC_OK);
			}else{
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			//============================
			// エラーハンドリング
			//============================
			String error_msg = e.getMessage();
			CommonLog.error(CommonPara.REQUEST_TYPE_JSP, error_msg, e);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			model.addAttribute("error_message", error_msg);

			return CommonPara.TEMPLATE_JSP_ERROR;
		} finally{
			//============================
			// Database切断要求
			//============================
			try {
				database.closeDatabaseSource();
			} catch (Exception e) {
				CommonLog.error(CommonPara.REQUEST_TYPE_JSP, CommonMsg.DATABASE_ERROR_DISCONNECT, e);
			}
		}
		
		return ContentClass.templateJsp;
	}
}
