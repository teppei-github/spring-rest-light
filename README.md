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
* アプリサーバはTomcat
* サンプルAPIを実行する場合→リクエストヘッダに「Content-Type:application/json」をセットして、http://XXX.XX.XX.XX:8080/NsoArchSpringApp/api/sample/
* サンプルJSPを表示する場合→http://XXX.XX.XX.XX:8080/NsoArchSpringApp/content/hello
