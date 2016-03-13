#include <jni.h>
#include "bme280-i2c.h"
#include "si1132.h"

static int pressure;
static int temperature;
static int humidity;
static float altitude;

JNIEXPORT jint JNICALL
Java_com_dpott197_weatherjni_MainActivity_openDevice(JNIEnv *env, jobject instance) {
    char *device = "/dev/i2c-1";
    return bme280_begin(device);
}

JNIEXPORT jint JNICALL
Java_com_dpott197_weatherjni_MainActivity_openLightDevice(JNIEnv *env, jobject instance) {
    char *device = "/dev/i2c-1";
    return si1132_begin(device);
}

JNIEXPORT jdouble JNICALL
Java_com_dpott197_weatherjni_MainActivity_getAltitude(JNIEnv *env, jobject instance) {
    bme280_read_pressure_temperature_humidity(&pressure, &temperature, &humidity);
    return bme280_readAltitude(pressure, 1024.25);
}

JNIEXPORT jdouble JNICALL
Java_com_dpott197_weatherjni_MainActivity_getHumidity(JNIEnv *env, jobject instance) {
    bme280_read_pressure_temperature_humidity(&pressure, &temperature, &humidity);
    return (double) humidity / 1024.0;
}

JNIEXPORT jdouble JNICALL
Java_com_dpott197_weatherjni_MainActivity_getPressure(JNIEnv *env, jobject instance) {
    bme280_read_pressure_temperature_humidity(&pressure, &temperature, &humidity);
    return (double) pressure / 100.0;
}

JNIEXPORT jdouble JNICALL
Java_com_dpott197_weatherjni_MainActivity_getTemp(JNIEnv *env, jobject instance) {
    bme280_read_pressure_temperature_humidity(&pressure, &temperature, &humidity);
    return (double) temperature / 100.0;
}

// Electromagnetic

JNIEXPORT jdouble JNICALL
Java_com_dpott197_weatherjni_MainActivity_getInfrared(JNIEnv *env, jobject instance) {
    return Si1132_readIR();
}

JNIEXPORT jdouble JNICALL
Java_com_dpott197_weatherjni_MainActivity_getUV(JNIEnv *env, jobject instance) {
    return Si1132_readUV() / 100.0;
}

JNIEXPORT jdouble JNICALL
Java_com_dpott197_weatherjni_MainActivity_getVisible(JNIEnv *env, jobject instance) {
    return Si1132_readVisible();
}

