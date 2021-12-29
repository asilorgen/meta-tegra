DESCRIPTION = "NVIDIA Fan Control"
L4T_DEB_COPYRIGHT_MD5 = "0131b26a89a8fbf5ca06d10b85ba2540"

L4T_DEB_TRANSLATED_BPN = "nvidia-l4t-nvfancontrol"
TEGRA_LIBRARIES_CONTAINER_CSV = ""
DEPENDS = "tegra-nvpower"

require tegra-debian-libraries-common.inc

MAINSUM = "120b08216d1ff657fb29b3b2e61bf135e959c1f2a5376f03d4e5202139a9968a"

SRC_URI += "\
   file://nvfancontrol.init \
   file://nvfancontrol.service \
"

do_install() {
    install -d ${D}${sbindir}
    install -m 0755 ${B}/usr/sbin/nvfancontrol ${D}${sbindir}/
    install -d ${D}${sysconfdir}/init.d
    install -m 0644 ${B}/etc/nvpower/nvfancontrol/${NVFANCONTROL}.conf ${D}${sysconfdir}/nvfancontrol.conf
    install -m 0755 ${WORKDIR}/nvfancontrol.init ${D}${sysconfdir}/init.d/nvfancontrol
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/nvfancontrol.service ${D}${systemd_system_unitdir}/
}

inherit systemd update-rc.d

INITSCRIPT_NAME = "nvfancontrol"
INITSCRIPT_PARAMS = "defaults"
SYSTEMD_SERVICE:${PN} = "nvfancontrol.service"
RDEPENDS:${PN} = "bash tegra-nvpower tegra-nvpmodel"
PACKAGE_ARCH = "${MACHINE_ARCH}"
