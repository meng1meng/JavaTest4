docker stop $(docker ps -aq)
docker rm $(docker ps -aq)
docker rmi myjava-01
docker rmi mysql-01

mvn clean package

cp target/test4.jar docker/java/
cd docker/mysql/
sh build.sh
cd ..
cd ..

cd docker/java/
sh build.sh
cd ..
cd ..

docker-compose up -d

sleep 15
