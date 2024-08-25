help:
    just --list

clean:
    rm -rf build

compile: clean
    javac \
      --module-source-path "./modules/*/src" \
      -g \
      --release 21 \
      --module-version 2024.08.25.1 \
      -d build/javac \
      --module dev.mccue.tools,dev.mccue.tools.jar,dev.mccue.tools.java,dev.mccue.tools.javac,dev.mccue.tools.javadoc,dev.mccue.tools.javap,dev.mccue.tools.jdk,dev.mccue.tools.jlink,dev.mccue.tools.jmod,dev.mccue.tools.jpackage

package: compile
    jar --create --file build/jar/dev.mccue.tools.jar -C build/javac/dev.mccue.tools .
    jar --create --file build/jar/dev.mccue.tools.jar.jar -C build/javac/dev.mccue.tools.jar .
    jar --create --file build/jar/dev.mccue.tools.java.jar -C build/javac/dev.mccue.tools.java .
    jar --create --file build/jar/dev.mccue.tools.javac.jar -C build/javac/dev.mccue.tools.javac .
    jar --create --file build/jar/dev.mccue.tools.javadoc.jar -C build/javac/dev.mccue.tools.javadoc .
    jar --create --file build/jar/dev.mccue.tools.javap.jar -C build/javac/dev.mccue.tools.javap .
    jar --create --file build/jar/dev.mccue.tools.jdk.jar -C build/javac/dev.mccue.tools.jdk .
    jar --create --file build/jar/dev.mccue.tools.jlink.jar -C build/javac/dev.mccue.tools.jlink .
    jar --create --file build/jar/dev.mccue.tools.jmod.jar -C build/javac/dev.mccue.tools.jmod .
    jar --create --file build/jar/dev.mccue.tools.jpackage.jar -C build/javac/dev.mccue.tools.jpackage .

document: clean
    javadoc --module-source-path "./modules/*/src" -d build/javadoc/dev.mccue.tools --module dev.mccue.tools
    javadoc --module-source-path "./modules/*/src" -d build/javadoc/dev.mccue.tools.jar --module dev.mccue.tools.jar
    javadoc --module-source-path "./modules/*/src" -d build/javadoc/dev.mccue.tools.java --module dev.mccue.tools.java
    javadoc --module-source-path "./modules/*/src" -d build/javadoc/dev.mccue.tools.javac --module dev.mccue.tools.javac
    javadoc --module-source-path "./modules/*/src" -d build/javadoc/dev.mccue.tools.javadoc --module dev.mccue.tools.javadoc
    javadoc --module-source-path "./modules/*/src" -d build/javadoc/dev.mccue.tools.javap --module dev.mccue.tools.javap
    javadoc --module-source-path "./modules/*/src" -d build/javadoc/dev.mccue.tools.jdk --module dev.mccue.tools.jdk
    javadoc --module-source-path "./modules/*/src" -d build/javadoc/dev.mccue.tools.jlink --module dev.mccue.tools.jlink
    javadoc --module-source-path "./modules/*/src" -d build/javadoc/dev.mccue.tools.jmod --module dev.mccue.tools.jmod
    javadoc --module-source-path "./modules/*/src" -d build/javadoc/dev.mccue.tools.jpackage --module dev.mccue.tools.jpackage

stage: package document
    jstage --output build/jstage --pom modules/dev.mccue.tools/pom.xml --artifact modules/dev.mccue.tools/src   --classifier sources
    jstage --output build/jstage --pom modules/dev.mccue.tools/pom.xml --artifact build/javadoc/dev.mccue.tools --classifier javadoc
    jstage --output build/jstage --pom modules/dev.mccue.tools/pom.xml --artifact build/jar/dev.mccue.tools.jar

    jstage --output build/jstage --pom modules/dev.mccue.tools.jar/pom.xml --artifact modules/dev.mccue.tools.jar/src   --classifier sources
    jstage --output build/jstage --pom modules/dev.mccue.tools.jar/pom.xml --artifact build/javadoc/dev.mccue.tools.jar --classifier javadoc
    jstage --output build/jstage --pom modules/dev.mccue.tools.jar/pom.xml --artifact build/jar/dev.mccue.tools.jar.jar

    jstage --output build/jstage --pom modules/dev.mccue.tools.java/pom.xml --artifact modules/dev.mccue.tools.java/src   --classifier sources
    jstage --output build/jstage --pom modules/dev.mccue.tools.java/pom.xml --artifact build/javadoc/dev.mccue.tools.java --classifier javadoc
    jstage --output build/jstage --pom modules/dev.mccue.tools.java/pom.xml --artifact build/jar/dev.mccue.tools.jar

    jstage --output build/jstage --pom modules/dev.mccue.tools.javac/pom.xml --artifact modules/dev.mccue.tools.javac/src   --classifier sources
    jstage --output build/jstage --pom modules/dev.mccue.tools.javac/pom.xml --artifact build/javadoc/dev.mccue.tools.javac --classifier javadoc
    jstage --output build/jstage --pom modules/dev.mccue.tools.javac/pom.xml --artifact build/jar/dev.mccue.tools.javac.jar

    jstage --output build/jstage --pom modules/dev.mccue.tools.javadoc/pom.xml --artifact modules/dev.mccue.tools.javadoc/src   --classifier sources
    jstage --output build/jstage --pom modules/dev.mccue.tools.javadoc/pom.xml --artifact build/javadoc/dev.mccue.tools.javadoc --classifier javadoc
    jstage --output build/jstage --pom modules/dev.mccue.tools.javadoc/pom.xml --artifact build/jar/dev.mccue.tools.javadoc.jar

    jstage --output build/jstage --pom modules/dev.mccue.tools.javap/pom.xml --artifact modules/dev.mccue.tools.javap/src   --classifier sources
    jstage --output build/jstage --pom modules/dev.mccue.tools.javap/pom.xml --artifact build/javadoc/dev.mccue.tools.javap --classifier javadoc
    jstage --output build/jstage --pom modules/dev.mccue.tools.javap/pom.xml --artifact build/jar/dev.mccue.tools.javap.jar

    jstage --output build/jstage --pom modules/dev.mccue.tools.jdk/pom.xml --artifact modules/dev.mccue.tools.jdk/src   --classifier sources
    jstage --output build/jstage --pom modules/dev.mccue.tools.jdk/pom.xml --artifact build/javadoc/dev.mccue.tools.jdk --classifier javadoc
    jstage --output build/jstage --pom modules/dev.mccue.tools.jdk/pom.xml --artifact build/jar/dev.mccue.tools.jdk.jar

    jstage --output build/jstage --pom modules/dev.mccue.tools.jlink/pom.xml --artifact modules/dev.mccue.tools.jlink/src   --classifier sources
    jstage --output build/jstage --pom modules/dev.mccue.tools.jlink/pom.xml --artifact build/javadoc/dev.mccue.tools.jlink --classifier javadoc
    jstage --output build/jstage --pom modules/dev.mccue.tools.jlink/pom.xml --artifact build/jar/dev.mccue.tools.jlink.jar

    jstage --output build/jstage --pom modules/dev.mccue.tools.jmod/pom.xml --artifact modules/dev.mccue.tools.jmod/src   --classifier sources
    jstage --output build/jstage --pom modules/dev.mccue.tools.jmod/pom.xml --artifact build/javadoc/dev.mccue.tools.jmod --classifier javadoc
    jstage --output build/jstage --pom modules/dev.mccue.tools.jmod/pom.xml --artifact build/jar/dev.mccue.tools.jmod.jar

    jstage --output build/jstage --pom modules/dev.mccue.tools.jpackage/pom.xml --artifact modules/dev.mccue.tools.jpackage/src   --classifier sources
    jstage --output build/jstage --pom modules/dev.mccue.tools.jpackage/pom.xml --artifact build/javadoc/dev.mccue.tools.jpackage --classifier javadoc
    jstage --output build/jstage --pom modules/dev.mccue.tools.jpackage/pom.xml --artifact build/jar/dev.mccue.tools.jpackage.jar



deploy: stage
    jreleaser deploy --output-directory build
