package njc.webservice.arch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import njc.framework.springbase.BlockRestInterface;
import njc.framework.springbase.BlockRestResponce;
import njc.framework.springbase.CommonDBAccesser;
import njc.framework.springbase.CommonLog;
import njc.framework.springbase.CommonPara;

public class ApiSample implements BlockRestInterface {

	@Override
	public Boolean get(CommonDBAccesser datasource, HttpServletRequest request,
			Map<String, Object> input, String methodType,
			BlockRestResponce responce) throws Exception {
		
		//----------------------
		// SQL実行
		//----------------------	
		List<Map<String, Object>> resultList;

		StringBuilder sqlBuilder =  new StringBuilder();
		// iOSのJsonパーサの制約："_"禁止。SpringMVC（Jackson）の制約：キー名大文字に変換。Oracleの制約："-"禁止
		
		// クエリ
		sqlBuilder.append("SELECT * FROM TABLE");
		
		//resultList = datasource.callSQLQuery(new String( sqlBuilder ),null);
		
		// Sample
		String strKey = "samplekey";
		String strValue = "samplevalue";
		Map<String, Object> sampleMap = new HashMap<String, Object>();
		sampleMap.put(strKey, strValue);
		resultList = new ArrayList<Map<String, Object>>();
		resultList.add(sampleMap);
		
		// テンプレートに変数を渡す
		responce.setResult(resultList);

		//----------------------
		// 処理結果の可否設定
		//----------------------
		//　結果
		responce.setStatusSuccess();

		return true;
	}
	
	@Override
	public Boolean post(CommonDBAccesser datasource,
			HttpServletRequest request, Map<String, Object> input,
			String methodType, BlockRestResponce responce) throws Exception {
	
		StringBuilder sqlBuilder;
		
		//--------------------------------------
		// トランザクション開始
		//--------------------------------------
//		datasource.beginTransaction();
//
		try {
//			//--------------------------------------
//			// リクエスト変数取得
//			//--------------------------------------
//			@SuppressWarnings("unchecked")
//			Map<String, Object> data  =(Map<String, Object>) input.get("data");
//			@SuppressWarnings("unchecked")
//			Map<String, String> head = (Map<String, String>) data.get("head");
//			@SuppressWarnings("unchecked")
//			List<Map<String, String>> datail_list = (List<Map<String, String>>)data.get("detail");
//			
//			//--------------------------------------
//			// DB保存
//			//--------------------------------------
//			sqlBuilder =  new StringBuilder();
//			sqlBuilder.append("INSERT INTO TABLE (");
//			sqlBuilder.append("       CD, NAME");
//			sqlBuilder.append("  ) VALUES ( ");
//			sqlBuilder.append("       ?, ? ");
//			sqlBuilder.append("  )");
//			
//			Object[] inObj = {
//					"CD01",
//					"NAME01",				
//			};
//			int result = datasource.callSQLExcec( new String(sqlBuilder) , inObj);
//			if( result <= 0 ){// DBエラー
//				return false;
//			}
			
//			//　コミット
//			datasource.commit();
			
			//　結果設定
			responce.setStatusSuccess();
			return true;
			
		} catch( Exception e ) {
			CommonLog.error(CommonPara.REQUEST_TYPE_RESTAPI, e.getMessage(),e);	
			try {
				datasource.rollback();
			} catch (Exception e1) {
				CommonLog.error(CommonPara.REQUEST_TYPE_RESTAPI, "Failed save log for database. Rollback Error", e1);
			}
			responce.setStatusError(CommonPara.STATUS_RESPONSE_ERROR, e.getMessage());
			return false;
		}
	}
	
	@Override
	public Boolean put(CommonDBAccesser datasource, HttpServletRequest request,
			Map<String, Object> input, String methodType,
			BlockRestResponce responce) throws Exception {
		return null;
	}

	@Override
	public Boolean delete(CommonDBAccesser datasource,
			HttpServletRequest request, Map<String, Object> input,
			String methodType, BlockRestResponce responce) throws Exception {
		return null;
	}
}
