#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_dpott197_weatherjni_MainActivity_getTempFromJni(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "68 °F");
}