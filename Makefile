TAG = $(shell git log -1 --pretty=%h)
export IMAGE_TAG=$(TAG)


build-image:
	docker build -f Dockerfile -t web:$(TAG) .

run-app:
	docker-compose up -d

run-maven:
	mvn spring-boot:run
