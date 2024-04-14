if ! [ -x "$(command -v cool-retro-term)" ]; then
    echo 'Error: cool-retro-term missing in PATH. Unable to wrap application.' >&2
    exit 1
fi

if ! [ -x "$(command -v mvn)" ]; then
    echo 'Error: mvn missing in PATH. Unable to build application.' >&2
    exit 1
fi

if ! [ -f "$(pwd)/target/increment-game-0.0.1.jar" ]; then
    mvn clean;
    mvn package;
fi

cool-retro-term \
    --workdir `pwd` \
    -T "Increment" \
    $1 \
    -e /bin/bash \
    -c 'java -cp target/increment-game-0.0.1.jar com.wyvrn.increment.App'
