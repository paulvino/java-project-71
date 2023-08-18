.DEFAULT_GOAL := build-run

clean:
	@./gradlew clean

build:
	@./gradlew clean build

install:
	@./gradlew clean install

run-dist:
	@./build/install/app/bin/app

help:
	@./build/install/app/bin/app -h

version:
	@./build/install/app/bin/app -V

compare-json:
	@./build/install/app/bin/app src/test/resources/file1.json src/test/resources/file2.json

compare-yaml:
	@./build/install/app/bin/app src/test/resources/file1.yml src/test/resources/file2.yml

compare-f-stylish:
	@./build/install/app/bin/app -f stylish src/test/resources/file1.json src/test/resources/file2.json

compare-f-plain:
	@./build/install/app/bin/app -f plain src/test/resources/file1.json src/test/resources/file2.json

compare-f-json:
	@./build/install/app/bin/app -f json src/test/resources/file1.yml src/test/resources/file2.yml

run:
	@./gradlew run

test:
	@./gradlew test

report:
	@./gradlew jacocoTestReport

lint:
	@./gradlew checkstyleMain checkstyleTest

update-deps:
	@./gradlew useLatestVersions


build-run: build run

.PHONY: build