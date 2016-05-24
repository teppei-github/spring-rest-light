package njc.webservice.arch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import njc.framework.springbase.BlockJspAbstruct;
import njc.framework.springbase.BlockJspResponce;
import njc.framework.springbase.CommonDBAccesser;

public class JspHello extends BlockJspAbstruct {

	@Override
	public Boolean run(CommonDBAccesser datasource, String methodType, HttpServletRequest request,
			HttpServletResponse response, Model model, BlockJspResponce responce) throws Exception {
		
		//======================
		//　処理内容
		//======================
		//----------------------
		// 処理結果の可否設定
		//----------------------
		//　結果
		responce.setStatusSuccess();
		
		// コンテキストパスをセット
		String strContextPath = request.getContextPath();
		model.addAttribute("url_context", strContextPath);
				
		return true;
	}
}
