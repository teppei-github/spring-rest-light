package njc.framework.springbase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 共通ログ関数
 * @author n-koreeda
 * @version 0.1
 */
public class CommonLog {
	private static final Logger logger = LoggerFactory.getLogger(CommonLog.class);

	private static final int LOGTYPE_WARNING = 0;
	private static final int LOGTYPE_ERROR = 1;
	private static final int LOGTYPE_INFO = 2;
	private static final int LOGTYPE_DEBUG = 3;
	
	public static final String LINE_SEPARATOR = System.lineSeparator(); //環境非依存の改行コード
	
	// ==================
	// 公開関数
	// ==================
	// 通常のログ
	/**
	 * Warning
	 * @param request_type　リクエストタイプ。CommonPara参照。
	 * @param title ログ内容。
	 */
	public static void warning( int request_type, String title ) {
		warning(request_type, title, null);
	}
	/**
	 * Warning
	 * @param request_type　リクエストタイプ。CommonPara参照。
	 * @param title ログ内容。
	 * @param arg1 記録する変数
	 */
	public static void warning( int request_type, String title , Throwable arg1 ) {
		write(LOGTYPE_WARNING, request_type, title, arg1, true);
	}
	
	/**
	 * Error
	 * @param request_type　リクエストタイプ。CommonPara参照。
	 * @param title ログ内容。
	 */
	public static void error( int request_type, String title ) {
		error(request_type, title, null);
	}
	/**
	 * Error
	 * @param request_type　リクエストタイプ。CommonPara参照。
	 * @param title ログ内容。
	 * @param arg1 記録する変数
	 */
	public static void error( int request_type, String title , Throwable arg1 ) {
		write(LOGTYPE_ERROR, request_type, title, arg1, true);
	}

	/**
	 * Info
	 * @param request_type　リクエストタイプ。CommonPara参照。
	 * @param title ログ内容。
	 */
	public static void info( int request_type, String title ) {
		info(request_type, title, null);
	}
	/**
	 * Info
	 * @param request_type　リクエストタイプ。CommonPara参照。
	 * @param title ログ内容。
	 * @param arg1 記録する変数
	 */
	public static void info( int request_type, String title , Throwable arg1 ) {
		write(LOGTYPE_INFO, request_type, title, arg1, true);
	}

	/**
	 * Debug
	 * @param request_type　リクエストタイプ。CommonPara参照。
	 * @param title ログ内容。
	 */
	public static void debug( int request_type, String title ) {
		debug(request_type, title, null);
	}
	/**
	 * Debug
	 * @param request_type　リクエストタイプ。CommonPara参照。
	 * @param title ログ内容。
	 * @param arg1 記録する変数
	 */
	public static void debug( int request_type, String title , Throwable arg1 ) {
		write(LOGTYPE_DEBUG, request_type, title, arg1, true);
	}

	// ==================
	// 内部関数
	// ==================
	/**
	 * ログ保存の内部関数。
	 * @param log_type ログ種別
	 * @param request_type　リクエストタイプ。CommonPara参照。
	 * @param title ログ内容。
	 * @param arg1 保存する変数
	 * @param db_write DBの保存有無
	 */
	private static void write( int log_type, int request_type, String title , Throwable error , Boolean db_write ) {
		String log_typeString = null;
		String request_typeString = null;
		
		// Request type to String
		switch (request_type) {
		case CommonPara.REQUEST_TYPE_RESTAPI:
			request_typeString = "REST_API";
			break;
		case CommonPara.REQUEST_TYPE_JSP:
			request_typeString = "JSP";
			break;
		case CommonPara.REQUEST_TYPE_SYS:
			request_typeString = "SYSTEM";
			break;
		case CommonPara.REQUEST_TYPE_SCHEDULE:
			request_typeString = "SCHEDULE";
			break;
		default:
			break;
		}

		// Logtype to String
		switch (log_type) {
		case LOGTYPE_WARNING:
			log_typeString = "WARNING";
			break;
		case LOGTYPE_ERROR:
			log_typeString = "ERROR";
			break;
		case LOGTYPE_INFO:
			log_typeString = "INFO";
			break;
		case LOGTYPE_DEBUG:
			log_typeString = "DEBUG";
			break;
		default:
			break;
		}
		
		// log4j保存
		write_object(log_type, log_typeString, request_type, request_typeString, title, error );
	}

	/**
	 * 内部関数。log4j保存
	 * @param log_type ログ種別
	 * @param log_typeString ログ種別名
	 * @param request_type　リクエストタイプ。CommonPara参照。
	 * @param request_typeString リクエストタイプ名
	 * @param title ログ内容。
	 * @param backtrace バックトレース・または詳細。
	 * @param arg1 保存する変数
	 */
	private static void write_object( int log_type, String log_typeString, int request_type, String request_typeString, String title , Throwable error ) {

		// Loggerオブジェクト用の文字列生成
		StringBuilder titeBuilder = new StringBuilder();
		String save_title; 
		titeBuilder.append( "[" );
		titeBuilder.append( request_typeString );
		titeBuilder.append( "] " );
		titeBuilder.append( title );

		
		save_title = new String(titeBuilder);
		
		//　Loggerオブジェクト
		switch (log_type) {
		case LOGTYPE_WARNING:
			logger.warn(save_title, error);
			break;
		case LOGTYPE_ERROR:
			logger.error(save_title, error);
			break;
		case LOGTYPE_INFO:
			logger.info(save_title, error);
			break;
		case LOGTYPE_DEBUG:
			logger.debug(save_title, error);
			break;
		default:
			break;
		}
	}
}
