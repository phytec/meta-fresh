include recipes-kernel/linux/linux-common.inc

DESCRIPTION = "The linux-vanilla_master recipe provides the latest unpatched kernel.\
               Device tree files may be provided in meta-phytec, but anything\
               besides adding configuration data is prohibited. Its purpose is\
               to evaluate features currently supported by the upstream kernel\
               for our products."
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

GIT_URL = "git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git"
SRC_URI = "${GIT_URL};branch=${BRANCH}"

DEFAULT_PREFERENCE = "-1"

PR = "${INC_PR}.0"

# NOTE: Keep version in filename in sync with commit id!
SRCREV = "${AUTOREV}"
BRANCH = "master"

S = "${WORKDIR}/git"

INTREE_DEFCONFIG = "multi_v7_defconfig"

COMPATIBLE_MACHINE  = "^("
COMPATIBLE_MACHINE .=  "phyboard-wega-am335x-1"
COMPATIBLE_MACHINE .= "|phyboard-wega-am335x-2"
COMPATIBLE_MACHINE .= "|phycore-am335x-1"
COMPATIBLE_MACHINE .= "|phycore-rk3288-3"
COMPATIBLE_MACHINE .= "|phyboard-segin-imx6ul-2"
COMPATIBLE_MACHINE .= "|phyboard-segin-imx6ul-7"
COMPATIBLE_MACHINE .= ")$"
