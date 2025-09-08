# Copyright (C) 2022 PHYTEC Messtechnik GmbH,
# Author: Norbert Wesp <n.wesp@phytec.de>

inherit kernel kernel-yocto
inherit buildinfo kernel-deploy-oftree
include recipes-kernel/linux/linux-common.inc

DEFAULT_PREFERENCE = "-1"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

GIT_URL = "git://github.com/phytec/linux-phytec.git;protocol=https"
BRANCH = "v5.15.y-phy"
SRC_URI = "${GIT_URL};branch=${BRANCH}"

RT_PATCH_FILE = "patch-5.15.189-rt87.patch.xz"
RT_PATCH = "${KERNELORG_MIRROR}/linux/kernel/projects/rt/5.15/older/${RT_PATCH_FILE};name=rt-patch"
SRC_URI:append = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'preempt-rt', '${RT_PATCH}', '', d)} \
"
SRC_URI[rt-patch.sha256sum] = "3c7597527a78ba31bbc7629201f38b5e8a1d787790acf1b5d4b236d6b6fd3b94"

PR = "${INC_PR}.0"

SRCREV = "${AUTOREV}"

PV = "5.15.y-phy"

S = "${WORKDIR}/git"

INTREE_DEFCONFIG = "imx_v6_v7_defconfig imx6_phytec_machine.config imx6_phytec_platform.config"
INTREE_DEFCONFIG:ti33x = "am335x_phytec_defconfig"

KERNEL_DEVICETREE_32BIT_COMPATIBILITY_UPDATE = "1"

COMPATIBLE_MACHINE  = "^("
COMPATIBLE_MACHINE .=  "phyboard-mira-imx6-3"
COMPATIBLE_MACHINE .= "|phyboard-mira-imx6-4"
COMPATIBLE_MACHINE .= "|phyboard-mira-imx6-5"
COMPATIBLE_MACHINE .= "|phyboard-mira-imx6-8"
COMPATIBLE_MACHINE .= "|phyboard-mira-imx6-9"
COMPATIBLE_MACHINE .= "|phyboard-mira-imx6-10"
COMPATIBLE_MACHINE .= "|phyboard-mira-imx6-11"
COMPATIBLE_MACHINE .= "|phyboard-mira-imx6-12"
COMPATIBLE_MACHINE .= "|phyboard-mira-imx6-13"
COMPATIBLE_MACHINE .= "|phyboard-mira-imx6-14"
COMPATIBLE_MACHINE .= "|phyboard-mira-imx6-15"
COMPATIBLE_MACHINE .= "|phyboard-segin-imx6ul-2"
COMPATIBLE_MACHINE .= "|phyboard-segin-imx6ul-3"
COMPATIBLE_MACHINE .= "|phyboard-segin-imx6ul-4"
COMPATIBLE_MACHINE .= "|phyboard-segin-imx6ul-5"
COMPATIBLE_MACHINE .= "|phyboard-segin-imx6ul-6"
COMPATIBLE_MACHINE .= "|phyboard-segin-imx6ul-7"
COMPATIBLE_MACHINE .= "|phyboard-segin-imx6ul-8"

COMPATIBLE_MACHINE .= "|phyflex-imx6-1"
COMPATIBLE_MACHINE .= "|phyflex-imx6-2"
COMPATIBLE_MACHINE .= "|phyflex-imx6-8"

COMPATIBLE_MACHINE .= "|phyboard-nunki-imx6-1"
COMPATIBLE_MACHINE .= "|phygate-tauri-s-imx6ul-1"

COMPATIBLE_MACHINE .= "|phyboard-regor-am335x-1"
COMPATIBLE_MACHINE .= "|phyboard-wega-am335x-1"
COMPATIBLE_MACHINE .= "|phyboard-wega-am335x-2"
COMPATIBLE_MACHINE .= "|phyboard-wega-am335x-3"
COMPATIBLE_MACHINE .= "|phyboard-wega-r2-am335x-1"
COMPATIBLE_MACHINE .= "|phycore-am335x-1"
COMPATIBLE_MACHINE .= "|phycore-am335x-2"
COMPATIBLE_MACHINE .= "|phycore-am335x-3"
COMPATIBLE_MACHINE .= "|phycore-am335x-4"
COMPATIBLE_MACHINE .= "|phycore-am335x-5"
COMPATIBLE_MACHINE .= "|phycore-emmc-am335x-1"
COMPATIBLE_MACHINE .= "|phycore-r2-am335x-1"
COMPATIBLE_MACHINE .= "|phycore-r2-am335x-2"
COMPATIBLE_MACHINE .= "|phycore-r2-am335x-3"
COMPATIBLE_MACHINE .= "|phycore-r2-am335x-4"
COMPATIBLE_MACHINE .= "|phycore-r2-am335x-5"
COMPATIBLE_MACHINE .= "|phycore-r2-am335x-6"
COMPATIBLE_MACHINE .= ")$"
