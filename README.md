# spring-rest-light
Java Spring Frameworkで作成したREST APIサーバの軽量版

※社内用で作成したものです。社外からの問合せやプルリクはあまりサポートできないかもしれません。。

## 特徴
* OAuth認証なし
* APIのマッピングはクラス内で実装
* DB接続なし（接続するためのクラスは作ってある）

## 使い方
* Mavenプロジェクトとして、STSでimport
* Javaは8以上
* アプリサーバはTomcat8
* サンプルAPIを実行する場合→リクエストヘッダに「Content-Type:application/json」をセットして、http://XXX.XX.XX.XX:8080/NsoArchSpringApp/api/sample/
* サンプルJSPを表示する場合→http://XXX.XX.XX.XX:8080/NsoArchSpringApp/content/hello

## 環境構築手順
* JavaSE8をインストールしておく
* Tomcat8をインストールしておく
* ソースをGiｔHubからcloneかダウンロードしておく

* STS
　ダウンロードして解凍>起動  
　Package Explorerを右クリック>import  
　Maven>Exist～を選んで、GitHubから落としたフォルダを指定  
　Mavenプロジェクトとして取り込まれる  
　Package Explorerでプロジェクトのルートを右クリック>Maven>Update Project>Force Updateにチェック入れてOK  
　　※一応やっておく  

* 設定
　window>Preferences>Java>Installed JREs  
　AddかSearchでjdk1.8.XXを選択  
　Package Explorerでプロジェクトのルートを右クリック>Properties>Project Facets>Javaを1.5から1.8に変更してApply>OK  
　　※ここは人によっては違う設定方法とってるかも  

* アプリサーバ
　Servers上（Package Explorerの下にあるスペース）で右クリック>New>Server  
　Apache>Tomcat 8.0 Server>Nextクリック  
　Browseクリック>インストールしておいたTomcat8を選択>Nextクリック  
　プロジェクトを選択してAdd>Finishクリック  

* 動作確認* 
　Tomcatサーバを右クリックしてStart  
　ブラウザ起動して、http://localhost:8080/NsoArchSpringApp/content/hello  
　Hello Worldが表示されたらOK  
