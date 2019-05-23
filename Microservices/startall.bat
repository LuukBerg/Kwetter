cd accountdoa

cmd.exe /c start mvn package && java -jar target/accountdoa-0.0.1-SNAPSHOT.jar

cd .. 
cd accountservice
cmd.exe /c start mvn package && java -jar target/accountservice-0.0.1-SNAPSHOT.jar

cd .. 
cd apiservice
cmd.exe /c start mvn package && java -jar target/apiservice-0.0.1-SNAPSHOT.jar

cd .. 
cd kweetservice
cmd.exe /c start mvn package && java -jar target/kweetservice-0.0.1-SNAPSHOT.jar

cd .. 
cd kwetter-doa
cmd.exe /c start mvn package && java -jar target/kwetter-doa-0.0.1-SNAPSHOT.jar

cd .. 
cd profile-doa
cmd.exe /c start mvn package && java -jar target/profile-doa-0.0.1-SNAPSHOT.jar
cd .. 
cd profileservice
cmd.exe /c start mvn package && java -jar target/profileservice-0.0.1-SNAPSHOT.jar