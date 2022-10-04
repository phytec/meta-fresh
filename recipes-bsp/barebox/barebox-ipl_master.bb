require barebox_${PV}.bb
require recipes-bsp/barebox/barebox-ipl.inc

PR = "${INC_PR}.0"

do_deploy:append () {
    # deploy peripheral boot images (without GP header)
    perimg=$(echo ${BAREBOX_IPL_BIN} | sed 's/barebox/start/;s/-/_/g;s/mlo/sram/;s/img/pbl/')
    install -m 0644 ${B}/$perimg ${DEPLOYDIR}/${BAREBOX_IPL_IMAGE_NAME}.img.per
    ln -sf ${BAREBOX_IPL_IMAGE_NAME}.img.per ${DEPLOYDIR}/${BAREBOX_IPL_BIN_LINK_NAME}.per
}

INTREE_DEFCONFIG:ti33x = "am335x_mlo_defconfig"
