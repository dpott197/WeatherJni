#include <jni.h>
#include <stdio.h>
#include "bme280-i2c.h"
#include "si1132.h"
#include "si702x.h"
#include "bmp180.h"

JNIEXPORT jstring JNICALL
Java_com_dpott197_weatherjni_MainActivity_getTempFromJni(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "68 °F");
}