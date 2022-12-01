DESCRIPTION = "NVIDIA TensorRT Core (GPU Inference Engine) for deep learning"
LICENSE = "Proprietary"

inherit l4t_deb_pkgfeed container-runtime-csv

HOMEPAGE = "http://developer.nvidia.com/tensorrt"

L4T_DEB_GROUP = "tensorrt"

SRC_COMMON_DEBS = "\
    libnvinfer7_${PV}+cuda10.2_arm64.deb;downloadfilename=${PREFIX}libnvinfer7_${PV}+cuda10.2_arm64.deb;name=lib;subdir=tensorrt \
    libnvinfer-dev_${PV}+cuda10.2_arm64.deb;downloadfilename=${PREFIX}libnvinfer-dev_${PV}+cuda10.2_arm64.deb;name=dev;subdir=tensorrt \
    libnvparsers7_${PV}+cuda10.2_arm64.deb;downloadfilename=${PREFIX}libnvparsers7_${PV}+cuda10.2_arm64.deb;name=nvp;subdir=tensorrt \
    libnvparsers-dev_${PV}+cuda10.2_arm64.deb;downloadfilename=${PREFIX}libnvparsers-dev_${PV}+cuda10.2_arm64.deb;name=nvpdev;subdir=tensorrt \
"
LIBSHA256SUM = "88636112a84fde159cce5b6eed11907bc98ddef02e88310c788a5462b88293be"
DEVSHA256SUM = "436e0832560e1c5b2a5377c1f9679ae1bf60649e5c0537a81c77baed53066633"
NVPSHA256SUM = "5477aeb1898d55182de02c3861f3576338ab01f2b71c776cb3fff5707e8e7758"
NVPDEVSHA256SUM = "0554c20ce85cfa0675f77df2539c34cecdeed9cf0402989c3404ab6e158f39aa"

SRC_URI[lib.sha256sum] = "${LIBSHA256SUM}"
SRC_URI[dev.sha256sum] = "${DEVSHA256SUM}"
SRC_URI[nvp.sha256sum] = "${NVPSHA256SUM}"
SRC_URI[nvpdev.sha256sum] = "${NVPDEVSHA256SUM}"

COMPATIBLE_MACHINE = "(tegra)"

LIC_FILES_CHKSUM = "file://usr/include/aarch64-linux-gnu/NvInfer.h;endline=48;md5=59218f2f10ab9e4132dda76c59e80fa1"

DEPENDS = "tegra-libraries-multimedia"

S = "${WORKDIR}/tensorrt"

CONTAINER_CSV_FILES = "${libdir}/*.so*"

do_configure() {
    :
}

do_compile() {
    :
}

do_install() {
    install -d ${D}${includedir}
    install -m 0644 ${S}/usr/include/aarch64-linux-gnu/*.h ${D}${includedir}
    install -d ${D}${libdir}
    cp --preserve=mode,timestamps,links --no-dereference ${S}/usr/lib/aarch64-linux-gnu/*.so* ${D}${libdir}
}

INSANE_SKIP_${PN} = "already-stripped"
PACKAGE_ARCH = "${TEGRA_PKGARCH}"
