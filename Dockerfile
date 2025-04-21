# 使用官方的 OpenJDK 作为基础镜像
FROM openjdk:11-jre-slim

# 设置工作目录
WORKDIR /app

# 将本地的 jar 文件复制到 Docker 镜像中
COPY target/Opera3-0.0.1-SNAPSHOT.jar /app/Opera3.jar

# 暴露端口
EXPOSE 8080

# 运行命令，启动 Spring Boot 应用
ENTRYPOINT ["java", "-jar", "Opera3.jar"]
