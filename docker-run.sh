#!/usr/bin/env bash
#
# Run docker image
#

if [[ -z "$FMI_API_KEY" ]]; then
   echo "FAIL: Environment variable FMI_API_KEY is not set"
   exit 1
fi

docker run -p 8080:8080 -e FMI_API_KEY="$FMI_API_KEY" weather:1.0.0