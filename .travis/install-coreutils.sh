#!/bin/bash

CORE_UTILS=8.27
export FORCE_UNSAFE_CONFIGURE=1


wget -qO- https://ftp.gnu.org/gnu/coreutils/coreutils-${CORE_UTILS}.tar.xz -O /tmp/coreutils-${CORE_UTILS}.tar.xz
tar -xf /tmp/coreutils-${CORE_UTILS}.tar.xz -C /tmp/
chmod 777 -R /tmp/coreutils-${CORE_UTILS}
/tmp/coreutils-${CORE_UTILS}/configure \
                             --prefix=${HOME}/coreutils \
                             --libexecdir=${HOME}/coreutils/lib \
                             --enable-no-install-program=kill,uptime

make -C /tmp/coreutils-${CORE_UTILS}
make install -C /tmp/coreutils-${CORE_UTILS}

${HOME}/coreutils/bin/base64 --version
