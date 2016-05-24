package njc.framework.springbase;

/**
 * REST API 共通クラス、共通変数
 * @author n-koreeda
 * @version 0.1
 */
public class CommonPara {
	// Http Status
	public static final int STATUS_RESPONSE_SUCCESS = 0;
	public static final int STATUS_RESPONSE_FAILED_REQUEST = 1;
	public static final int STATUS_RESPONSE_AUTH_ERR = 2;
	public static final int STATUS_RESPONSE_ERROR = 3;
	
	// RequestType
	public static final int REQUEST_TYPE_RESTAPI = 0;
	public static final int REQUEST_TYPE_JSP = 1;
	//public static final int REQUEST_TYPE_WEBSOCKET = 2;
	public static final int REQUEST_TYPE_SYS = 3;
	public static final int REQUEST_TYPE_SCHEDULE = 4;
	
	// AuthType
	public static final int AUTH_TYPE_RESTAPI = 0;
	public static final int AUTH_TYPE_JSP = 1;
	
	// JSP Template
	public static final String TEMPLATE_JSP_ERROR = "error_50x";
}
