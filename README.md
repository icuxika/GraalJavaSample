# GraalVM Java 示例

## 一般编译执行

```shell
mvn compile
mvn exec:java@java
```

## GraalVM 构建执行

```shell
mvn -Pnative -DskipTests package
mvn exec:exec@native
```

```shell
mvn -Pshaded -DskipTests package
mvn exec:exec@native
```

## jpackage 构建

```shell
mvn package
mvn exec:exec@image
.\target\buildImage\GraalJavaSample\GraalJavaSample.exe
```
