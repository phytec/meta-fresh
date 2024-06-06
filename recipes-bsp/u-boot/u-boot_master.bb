require recipes-bsp/u-boot/u-boot-common.inc
require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "bc-native dtc-native"

GIT_URL = "git://source.denx.de/u-boot/u-boot.git"
SRC_URI = "${GIT_URL};branch=${BRANCH}"

SRCREV = "${AUTOREV}"
BRANCH = "master"

DEFAULT_PREFERENCE = "-1"

# Location known to imx-boot component, where U-Boot artifacts
# should be additionally deployed.
# See below note above do_deploy_append_mx8m for the purpose of
# this delopyment location
BOOT_TOOLS = "imx-boot-tools"

PROVIDES += "u-boot"

# imx8m machines require additional deployment tasks to be
# carried out due to the fact that final boot image is constructed
# using imx-boot recipe. It produces a boot binary image, which is
# constructed from various binary components (u-boot with separate
# dtb, atf, DDR firmware and optional op-tee) into a single image
# using FIT format. This image is then parsed and loaded either via
# SPL directly (imx8mm), or using bootrom code (imx8mn and imx8mp).
#
# In order for imx-boot to construct the final binary boot image,
# it is required that the U-Boot dtb files are to be deployed into
# a location known by imx-boot so they could be picked up and
# inserted into the boot container.
do_deploy_append_mx8m() {
    # Deploy the mkimage, u-boot-nodtb.bin and fsl-imx8m*-XX.dtb for mkimage to generate boot binary
    if [ -n "${UBOOT_CONFIG}" ]; then
        for config in ${UBOOT_MACHINE}; do
            i=$(expr $i + 1);
            for type in ${UBOOT_CONFIG}; do
                j=$(expr $j + 1);
                if [ $j -eq $i ]
                then
                    install -d ${DEPLOYDIR}/${BOOT_TOOLS}
                    install -m 0777 ${B}/${config}/arch/arm/dts/${UBOOT_DTB_NAME}  ${DEPLOYDIR}/${BOOT_TOOLS}
                    install -m 0777 ${B}/${config}/u-boot-nodtb.bin  ${DEPLOYDIR}/${BOOT_TOOLS}/u-boot-nodtb.bin-${MACHINE}-${UBOOT_CONFIG}
                fi
            done
            unset  j
        done
        unset  i
   fi
}

do_deploy_append_rk3288 () {
    # deploy SPL images for USB, SD and SPI boot sources.
    ${B}/tools/mkimage -n rk3288 -T rkimage -d ${B}/${SPL_BINARY} ${DEPLOYDIR}/${SPL_BINARYNAME}.rkimage
    ${B}/tools/mkimage -n rk3288 -T rksd -d ${B}/${SPL_BINARY} ${DEPLOYDIR}/${SPL_BINARYNAME}.rksd
    ${B}/tools/mkimage -n rk3288 -T rkspi -d ${B}/${SPL_BINARY} ${DEPLOYDIR}/${SPL_BINARYNAME}.rkspi
    cat ${B}/u-boot-dtb.bin >> ${DEPLOYDIR}/${SPL_BINARYNAME}.rkimage
    cat ${B}/u-boot-dtb.bin >> ${DEPLOYDIR}/${SPL_BINARYNAME}.rksd
    cat ${B}/u-boot-dtb.bin >> ${DEPLOYDIR}/${SPL_BINARYNAME}.rkspi
}

COMPATIBLE_MACHINE = "^("
COMPATIBLE_MACHINE .= "phyboard-pollux-imx8mp-1"
COMPATIBLE_MACHINE .= "|phycore-rk3288-3"
COMPATIBLE_MACHINE .= ")$"
