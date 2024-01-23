# Copyright (C) 2024 PHYTEC Messtechnik GmbH

inherit phygittag
include recipes-kernel/linux/linux-common.inc

DEFAULT_PREFERENCE = "-1"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

GIT_URL = "git://git.phytec.de/${PN}"
SRC_URI = "${GIT_URL};branch=${BRANCH}"

PR = "${INC_PR}.0"

SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

INTREE_DEFCONFIG = "defconfig"

COMPATIBLE_MACHINE  = "^("
COMPATIBLE_MACHINE .=  "phyboard-pollux-imx8mp-3"
COMPATIBLE_MACHINE .= ")$"
