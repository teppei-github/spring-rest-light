package njc.framework.springbase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 共通REST API Interface
 * @author n-koreeda
 * @version 0.1
 */
public interface BlockRestInterface {
	/**
	 * GETメソッド時の処理内容を記述する。
	 * @param request HttpServletRequest
	 * @param input クエリ
	 * @param methodType リクエストメソッド。GET/POSTのみ。
	 * @param responce 共通レスポンスクラス
	 * @return 処理結果。エラー時Null。
	 * @throws Exception エラー
	 */
	Boolean get(
			CommonDBAccesser datasource,
			HttpServletRequest request,
			Map<String, Object> input,
			String methodType,
			BlockRestResponce responce) throws Exception;
	/**
	 * POSTメソッド時の処理内容を記述する。
	 * @param request HttpServletRequest
	 * @param input クエリ
	 * @param methodType リクエストメソッド。GET/POSTのみ。
	 * @param responce 共通レスポンスクラス
	 * @return 処理結果。エラー時Null。
	 * @throws Exception エラー
	 */
	Boolean post(
			CommonDBAccesser datasource,
			HttpServletRequest request,
			Map<String, Object> input,
			String methodType,
			BlockRestResponce responce) throws Exception;
	/**
	 * PUTメソッド時の処理内容を記述する。
	 * @param request HttpServletRequest
	 * @param input クエリ
	 * @param methodType リクエストメソッド。GET/POSTのみ。
	 * @param responce 共通レスポンスクラス
	 * @return 処理結果。エラー時Null。
	 * @throws Exception エラー
	 */
	Boolean put(
			CommonDBAccesser datasource,
			HttpServletRequest request,
			Map<String, Object> input,
			String methodType,
			BlockRestResponce responce) throws Exception;
	/**
	 * DELETEメソッド時の処理内容を記述する。
	 * @param request HttpServletRequest
	 * @param input クエリ
	 * @param methodType リクエストメソッド。GET/POSTのみ。
	 * @param responce 共通レスポンスクラス
	 * @return 処理結果。エラー時Null。
	 * @throws Exception エラー
	 */
	Boolean delete(
			CommonDBAccesser datasource,
			HttpServletRequest request,
			Map<String, Object> input,
			String methodType,
			BlockRestResponce responce) throws Exception;
}
