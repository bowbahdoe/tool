help:
    just --list

clean:
    rm -rf build

install:
    jresolve \
      --purge-output-directory \
      --use-module-names \
      --output-directory modules/dev.mccue.tools.jresolve/libs \
      @modules/dev.mccue.tools.jresolve/libs.txt

    jresolve \
      --purge-output-directory \
      --use-module-names \
      --output-directory modules/dev.mccue.tools.jstage/libs \
      @modules/dev.mccue.tools.jstage/libs.txt

    jresolve \
      --purge-output-directory \
      --use-module-names \
      --output-directory modules/dev.mccue.tools.junit/libs \
      @modules/dev.mccue.tools.junit/libs.txt

compile: clean
    javac \
      --module-source-path "./modules/*/src" \
      -g \
      --release 21 \
      --module-version 2024.09.03.1 \
      -d build/javac \
      --module dev.mccue.tools,dev.mccue.tools.googlejavaformat,dev.mccue.tools.jar,dev.mccue.tools.java,dev.mccue.tools.javac,dev.mccue.tools.javadoc,dev.mccue.tools.javap,dev.mccue.tools.jdk,dev.mccue.tools.jlink,dev.mccue.tools.jmod,dev.mccue.tools.jpackage,dev.mccue.tools.pmd

    javac \
      --module-source-path "./modules/*/src" \
      -g \
      --release 21 \
      --module-version 2024.09.03.1 \
      --module-path modules/dev.mccue.tools.jresolve/libs \
      -d build/javac \
      --module dev.mccue.tools.jresolve

    javac \
      --module-source-path "./modules/*/src" \
      -g \
      --release 21 \
      --module-version 2024.09.03.1 \
      --module-path modules/dev.mccue.tools.jstage/libs \
      -d build/javac \
      --module dev.mccue.tools.jstage

    javac \
      --module-source-path "./modules/*/src" \
      -g \
      --release 21 \
      --module-version 2024.09.03.1 \
      --module-path modules/dev.mccue.tools.junit/libs \
      -d build/javac \
      --module dev.mccue.tools.junit

package: compile
    jar --create --file build/jar/dev.mccue.tools.jar -C build/javac/dev.mccue.tools .
    jar --create --file build/jar/dev.mccue.tools.googlejavaformat.jar -C build/javac/dev.mccue.tools.googlejavaformat .
    jar --create --file build/jar/dev.mccue.tools.jar.jar -C build/javac/dev.mccue.tools.jar .
    jar --create --file build/jar/dev.mccue.tools.java.jar -C build/javac/dev.mccue.tools.java .
    jar --create --file build/jar/dev.mccue.tools.javac.jar -C build/javac/dev.mccue.tools.javac .
    jar --create --file build/jar/dev.mccue.tools.javadoc.jar -C build/javac/dev.mccue.tools.javadoc .
    jar --create --file build/jar/dev.mccue.tools.javap.jar -C build/javac/dev.mccue.tools.javap .
    jar --create --file build/jar/dev.mccue.tools.jdk.jar -C build/javac/dev.mccue.tools.jdk .
    jar --create --file build/jar/dev.mccue.tools.jlink.jar -C build/javac/dev.mccue.tools.jlink .
    jar --create --file build/jar/dev.mccue.tools.jmod.jar -C build/javac/dev.mccue.tools.jmod .
    jar --create --file build/jar/dev.mccue.tools.jpackage.jar -C build/javac/dev.mccue.tools.jpackage .
    jar --create --file build/jar/dev.mccue.tools.jresolve.jar -C build/javac/dev.mccue.tools.jresolve .
    jar --create --file build/jar/dev.mccue.tools.jstage.jar -C build/javac/dev.mccue.tools.jstage .
    jar --create --file build/jar/dev.mccue.tools.junit.jar -C build/javac/dev.mccue.tools.junit .
    jar --create --file build/jar/dev.mccue.tools.pmd.jar -C build/javac/dev.mccue.tools.pmd .

document: clean
    javadoc -quiet --module-source-path "./modules/*/src" -d build/javadoc/dev.mccue.tools --module dev.mccue.tools
    javadoc -quiet --module-source-path "./modules/*/src" -d build/javadoc/dev.mccue.tools.googlejavaformat --module dev.mccue.tools.googlejavaformat
    javadoc -quiet --module-source-path "./modules/*/src" -d build/javadoc/dev.mccue.tools.jar --module dev.mccue.tools.jar
    javadoc -quiet --module-source-path "./modules/*/src" -d build/javadoc/dev.mccue.tools.java --module dev.mccue.tools.java
    javadoc -quiet --module-source-path "./modules/*/src" -d build/javadoc/dev.mccue.tools.javac --module dev.mccue.tools.javac
    javadoc -quiet --module-source-path "./modules/*/src" -d build/javadoc/dev.mccue.tools.javadoc --module dev.mccue.tools.javadoc
    javadoc -quiet --module-source-path "./modules/*/src" -d build/javadoc/dev.mccue.tools.javap --module dev.mccue.tools.javap
    javadoc -quiet --module-source-path "./modules/*/src" -d build/javadoc/dev.mccue.tools.jdk --module dev.mccue.tools.jdk
    javadoc -quiet --module-source-path "./modules/*/src" -d build/javadoc/dev.mccue.tools.jlink --module dev.mccue.tools.jlink
    javadoc -quiet --module-source-path "./modules/*/src" -d build/javadoc/dev.mccue.tools.jmod --module dev.mccue.tools.jmod
    javadoc -quiet --module-source-path "./modules/*/src" -d build/javadoc/dev.mccue.tools.jpackage --module dev.mccue.tools.jpackage
    javadoc -quiet --module-path modules/dev.mccue.tools.jresolve/libs --module-source-path "./modules/*/src" -d build/javadoc/dev.mccue.tools.jresolve --module dev.mccue.tools.jresolve
    javadoc -quiet --module-path modules/dev.mccue.tools.jstage/libs --module-source-path "./modules/*/src" -d build/javadoc/dev.mccue.tools.jstage --module dev.mccue.tools.jstage
    javadoc -quiet --module-path modules/dev.mccue.tools.junit/libs --module-source-path "./modules/*/src" -d build/javadoc/dev.mccue.tools.junit --module dev.mccue.tools.junit
    javadoc -quiet --module-source-path "./modules/*/src" -d build/javadoc/dev.mccue.tools.pmd --module dev.mccue.tools.pmd

stage: package document
    jstage --output build/jstage --pom modules/dev.mccue.tools/pom.xml --artifact modules/dev.mccue.tools/src   --classifier sources
    jstage --output build/jstage --pom modules/dev.mccue.tools/pom.xml --artifact build/javadoc/dev.mccue.tools --classifier javadoc
    jstage --output build/jstage --pom modules/dev.mccue.tools/pom.xml --artifact build/jar/dev.mccue.tools.jar

    jstage --output build/jstage --pom modules/dev.mccue.tools.googlejavaformat/pom.xml --artifact modules/dev.mccue.tools.googlejavaformat/src   --classifier sources
    jstage --output build/jstage --pom modules/dev.mccue.tools.googlejavaformat/pom.xml --artifact build/javadoc/dev.mccue.tools.googlejavaformat --classifier javadoc
    jstage --output build/jstage --pom modules/dev.mccue.tools.googlejavaformat/pom.xml --artifact build/jar/dev.mccue.tools.googlejavaformat.jar

    jstage --output build/jstage --pom modules/dev.mccue.tools.jar/pom.xml --artifact modules/dev.mccue.tools.jar/src   --classifier sources
    jstage --output build/jstage --pom modules/dev.mccue.tools.jar/pom.xml --artifact build/javadoc/dev.mccue.tools.jar --classifier javadoc
    jstage --output build/jstage --pom modules/dev.mccue.tools.jar/pom.xml --artifact build/jar/dev.mccue.tools.jar.jar

    jstage --output build/jstage --pom modules/dev.mccue.tools.java/pom.xml --artifact modules/dev.mccue.tools.java/src   --classifier sources
    jstage --output build/jstage --pom modules/dev.mccue.tools.java/pom.xml --artifact build/javadoc/dev.mccue.tools.java --classifier javadoc
    jstage --output build/jstage --pom modules/dev.mccue.tools.java/pom.xml --artifact build/jar/dev.mccue.tools.java.jar

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

    jstage --output build/jstage --pom modules/dev.mccue.tools.jresolve/pom.xml --artifact modules/dev.mccue.tools.jresolve/src   --classifier sources
    jstage --output build/jstage --pom modules/dev.mccue.tools.jresolve/pom.xml --artifact build/javadoc/dev.mccue.tools.jresolve --classifier javadoc
    jstage --output build/jstage --pom modules/dev.mccue.tools.jresolve/pom.xml --artifact build/jar/dev.mccue.tools.jresolve.jar

    jstage --output build/jstage --pom modules/dev.mccue.tools.jstage/pom.xml --artifact modules/dev.mccue.tools.jstage/src   --classifier sources
    jstage --output build/jstage --pom modules/dev.mccue.tools.jstage/pom.xml --artifact build/javadoc/dev.mccue.tools.jstage --classifier javadoc
    jstage --output build/jstage --pom modules/dev.mccue.tools.jstage/pom.xml --artifact build/jar/dev.mccue.tools.jstage.jar

    jstage --output build/jstage --pom modules/dev.mccue.tools.junit/pom.xml --artifact modules/dev.mccue.tools.junit/src   --classifier sources
    jstage --output build/jstage --pom modules/dev.mccue.tools.junit/pom.xml --artifact build/javadoc/dev.mccue.tools.junit --classifier javadoc
    jstage --output build/jstage --pom modules/dev.mccue.tools.junit/pom.xml --artifact build/jar/dev.mccue.tools.junit.jar

    jstage --output build/jstage --pom modules/dev.mccue.tools.pmd/pom.xml --artifact modules/dev.mccue.tools.pmd/src   --classifier sources
    jstage --output build/jstage --pom modules/dev.mccue.tools.pmd/pom.xml --artifact build/javadoc/dev.mccue.tools.pmd --classifier javadoc
    jstage --output build/jstage --pom modules/dev.mccue.tools.pmd/pom.xml --artifact build/jar/dev.mccue.tools.pmd.jar

deploy: stage
    jreleaser deploy --output-directory build
