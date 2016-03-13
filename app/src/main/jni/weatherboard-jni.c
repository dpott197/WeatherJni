#include <jni.h>
#include <stdio.h>
#include "bme280-i2c.h"
#include "si1132.h"
#include "si702x.h"
#include "bmp180.h"

static int pressure;
static int temperature;
static int humidity;
static float altitude;

JNIEXPORT jdouble JNICALL
Java_com_dpott197_weatherjni_MainActivity_getTemp(JNIEnv *env, jobject instance) {
    bme280_read_pressure_temperature_humidity(&pressure, &temperature, &humidity);
    return (double) temperature / 100.0;
}

JNIEXPORT jint JNICALL
Java_com_dpott197_weatherjni_MainActivity_openDevice(JNIEnv *env, jobject instance) {
    char *device = "/dev/i2c-1";
    return bme280_begin(device);
}