CUDA_PKG = "${BPN}"

require cuda-shared-binaries-${PV}.inc

MAINSUM = "1a0ea57d4c1b1d9394d7e4f6ab94baa2aa49883f4ba2d59a60b750bb88d0fdeb"
MAINSUM_x86-64 = "a4d649cb1433f7f9e2453abf2caf606819db0eb57def025568f276c31da53f85"

FILES_${PN} = "${prefix}/local/cuda-${CUDA_VERSION}"
FILES_${PN}-dev = ""
INSANE_SKIP_${PN} += "dev-so"
RDEPENDS_${PN} = "${BPN}-headers"
RDEPENDS_${PN}_append_class-nativesdk = " nativesdk-cuda-environment"

BBCLASSEXTEND = "native nativesdk"
