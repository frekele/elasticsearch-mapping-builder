#!/bin/bash

# Only install if HOME_CORE_UTILS cache directory exist.
if [ ! -d "${HOME_CORE_UTILS}/bin" ]; then
   export FORCE_UNSAFE_CONFIGURE=1
   mkdir -p ${HOME_CORE_UTILS}
   
   echo "DOWNLOAD coreutils-${CORE_UTILS_VERSION}!"
   wget -qO- https://ftp.gnu.org/gnu/coreutils/coreutils-${CORE_UTILS_VERSION}.tar.xz -O /tmp/coreutils-${CORE_UTILS_VERSION}.tar.xz
   tar -xf /tmp/coreutils-${CORE_UTILS_VERSION}.tar.xz -C /tmp/
   chmod 777 -R /tmp/coreutils-${CORE_UTILS_VERSION}
   
   cd /tmp/coreutils-${CORE_UTILS_VERSION}
   echo "CONFIGURE coreutils-${CORE_UTILS_VERSION}!"
   ./configure --prefix=${HOME_CORE_UTILS}/ \
               --libexecdir=${HOME_CORE_UTILS}/lib \
               --enable-no-install-program=kill,uptime

   echo "MAKE coreutils-${CORE_UTILS_VERSION}!"
   make
   
   echo "MAKE INSTALL coreutils-${CORE_UTILS_VERSION}!"
   make install
fi

${HOME_CORE_UTILS}/bin/base64 --version
