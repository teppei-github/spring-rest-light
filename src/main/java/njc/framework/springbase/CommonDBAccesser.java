package njc.framework.springbase;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 共通DBアクセスクラス。
 * @author n-koreeda
 * @version 0.1
 */
public class CommonDBAccesser {
	private PlatformTransactionManager txManager;
	private TransactionStatus ts;
	
	private BasicDataSource datasource = null; // DataSource
	private boolean isOpenActioned = false; // 要求フラグ
	private boolean isConnected = false; // 接続フラグ
	private JdbcTemplate template; // JDBCテンプレート

	/**
	 * Database 接続要求
	 * @throws Exception
	 * @throws SQLException
	 */
	public void openDatabaseSource() throws Exception, SQLException {
		if( this.isOpenActioned ){
			return;
		}
		// =========================
		// Bean設定からDB接続
		// =========================
		this.isOpenActioned = true;
		
		// データソース
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring/application-config.xml");
		
		this.datasource = (BasicDataSource) context.getBean("dataSource");
		this.txManager = (PlatformTransactionManager) context.getBean("transactionManager");
		
		// JdbcTemplate作成
		this.template = new JdbcTemplate(this.datasource);
		// 戻り値をcaseInSensitiveで扱う
		this.template.setResultsMapCaseInsensitive(true);

		this.isConnected = true;
	}
	
	/**
	 * SELECT文を発行し、1レコード取得する。
	 * @param sql 実行文
	 * @return 結果セット。エラー時NULL。
	 * @throws Exception 処理例外
	 * @throws SQLException SQL実行例外
	 */
	public Map<String, Object>  callSQLQuerySingle(String sql,Object[] in ) throws Exception,SQLException  {
		List<Map<String, Object>>  list1 = this.callSQLQuery(sql, in);
		Map<String, Object> res = null;
		for (Map<String, Object> map : list1) {
			res = map;
		}
		return res;
	}
	/**
	 * SELECT文を発行する。
	 * @param sql 実行文
	 * @param in 代入引数
	 * @return 結果セット。エラー時NULL。
	 * @throws Exception 処理例外
	 * @throws SQLException SQL実行例外
	 */
	public List<Map<String, Object>>  callSQLQuery(String sql,Object[] in ) throws Exception,SQLException  {
		if( this.isOpenActioned == false ){// Database接続要求
			this.openDatabaseSource();
		}
		if( this.datasource == null 
				|| this.datasource.isClosed() 
				|| this.isConnected == false ){// Database接続失敗
			throw new Exception(CommonMsg.DATABASE_ERROR_CONNECT);
		}

		List<Map<String, Object>>  list1 = null;
		if( in != null){
			list1 = this.template.queryForList(sql,in); 
		}else{
			list1 = this.template.queryForList(sql); 
		}
		return list1;
	}
	public List<Map<String, Object>>  callSQLQuery(String sql) throws Exception,SQLException  {
		return this.callSQLQuery(sql, null);
	}
	
	
	/**
	 * INSERT分やUPDATE文を発行する。
	 * @param sql 実行文
	 * @param in 代入引数
	 * @return 結果セット。エラー時NULL。
	 * @throws Exception 処理例外
	 * @throws SQLException SQL実行例外
	 */
	public int  callSQLExcec(String sql,Object[] in ) throws Exception,SQLException  {
		if( this.isOpenActioned == false ){// Database接続要求
			this.openDatabaseSource();
		}
		if( this.datasource == null 
				|| this.datasource.isClosed() 
				|| this.isConnected == false ){// Database接続失敗
			throw new Exception(CommonMsg.DATABASE_ERROR_CONNECT);
		}

		int result = -1;
		
		if( in != null){
			result = this.template.update(sql, in); 
		}else{
			result = this.template.update(sql); 
		}
		
		return result;
	}
	
	/**
	 * 
	 * @throws Exception エラー
	 */
	public void beginTransaction() throws Exception{
		this.beginTransaction(false);
	}
	
	/**
	 * トランザクションを開始する。
	 * @param read_only Readonlyフラグ
	 * @throws Exception エラー
	 */
	public void beginTransaction( boolean read_only ) throws Exception{
	  DefaultTransactionDefinition dtd = new DefaultTransactionDefinition();
	  dtd.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	  dtd.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
	  dtd.setTimeout(30);
	  dtd.setReadOnly(read_only);
	  ts = txManager.getTransaction(dtd);
	}
	
	/**
	 * コミットする
	 * @throws Exception エラー
	 */
	public void commit() throws Exception{
		  txManager.commit(ts);
		  ts = null;
	}

	/**
	 * ロールバックする
	 * @throws Exception エラー
	 */
	public void rollback() throws Exception{
	    txManager.rollback(ts);
		  ts = null;
	}
	
	/**
	 * Database 切断
	 * @throws SQLException SQL実行例外
	 */
	public void closeDatabaseSource() throws SQLException{
		this.isOpenActioned = false;
		if( this.datasource != null && !this.datasource.isClosed() ){
			this.datasource.close();
		}
		this.isConnected = false;
	}
}
