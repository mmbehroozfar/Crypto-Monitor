#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_mmb_data_datasource_remote_util_SecureKey_publicKey(JNIEnv *env, jobject /* this */) {
    std::string publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAx4VLbUxvFpPS5k96GH45iK2qRZHQOk7cZcCs7HK8H18iQj9uCGUL8lt0dmxUg3t/YLF+m6yoK/bHDYMnHcSeAmuycIMsjGtngELWrOl6vMRzrN+/uyhqI0LAWj/DOVXT5pF7Kq5gAD9g2YenEgdrDDt0dggryU/zmLVBFq13BU+h78qxW5Nv5URPu+PHjcOHNqeHakvsE/U8BaaKichdeKcKYJGYQDmt+/yukZQ1QGtAvvms/cl4j2Y/QDEiPFhWSMeHi9a+BBKGSGJVfxCoRSZ5OrDPKHveokhRV53lPloR8BDP0fCLBrMRW3sgqQDlLUNuPwZm2nlmpfYGCl9HuQIDAQAB";
    return env->NewStringUTF(publicKey.c_str());
}