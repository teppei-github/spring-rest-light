package njc.framework.springbase;

/**
 * JSP　共通レスポンスクラス
 * @author n-koreeda
 * @version 0.1
 */
public class BlockJspResponce {
	// ==========================
	// 変数 
	// ==========================
	// ------------------------
	// 共通変数
	// ------------------------
	// レスポンスステータス
	public int status = CommonPara.STATUS_RESPONSE_SUCCESS;
	// エラーメッセージ
	public String errorMessage = null;

	// ==========================
	// セッター 
	// ==========================
	/**
	 * 成功ステータスをセットします。
	 */
	public void setStatusSuccess(){
		this.status = CommonPara.STATUS_RESPONSE_SUCCESS;
	}
	/**
	 * エラーステータスをセットします。
	 * @param res_status CommonParaのステータス情報をセットします。
	 * @param messsage エラー内容をセットします。
	 */
	public void setStatusError(int res_status,String messsage){
		this.status = res_status;
		this.errorMessage = messsage;
	}
}
