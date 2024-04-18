if ! [ -x "$(command -v mvn)" ]; then
    echo 'Error: mvn missing in PATH. Unable to build application.' >&2
    exit 1
fi

if ! [ -f "$(pwd)/target/increment-game-0.0.1.jar" ]; then
    mvn clean;
    mvn package;
fi

if ! [ -x "$(command -v cool-retro-term)" ]; then
    echo 'Error: cool-retro-term missing in PATH. Falling back to current terminal.' >&2
    java -cp target/increment-game-0.0.1.jar com.wyvrn.increment.Main

else
    cool-retro-term \
        --workdir `pwd` \
        -T "Increment" \
        $1 \
        -e /bin/bash \
        -c 'java -cp target/increment-game-0.0.1.jar com.wyvrn.increment.Main'
fi
