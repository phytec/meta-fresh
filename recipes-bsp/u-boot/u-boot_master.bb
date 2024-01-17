require recipes-bsp/u-boot/u-boot-common.inc
require recipes-bsp/u-boot/u-boot.inc

inherit ${@oe.utils.ifelse(d.getVar('UBOOT_PROVIDES_BOOT_CONTAINER') == '1', 'imx-boot-container', '')}

DEPENDS += "bc-native dtc-native python3-setuptools-native"

GIT_URL = "git://source.denx.de/u-boot.git"
SRC_URI = "${GIT_URL};branch=${BRANCH}"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"

SRCREV = "${AUTOREV}"
BRANCH = "master"

DEFAULT_PREFERENCE = "-1"

# Location known to imx-boot component, where U-Boot artifacts
# should be additionally deployed.
# See below note above do_deploy:append:mx8m for the purpose of
# this delopyment location
BOOT_TOOLS = "imx-boot-tools"

PROVIDES += "u-boot"

do_deploy:append:rk3288 () {
    # deploy SPL images for USB, SD and SPI boot sources.
    ${B}/tools/mkimage -n rk3288 -T rkimage -d ${B}/${SPL_BINARY} ${DEPLOYDIR}/${SPL_BINARYNAME}.rkimage
    ${B}/tools/mkimage -n rk3288 -T rksd -d ${B}/${SPL_BINARY} ${DEPLOYDIR}/${SPL_BINARYNAME}.rksd
    ${B}/tools/mkimage -n rk3288 -T rkspi -d ${B}/${SPL_BINARY} ${DEPLOYDIR}/${SPL_BINARYNAME}.rkspi
    cat ${B}/u-boot-dtb.bin >> ${DEPLOYDIR}/${SPL_BINARYNAME}.rkimage
    cat ${B}/u-boot-dtb.bin >> ${DEPLOYDIR}/${SPL_BINARYNAME}.rksd
    cat ${B}/u-boot-dtb.bin >> ${DEPLOYDIR}/${SPL_BINARYNAME}.rkspi
}

COMPATIBLE_MACHINE = "^("
COMPATIBLE_MACHINE .= "phyboard-polis-imx8mm-5"
COMPATIBLE_MACHINE .= "|phyboard-pollux-imx8mp-3"
COMPATIBLE_MACHINE .= "|phygate-tauri-l-imx8mm-2"
COMPATIBLE_MACHINE .= "|phycore-rk3288-3"
COMPATIBLE_MACHINE .= ")$"
