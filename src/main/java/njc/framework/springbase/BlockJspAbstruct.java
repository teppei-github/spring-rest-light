package njc.framework.springbase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

/**
 * 共通JSPクラス抽象クラス
 * @author n-koreeda
 * @version 0.1
 */
public abstract class BlockJspAbstruct {
	public String templateJsp = null; // JSPファイル名
	
	/**
	 * 処理内容を記述します。
	 * @param datasource Database
	 * @param methodType リクエストメソッド
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param model SpringWebFrameWorkのModelクラス。テンプレート用の変数を代入する。
	 * @param responce 共通レスポンスクラス。
	 * @return 処理結果。
	 * @throws Exception エラー
	 */
	public abstract Boolean run(CommonDBAccesser datasource,
			String methodType,
			HttpServletRequest request,
            HttpServletResponse response,
            Model model,
            BlockJspResponce responce) throws Exception;
}
