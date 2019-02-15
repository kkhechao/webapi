FROM registry.cn-hangzhou.aliyuncs.com/prod-env/jdk1.8:v1
MAINTAINER ZQKHYL
COPY healthbutler-webapi.jar /opt/apps/app.jar
COPY start.sh /opt/script/start.sh
RUN chmod +x /opt/script/start.sh
ENV minx 256m
ENV max 1024m
ENV envs prod
ENV port 7777
EXPOSE 7777
CMD ["/opt/script/start.sh"]
