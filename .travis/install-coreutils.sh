#!/bin/bash

export FORCE_UNSAFE_CONFIGURE=1

CORE_UTILS=8.27

echo "DOWNLOAD coreutils-${CORE_UTILS}!"
wget -qO- https://ftp.gnu.org/gnu/coreutils/coreutils-${CORE_UTILS}.tar.xz -O /tmp/coreutils-${CORE_UTILS}.tar.xz
tar -xf /tmp/coreutils-${CORE_UTILS}.tar.xz -C /tmp/
chmod 777 -R /tmp/coreutils-${CORE_UTILS}
mkdir -p ${HOME}/coreutils

cd /tmp/coreutils-${CORE_UTILS}

echo "CONFIGURE!"
./configure --prefix=${HOME}/coreutils \
           --libexecdir=${HOME}/coreutils/lib \
           --enable-no-install-program=kill,uptime

echo "MAKE!"
make

echo "MAKE INSTALL!"
make install

${HOME}/coreutils/bin/base64 --version
