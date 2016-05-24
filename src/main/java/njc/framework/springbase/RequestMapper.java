package njc.framework.springbase;

import java.util.HashMap;
import java.util.Map;

/**
 * 共通リクエストマッピングクラス
 * 該当のコンテンツクラス名を返す
 * @author n-koreeda
 * @version 0.1
 */
public class RequestMapper {
	
	public static BlockRestInterface searchRestClass(int request_type, String content_name,String method) {
		BlockRestInterface result = null;
		try{
			//=======================
			//　コンテンツクラス取得
			//=======================
			Map<?,?> resMap = getMappingMap(request_type, content_name, method);
			if( resMap == null ){// 該当クラスが登録されてない。
				return null;
			}
			// クラス名取得
			String ContentClassName = resMap.get("CLASS_NAME").toString();
			
			// リフレクションでクラスを読み込む
			Class<?> class1 = Class.forName(ContentClassName);
			result = (BlockRestInterface) class1.newInstance();
			
		} catch (ClassNotFoundException e) { // リフレクションでクラスが見つからなかった
			CommonLog.error(request_type, CommonMsg.REQ_CONTENT_ERROR_CLASS_NOTFOUND, e);
			return null;
		} catch (InstantiationException e) { // リフレクション失敗
			CommonLog.error(request_type, CommonMsg.REQ_CONTENT_ERROR_REFLECTION, e);
			return null;
		} catch (IllegalAccessException e) { // リフレクション失敗
			CommonLog.error(request_type, CommonMsg.REQ_CONTENT_ERROR_REFLECTION, e);
			return null;
		} catch (Exception e) { // その他ー
			CommonLog.error(request_type, CommonMsg.REQ_CONTENT_ERROR_OTHER, e);
		}
		return result;
	}

	/**
	 * 該当のコンテンツクラスを返す。(JSP)
	 * @param request_type リクエストタイプ。CommonPara参照。
	 * @param content_name REST APIのパス名
	 * @param method リクエストメソッド。GET/POST/PUT/DELETE
	 * @return　コンテンツクラス
	 */
	public static BlockJspAbstruct searchJspClass(int request_type, String content_name,String method) {
		BlockJspAbstruct result = null;
		
		try {
			//=======================
			//　コンテンツクラス取得
			//=======================
			Map<?,?> resMap = getMappingMap(request_type, content_name, method);
			if( resMap == null ){// 該当クラスが登録されてない。
				return null;
			}
			// クラス・テンプレート名取得
			String ContentClassName = resMap.get("CLASS_NAME").toString();
			String contentTemplateName = resMap.get("TEMPLATE_NAME").toString();
			
			// リフレクションでクラスを読み込む
			Class<?> class1 = Class.forName(ContentClassName);
			result = (BlockJspAbstruct) class1.newInstance();
						
			// クラスオブジェクトにJSPテンプレート名をセットする
			result.templateJsp = contentTemplateName;
		
		} catch (ClassNotFoundException e) { // リフレクションでクラスが見つからなかった
			CommonLog.error(request_type, CommonMsg.REQ_CONTENT_ERROR_CLASS_NOTFOUND, e);
			return null;
		} catch (InstantiationException e) { // リフレクション失敗
			CommonLog.error(request_type, CommonMsg.REQ_CONTENT_ERROR_REFLECTION, e);
			return null;
		} catch (IllegalAccessException e) { // リフレクション失敗
			CommonLog.error(request_type, CommonMsg.REQ_CONTENT_ERROR_REFLECTION, e);
			return null;
		} catch (Exception e) { // その他ー
			CommonLog.error(request_type, CommonMsg.REQ_CONTENT_ERROR_OTHER, e);
		}
		return result;
	}
	
	/**
	 * 内部関数
	 * リクエストのマッピングを行う
	 * @param request_type リクエストタイプ。CommonPara参照。
	 * @param content_name REST APIのパス名
	 * @param method リクエストメソッド。GET/POST/PUT/DELETE
	 * @return　対応クラス名・テンプレートのMap
	 * @throws Exception 処理例外
	 */
	private static Map<?,?> getMappingMap(int request_type, String content_name, String method) throws Exception {
		
		Map<String, String> resMap = new HashMap<String, String>();
		
		//-----------------------------------------
		// 以下は実際は設定ファイルやデータベースでマッピングするほうがよさそう
		//----------------------------------------		
		switch (request_type) {
			case CommonPara.REQUEST_TYPE_RESTAPI:
				if (content_name.equals("sample")) {
					resMap.put("CLASS_NAME", "njc.webservice.arch.ApiSample");
					resMap.put("TEMPLATE_NAME", "");
				}
				break;
			case CommonPara.REQUEST_TYPE_JSP:
				if (content_name.equals("hello")) {
					resMap.put("CLASS_NAME", "njc.webservice.arch.JspHello");
					resMap.put("TEMPLATE_NAME", "hello");
				}
				break;
			default:
				break;
		}
		
		return resMap;
	}
}
