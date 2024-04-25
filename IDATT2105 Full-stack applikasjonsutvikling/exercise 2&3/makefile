fresh:
	gradlew clean build
	docker-compose up --build

auto:
	./gradlew -t installDist

start:
	./gradlew build
	docker-compose up
	docker-compose build
s:
	make start

test:
	./gradlew test