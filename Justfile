help:
    just --list

clean:
    rm -rf build

compile: clean
    javac \
      --class-path "libs/*" \
      -g \
      --release 21 \
      --module-version 2024.08.22 \
      -d build/javac \
      $(find ./src -name "*.java" -type f)

package: compile
    jar --create --file build/jar/tools.jar -C build/javac .

document: clean
    javadoc --class-path "libs/*" -d build/javadoc $(find ./src -name "*.java" -type f)

stage: package document
    jstage --output build/jstage --classifier sources --pom pom.xml --artifact src
    jstage --output build/jstage --classifier javadoc --pom pom.xml --artifact build/javadoc
    jstage --output build/jstage --pom pom.xml --artifact build/jar/tools.jar

deploy: stage
    jreleaser deploy --output-directory build
