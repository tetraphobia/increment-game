if ! [ -x "$(command -v cool-retro-term)" ]; then
    echo 'Error: cool-retro-term is missing. Unable to do cool things.' >&2
    exit 1
fi

cool-retro-term \
    --workdir `pwd` \
    -T "Increment" \
    $1 \
    -e /bin/bash \
    -c 'java -cp target/increment-game-0.0.1.jar com.wyvrn.increment.App'
