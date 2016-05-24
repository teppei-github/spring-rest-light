package njc.framework.springbase;

/**
 * 共通システムメッセージ
 * @author n-koreeda
 * @version 0.1
 */
public class CommonMsg {
	// Http Request
	public static final String REQUEST_ERROR_FAILED_REQUEST = "failed request";
	public static final String REQUEST_ERROR_TIMEOUT = "timeout";
	
	// Request content
	public static final String REQ_CONTENT_ERROR_NOTFOUND = "request content not found";
	public static final String REQ_CONTENT_ERROR_OTHER = "request content_error";
	public static final String REQ_CONTENT_ERROR_CLASS_NOTFOUND = "request content class not found";
	public static final String REQ_CONTENT_ERROR_REFLECTION  = "request content can not class instance";
	public static final String REQ_CONTENT_ERROR_JSP_NOTFOUND = "request content jsp template not found";
	
	// Database
	public static final String DATABASE_ERROR_CONNECT = "failed database connect";
	public static final String DATABASE_ERROR_PLSQL = "failed pl/sql error";
	public static final String DATABASE_ERROR_DISCONNECT = "failed database disconnect";

	// Auth
	public static final String AUTH_ERROR_FAILED_REQUEST = "failed request";
	public static final String AUTH_ERROR_INVALID = "auth error";
	
	// Other
	public static final String OTHER_ERROR_UNDEFINED = "undefined error";

}
