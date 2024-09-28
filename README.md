# 書籍管理システム

## Requirements

- Java 21
- Docker

## ローカル開発環境立ち上げ

```shell
docker compose up -d
./gradlew bootRun
```

## テスト

### Smallテスト

```shell
./gradlew test
```

### Mediumテスト

テスト用のDBコンテナが別途立ち上がってDBを通してテストを行う

```shell
./gradlew testMedium
```
