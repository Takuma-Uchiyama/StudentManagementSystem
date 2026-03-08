# 学生管理システム（OOP応用版）

![Java](https://img.shields.io/badge/Java-17+-blue)
![OOP](https://img.shields.io/badge/OOP-応用-green)
![Week](https://img.shields.io/badge/Week-5完成-brightgreen)
![License](https://img.shields.io/badge/License-MIT-yellow)
![Lines](https://img.shields.io/badge/Lines-600~700-orange)

**Week 5: Java応用・オブジェクト指向応用 完成版**

継承・ポリモーフィズム・抽象化・例外処理・Lombokを統合した
エンタープライズレベル学生管理システム

## 📋 目次
- [概要](#概要)
- [技術スタック](#技術スタック)
- [主要機能](#主要機能)
- [OOP設計](#oop設計)
- [セットアップ](#セットアップ)
- [使用方法](#使用方法)
- [学習成果](#学習成果)
- [今後の拡張予定](#今後の拡張予定)
- [ライセンス](#ライセンス)

## 🎯 概要

Java のオブジェクト指向プログラミング（OOP）の応用技術を活用して開発した、実用的な学生管理システムです。
継承・ポリモーフィズム・抽象化・例外処理・Lombokを統合し、エンタープライズレベルの品質を目指しました。

### ✨ 特徴
- **継承階層**: Person → Student の明確な継承関係
- **ポリモーフィズム**: 統一インターフェースによる異なる学生タイプの処理
- **抽象化**: インターフェース・抽象クラスによる設計品質確保
- **例外処理**: カスタム例外による堅牢なエラーハンドリング
- **Lombok統合**: 86%のコード削減による効率的な開発


## 🛠️ 技術スタック

| 技術 | バージョン | 用途 |
|------|------------|------|
| Java | 17+ | 主要開発言語 |
| Lombok | Latest | コード生成・効率化 |
| Maven | 3.6+ | 依存関係管理 |
| JUnit | 5+ | 単体テスト（拡張予定） |

### 📦 依存関係
```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.28</version>
</dependency>
```

## 🎯 主要機能
### 基本CRUD操作
- 学生登録: 基本情報（ID、名前、年齢、メール）の登録
- 学生一覧表示: 登録済み学生の一覧表示
- 学生検索: ID・名前による検索機能
- 学生削除: 指定学生の削除（安全な確認付き）
### 高度な機能
- 学生タイプ管理: 正規生・交換留学生・聴講生の区分管理
- 成績管理: 科目別成績の登録・管理
- 統計機能: 平均点計算・合格者数集計
- バリデーション: 入力データの妥当性検証

## 🏗️ OOP設計

### クラス設計図
```text
Person (抽象基底クラス)
    ├── Student (具象クラス)
    │   ├── RegularStudent (正規学生)
    │   ├── ExchangeStudent (交換留学生)
    │   └── AuditStudent (聴講生)
    │
    └── interfaces/
        ├── Reportable (レポート生成)
        ├── Validatable (バリデーション)
        └── Comparable<Student> (比較)
```

### 設計パターン
- Template Method: 共通処理の抽象化
- Strategy Pattern: 学生タイプ別処理の切り替え
- Factory Pattern: 学生オブジェクトの生成管理
### 例外階層
```text
StudentManagementException (基底例外)
    ├── StudentNotFoundException (学生未発見)
    ├── DuplicateStudentException (重複登録)
    ├── InvalidStudentDataException (無効データ)
    └── GradeCalculationException (成績計算エラー)
```

## 🚀 セットアップ

### 前提条件
- Java 17以上
- IDE（Eclipse、IntelliJ IDEA等）
- Lombokプラグインのインストール

### インストール手順
1.リポジトリのクローン
```bash
git clone https://github.com/Takuma-Uchiyama/StudentManagementSystem.git
```
2.Lombok設定
3.IDEにLombokプラグインをインストール
4.アノテーション処理を有効化
5.プロジェクト実行

## 📖 使用方法
### 基本操作
1.システム起動
```text
=== 学生管理システム（OOP応用版）===
Week 5: 継承・ポリモーフィズム・抽象化・例外処理・Lombok統合

==================================================
        📚 学生管理システム（OOP応用版）
==================================================
```
2.メニュー選択
```text
1: 新しい学生を登録
2: 全学生一覧を表示
3: 学生を検索
4: 学生を削除
5: 成績を管理
6: 統計情報を表示
0: システム終了
```

### 実行例
// 学生登録例
```java
Student student = new RegularStudent.builder()
    .id("S001")
    .name("田中太郎")
    .age(20)
    .email("tanaka@example.com")
    .department("情報工学科")
    .build();
```

// ポリモーフィズムによる統一処理
```java
students.values().forEach(student -> {
    System.out.println(student.generateReport());
});
```

## 🎓 学習成果

Week 5で習得した技術

**継承技術**
- extends、super、メソッドオーバーライド
- 継承階層の適切な設計
- 親子クラス間の責務分担
- 
**ポリモーフィズム**
- 動的メソッド選択の活用
- 統一インターフェースによる処理
- 実行時型判定の実装
**抽象化技術**
- 抽象クラス（abstract）の設計
- インターフェース（interface）による契約
- 具象化の強制による品質確保
**例外処理**
- カスタム例外クラスの設計
- 例外安全なコード実装
- プロフェッショナルなエラーハンドリング
**Lombok活用**
- @Data、@Builder、@Slf4jの効果的活用
- 86%のコード削減達成
- 現代的Java開発手法の習得
### 💼 実務レベル到達証明
- 総実装行数: 600-700行
- 設計品質: エンタープライズレベル
- 技術統合: 5つの高度OOP概念を完全統合
- 実用性: 実際の企業システムで使用可能な品質

## 🚀 今後の拡張予定
**Phase 1: データベース連携（Week 8-10予定）**
- [ ] MySQL連携による永続化
- [ ] JPA/HibernateによるORM実装
- [ ] Spring Bootによる本格的Web化
**Phase 2: Web UI実装**
- [ ] Thymeleafによる画面作成
- [ ] REST API化
- [ ] レスポンシブデザイン対応
**Phase 3: 高度機能**
- [ ] ファイルアップロード（CSV Import/Export）
- [ ] 成績統計・グラフ表示
- [ ] ユーザー認証・権限管理
**Phase 4: 運用機能**
- [ ] JUnitによる自動テスト
- [ ] ログ管理システム
- [ ] パフォーマンス最適化

## 📊 プロジェクト統計

| 項目 | 値 |
|------|----|
| 総クラス数 | 12クラス |
| 総メソッド数 | 45メソッド |
| 総実装行数 | 600-700行 |
| Lombok削減行数 | 400行以上 |
| テストカバレッジ | 拡張予定 |

## 🤝 Contributing
1. このリポジトリをFork
2. 機能ブランチを作成 (git checkout -b feature/AmazingFeature)
3. 変更をコミット (git commit -m 'Add some AmazingFeature')
4. ブランチにPush (git push origin feature/AmazingFeature)
5. Pull Requestを作成

## 📝 ライセンス
このプロジェクトはMIT Licenseの下で公開されています。 詳細は LICENSE ファイルを参照してください。

## 📧 お問い合わせ
プロジェクトに関するご質問は、以下の方法でお気軽にお問い合わせください：

Issue作成: バグ報告・機能要望は Issues で
メール: takuma.uchiyama@nkaihatsu.com
Twitter: @chinchin_twitter_handle

**最終更新**: GitHub Web編集にて追加
