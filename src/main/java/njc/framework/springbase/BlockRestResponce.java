package njc.framework.springbase;

import java.util.Date;
import java.util.List;

/**
 * REST API 共通レスポンスクラス
 * @author n-koreeda
 * @version 0.1
 */
public class BlockRestResponce {
	// ==========================
	// 変数 
	// ==========================
	// ------------------------
	// 共通変数
	// ------------------------
	// アクセストークン
	public String token;
	// レスポンスステータス
	public int status = CommonPara.STATUS_RESPONSE_SUCCESS;
	// 各種日付
	public Date receiveDate = new Date();
	public Date responceDate = new Date();
	// エラーメッセージ
	public String errorMessage = null;
	
	// ------------------------
	// データ内容
	// ------------------------
	// データ本体
	public List<?> result = null;
	// データ本体
	public int length = 0;

	// ==========================
	// セッター 
	// ==========================
	/**
	 * 結果セットを設定します。
	 * @param result 配列
	 */
	public void setResult(List<?> result) {
		this.result = result;
		if( result != null){
			this.length = result.size();
		}
	}
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
		if( this.status == CommonPara.STATUS_RESPONSE_FAILED_REQUEST){
			this.token = null;
		}
	}
	/**
	 * 認証トークンをセットします 
	 * @param token　トークン
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * 出力日時を更新します。
	 */
	public void updateResponceDate(){
		this.responceDate = new Date();
	}
}
