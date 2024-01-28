# GraalVM Java 示例

## 构建

```shell
mvn -Pnative -DskipTests package
```

```shell
mvn -Pshaded -DskipTests package
```

## 执行

```shell
mvn exec:exec@native
```

```shell
mvn exec:java@java
```