default: compile

clean:
	./gradlew clean

compile:
	./gradlew build -xtest

build: compile

versioncheck:
	./gradlew dependencyUpdates

depends:
	./gradlew dependencies